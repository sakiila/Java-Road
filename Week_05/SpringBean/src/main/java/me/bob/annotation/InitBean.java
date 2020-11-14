package me.bob.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * 注解方式加载Bean
 */
//@Import(Student.class)
public class InitBean {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(Student.class);
        applicationContext.refresh();

        Student student = applicationContext.getBean(Student.class);

        System.out.println("student = " + student);
    }
}