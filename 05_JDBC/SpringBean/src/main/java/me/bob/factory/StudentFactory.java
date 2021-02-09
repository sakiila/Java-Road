package me.bob.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentFactory implements FactoryBean<Student> {

    @Override
    public Student getObject() throws Exception {
        return new Student(3, "Bob03");
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
