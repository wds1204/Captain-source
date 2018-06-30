package com.captain.wds.javamemory;

import java.util.ArrayList;

/**
 * Created by wds on 2018-5-16.
 */

public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        ///使用list保持持有常量池引用，避免full gc回收常量池行为
        ArrayList<String> list = new ArrayList<>();

        //10MB的Permsize在integer范围内足够产生OOM了

        int i = 0;
        String intern = String.valueOf(i++).intern();
        System.out.println("intern :" + intern);
//        while (true) {
//            list.add(String.valueOf(i++).intern());
//        }
    }
}
