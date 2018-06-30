package com.captain.wds.javamemory;

/**
 * Created by wds on 2018-5-16.
 */

public class JVMStackSOF {
    private int stackLenght = 1;

    public void stackLeak() {
        stackLenght++;
        stackLeak();
    }

    public static void main(String[] args) {
        JVMStackSOF stackSOF = new JVMStackSOF();
        try {
            stackSOF.stackLeak();

        } catch (Throwable e) {
            System.out.println("stack length :" + stackSOF.stackLenght);
            throw e;
        }
    }
}
