package me.bob.staticFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentFactory {

    @Bean
    public static Student createStudent() {
        return new Student(4, "Bob04");
    }

}
