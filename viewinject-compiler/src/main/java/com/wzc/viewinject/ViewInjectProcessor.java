package com.wzc.viewinject;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * @author wzc
 * @date 2018/7/30
 */
@AutoService(Processor.class)
public class ViewInjectProcessor extends AbstractProcessor {

    private Filer mFilerUtils;
    private Elements elementUtils;
    private Messager messager;
    private Map<String, ProxyInfo> mProxyMap = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        // Returns the filer used to create new source, class, or auxiliary files.
        // 跟文件相关的辅助类，生成JavaSourceCode.
        mFilerUtils = processingEnv.getFiler();
        // Returns an implementation of some utility methods for operating on elements
        // 跟元素相关的辅助类，帮助我们去获取一些元素相关的信息
        elementUtils = processingEnv.getElementUtils();
        // Returns the messager used to report errors, warnings, and other notices.
        // 跟日志相关的辅助类
        messager = processingEnv.getMessager();
    }

    // 返回支持的注解类型
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> supportTypes = new LinkedHashSet<>();
        supportTypes.add(Bind.class.getCanonicalName());
        return supportTypes;
    }

    // 返回支持的源码版本
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    // 这个方法是必须实现的, 是编写代码的核心部分
    // 两个大步骤:
    // 收集信息
    // 生成代理类(也就是编译时生成的类)
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        messager.printMessage(Diagnostic.Kind.NOTE, "process...");
        // 调用一下mProxyMap.clear();，因为process可能会多次调用，避免生成重复的代理类，避免生成类的类名已存在异常。
        mProxyMap.clear();
        // 获取 @Bind 注解的元素集合
        Set<? extends Element> elementsWithBind = roundEnv.getElementsAnnotatedWith(Bind.class);
        // 收集信息
        for (Element element : elementsWithBind) {
            // 检查 element 类型
            if (!checkAnnotationValid(element, Bind.class)) {
                return false;
            }
            // field type
            VariableElement variableElement = (VariableElement) element;
            //class type
            TypeElement classElement = (TypeElement) variableElement.getEnclosingElement();
            //full class name
            String qualifiedName = classElement.getQualifiedName().toString();

            ProxyInfo proxyInfo = mProxyMap.get(qualifiedName);
            // 如果没有生成才会去生成一个新的，ProxyInfo与类是一一对应的。
            if (proxyInfo == null) {
                proxyInfo = new ProxyInfo(elementUtils, classElement);
                mProxyMap.put(qualifiedName, proxyInfo);
            }
            // 将与该类对应的且被@BindView声明的VariableElement加入到ProxyInfo中去，key为我们声明时填写的id，即View的id。
            Bind bindAnnotation = variableElement.getAnnotation(Bind.class);
            int id = bindAnnotation.value();
            proxyInfo.injectVariables.put(id, variableElement);
        }
        // 生成代理类
        for (String key : mProxyMap.keySet()) {
            ProxyInfo proxyInfo = mProxyMap.get(key);
            try {
                JavaFileObject jfo = processingEnv.getFiler().createSourceFile(
                        proxyInfo.getProxyClassFullName(),
                        proxyInfo.getTypeElement());
                Writer writer = jfo.openWriter();
                writer.write(proxyInfo.generateJavaCode());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                error(proxyInfo.getTypeElement(),
                        "Unable to write injector for type %s: %s",
                        proxyInfo.getTypeElement(), e.getMessage());
            }

        }
        return true;
    }

    private boolean checkAnnotationValid(Element annotatedElement, Class clazz) {
        if (annotatedElement.getKind() != ElementKind.FIELD) {
            error(annotatedElement, "%s must be declared on field.", clazz.getSimpleName());
            return false;
        }
        if (ClassValidator.isPrivate(annotatedElement)) {
            error(annotatedElement, "%s() must can not be private.", annotatedElement.getSimpleName());
            return false;
        }

        return true;
    }

    private void error(Element element, String message, Object... args) {
        if (args.length > 0) {
            message = String.format(message, args);
        }
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message, element);
    }
}
