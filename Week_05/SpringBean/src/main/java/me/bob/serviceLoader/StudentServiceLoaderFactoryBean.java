package me.bob.serviceLoader;

import org.springframework.beans.factory.serviceloader.ServiceLoaderFactoryBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class StudentServiceLoaderFactoryBean extends ServiceLoaderFactoryBean {

    @PostConstruct
    public void setServiceType() {
        super.setServiceType(StudentFactory.class);
    }
}
