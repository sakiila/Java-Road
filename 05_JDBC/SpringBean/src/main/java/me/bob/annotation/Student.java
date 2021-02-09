package me.bob.annotation;

import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ToString
public class Student {

    private Integer id = 2;

    private String name = "Bob";

}
