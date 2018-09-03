package com.wzc.masterblogdemo.rxjava;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzc
 * @date 2018/8/1
 */
public class MockData {

    private MockData() {
        //no instance
    }

    public static List<Student> getStudents() {
        List<Student> result = new ArrayList<>();
        // Student A
        List<Source> studentASources = new ArrayList<>();
        studentASources.add(new Source(1, "chinese", 90));
        studentASources.add(new Source(2, "math", 100));
        studentASources.add(new Source(3, "english", 18));
        Student studentA = new Student("StudentA", 1000, studentASources);
        // Student B
        List<Source> studentBSources = new ArrayList<>();
        studentBSources.add(new Source(1, "chinese", 90));
        studentBSources.add(new Source(2, "math", 0));
        studentBSources.add(new Source(3, "english", 100));
        Student studentB = new Student("StudentB", 1001, studentBSources);
        // Student C
        List<Source> studentCSources = new ArrayList<>();
        studentCSources.add(new Source(1, "chinese", 10));
        studentCSources.add(new Source(2, "math", 0));
        studentCSources.add(new Source(3, "english", 100));
        Student studentC = new Student("StudentC", 1002, studentCSources);
        // Student D
        List<Source> studentDSources = new ArrayList<>();
        studentDSources.add(new Source(1, "chinese", 60));
        studentDSources.add(new Source(2, "math", 50));
        studentDSources.add(new Source(3, "english", 50));
        Student studentD = new Student("StudentD", 1003, studentDSources);

        result.add(studentA);
        result.add(studentB);
        result.add(studentC);
        result.add(studentD);

        return result;
    }
}
