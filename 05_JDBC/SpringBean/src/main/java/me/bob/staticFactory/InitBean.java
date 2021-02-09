package me.bob.staticFactory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 静态工厂方式加载Bean
 */
public class InitBean {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(StudentFactory.class);
        applicationContext.refresh();

        Student student = StudentFactory.createStudent();

        System.out.println("student = " + student);
    }
}
