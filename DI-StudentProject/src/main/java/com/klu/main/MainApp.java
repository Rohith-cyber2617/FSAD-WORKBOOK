package com.klu.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.klu.config.AppConfig;
import com.klu.model.Student;

public class MainApp {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        // Annotation-based bean
        Student s1 = (Student) context.getBean("annoStudent");
        s1.display();

        System.out.println("------------------");

        // XML-based bean
        Student s2 = (Student) context.getBean("xmlStudent");
        s2.display();
    }
}

