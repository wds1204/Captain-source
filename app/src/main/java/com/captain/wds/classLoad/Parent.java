package com.captain.wds.classLoad;

/**
 * Created by wds on 2018-5-25.
 */

public class Parent {
    static {
        System.out.println("Static Child");
    }

    public Parent() {
        System.out.println("Child");
    }

    public static void main(String[] args) {
        Class<Parent> parentClass1 = Parent.class;
        Class<Parent> parentClass2 = Parent.class;

        new Parent();
        new Parent();
    }
}

class IParent {

    static {
        System.out.println("Static Parent");
    }

    public IParent() {
        System.out.println("Parent");
    }

}
