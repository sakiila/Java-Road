package me.bob.serviceLoader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
public class Student {

    private Integer id ;

    private String name;

}
