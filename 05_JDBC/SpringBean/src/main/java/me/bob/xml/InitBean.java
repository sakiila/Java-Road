package me.bob.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 配置文件加载Bean
 */
public class InitBean {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student student01 = (Student) applicationContext.getBean("student");

        System.out.println("student = " + student01);
    }
}
