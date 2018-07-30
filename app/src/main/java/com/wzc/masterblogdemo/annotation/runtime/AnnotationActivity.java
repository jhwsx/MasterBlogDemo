package com.wzc.masterblogdemo.annotation.runtime;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.wzc.masterblogdemo.R;

import java.lang.reflect.Field;

/**
 * @author wzc
 * @date 2018/7/30
 */
public class AnnotationActivity extends AppCompatActivity {

    @GetViewTo(R.id.textView)
    private TextView mTv;

    @GetViewTo(R.id.button)
    private Button mBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        //通过注解生成View；
        getAllAnnotationViews();

        mTv.setText("Hello");
        mBtn.setText("Click");
    }

    /**
     * 解析注解, 获取控件
     */
    private void getAllAnnotationViews() {
        // 获取声明的字段
        Field[] fields = this.getClass().getDeclaredFields();

        try {
            // 遍历字段数组
            for (Field field : fields) {
                // 当前这个 Field 对象有没有声明注解
                if (field.getAnnotations() != null) {
                    // 是不是 GetViewTo 这个注解
                    if (field.isAnnotationPresent(GetViewTo.class)) {
                        // 修改反射属性
                        field.setAccessible(true);
                        // 获取注解对象
                        GetViewTo getViewTo = field.getAnnotation(GetViewTo.class);
                        // findViewById 将注解的 id ，找到 View 注入成员变量中
                        field.set(this, findViewById(getViewTo.value()));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
