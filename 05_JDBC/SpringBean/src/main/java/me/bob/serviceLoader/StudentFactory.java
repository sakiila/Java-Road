package me.bob.serviceLoader;

public interface StudentFactory {

    default Student createStudent() {
        return new Student(6, "Bob06");
    }
}
