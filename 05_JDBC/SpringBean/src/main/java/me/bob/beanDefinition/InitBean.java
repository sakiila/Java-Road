package me.bob.beanDefinition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 注册方式加载Bean
 */
public class InitBean {

    @Autowired
    private Student student;

    public static void main(String[] args) {

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition();
        beanDefinitionBuilder.addPropertyValue("id", 5).addPropertyValue("name", "Bob05");
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        beanDefinition.setBeanClass(Student.class);

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(InitBean.class);
        applicationContext.registerBeanDefinition("student", beanDefinition);
        applicationContext.refresh();

        InitBean bean = applicationContext.getBean(InitBean.class);

        System.out.println("student = " + bean.student);
    }
}