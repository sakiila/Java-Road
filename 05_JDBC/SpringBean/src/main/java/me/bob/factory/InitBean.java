package me.bob.factory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 工厂方式加载Bean
 */
public class InitBean {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(StudentFactory.class);
        applicationContext.refresh();

        StudentFactory studentFactory = applicationContext.getBean(StudentFactory.class);

        System.out.println("student03 = " + studentFactory.getObject());
    }
}
