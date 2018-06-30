package com.captain.wds.classLoad;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wds on 2018-5-24.
 */

public class ClassLoadTest {
    public static void main(String[]args)throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj = myLoader.loadClass("com.captain.wds.classLoad.ClassLoadTest").newInstance();
        System.out.println(obj.getClass());
        ClassLoader classLoader = ClassLoadTest.class.getClassLoader();
        System.out.println("classLoader  :" + classLoader.toString());

        System.out.println("myLoader  :" + myLoader.toString());
        System.out.println(obj instanceof com.captain.wds.classLoad.ClassLoadTest);
    }
}
