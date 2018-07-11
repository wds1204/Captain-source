package com.captain.wds.reflect;

public class ReflectTemp {

    public static void main(String[] args) {
        try {
            Class<?> aClass = Class.forName("com.captain.wds.reflect.AClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
