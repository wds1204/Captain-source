package com.captain.wds.classLoad;

/**
 * Created by wudongsheng on 2018/4/17.
 */

public class TestClass {
    public int sum(int a,int b){
        return a + b;
    }

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder("wds/").append("captain/").append(false ? "&mobile_shield=1&ukey=12" : "");
        System.out.println("stringBuilder" + stringBuilder.toString());
    }
}
