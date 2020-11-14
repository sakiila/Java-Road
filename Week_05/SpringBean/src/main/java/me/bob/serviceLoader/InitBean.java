package me.bob.serviceLoader;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 注解方式加载Bean
 */
public class InitBean {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(StudentServiceLoaderFactoryBean.class);
        applicationContext.refresh();

        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        ServiceLoader<StudentFactory> bean = autowireCapableBeanFactory.getBean(ServiceLoader.class);

        Iterator<StudentFactory> iterator = bean.iterator();
        while (iterator.hasNext()) {
            StudentFactory studentFactory = iterator.next();
            System.out.println("student = " + studentFactory.createStudent());
        }

    }
}