package com.captain.wds.classLoad;

/**
 * Created by wds on 2018-5-8.
 */

public class ClassLoaderTree {
    public static void main(String[] args) {
        ClassLoader loader = ClassLoaderTree.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.toString());
            loader = loader.getParent();
        }
        //sun.misc.Launcher$AppClassLoader@74a14482 系统类加载器
        // sun.misc.Launcher$ExtClassLoader@135fbaa4 扩展类加载器


    }
}
