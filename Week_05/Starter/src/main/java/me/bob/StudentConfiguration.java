package me.bob;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(StudentProperties.class)
public class StudentConfiguration {

    @Bean
    public StudentService createStudent(StudentProperties properties) {
        StudentService service = new StudentService();
        service.setId(properties.getId());
        service.setName(properties.getName());
        return service;
    }

}
