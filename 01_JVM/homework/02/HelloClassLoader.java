package com.bob.classloader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;

public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) {
        try {
            Class<?> hello = new HelloClassLoader().findClass("Hello");
            Object helloObject = hello.newInstance();
            Method helloMethod = hello.getMethod("hello");
            helloMethod.invoke(helloObject);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] fileByte = getBytes();
        byte[] decodeByte = decode(fileByte);
        return defineClass(name, decodeByte, 0, decodeByte.length);
    }

    private byte[] getBytes() {
        File file = new File("src/main/java/com/bob/classloader/Hello.xlass");

        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }

    public byte[] decode(byte[] bytes) {
        byte[] res = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            res[i] = (byte) (255 - bytes[i]);
        }
        return res;
    }
}
