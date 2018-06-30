package com.captain.wds.javamemory;

import java.util.ArrayList;

/**
 * Created by wds on 2018-5-16.
 */

public class HeapOOM {
    static class OOMObject {

    }

    //堆内存溢出
    public static void main(String[] args) {
        ArrayList<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }






    ///////////////////////////////////////////////////////////////////////////
    // new String 都是在堆上创建字符串对象。当调用intern方法时，
    //编译器会将字符串添加到常量池中，并返回指向该常量的引用
    ///////////////////////////////////////////////////////////////////////////
    public void temp1() {
        String str1 = new String("abc");
        String str2 = new String("abc");
        String str3 = new String("wdscaptain");

    }

    ///////////////////////////////////////////////////////////////////////////
    //
    //通过字面量赋值创建字符串（String str="wds"）时，会现在常量池中查
    // 找是否存在相同的字符串，若存在，则将栈中的引用直接指向字符串；若不存在，则在常量池中生成一个字符
    // 串，再将栈中的引用指向该字符串。
    ///////////////////////////////////////////////////////////////////////////
    public void temp2() {
        String str1 = "abc";
        String str2 = "abc";
        String str3 = "wdscaptain";
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    //常量字符串的“+”操作，编译阶段直接回合成一个字符串。如String str=“JA”+"VA";在编译阶段
    // 会直接合并成语句String str=“JAVA”,于是会去常量池中查看是否存在“JAVA”，从而进行创建或引用
    //对于final字段，编译期直接进行了常量替换（而对于非final字段则是在运行期进行赋值处理的）
    ///////////////////////////////////////////////////////////////////////////

    public void temp3() {
        final String str1 = "wds";
        final String str2 = "captain";
        String str3 = str1 + str2;

        //在编译时，直接替换成了String str3=“wds”+“captain”，
        // 然后再替换成 String str3=“wdscaptain”;
    }

    ///////////////////////////////////////////////////////////////////////////
    // 在JDK 1.7下，当执行str2.intern();时，因为常量池中没有“str01”
    // 这个字符串，所以会在常量池中生成一个对堆中的“str01”的引用(
    // 注意这里是引用 ，就是这个区别于JDK 1.6的地方。在JDK1.6下
    // 是生成原字符串的拷贝)，
    // 而在进行String str1 = “str01”;字面量赋值的时候，常量池中已经
    // 存在一个引用，所以直接返回了该引用，因此str1和str2都指向
    // 堆中的同一个字符串，返回true。
    ///////////////////////////////////////////////////////////////////////////    
    public static void temp4() {
        String str2 = new String("str") + new String("01");
        str2.intern();
        String str1 = "str01";
        System.out.println(str1 == str2);//true
        
    }

    public static void temp5() {
        String str2 = new String("str") + new String("01");
        String str1 = "str01";
        str2.intern();
        System.out.println(str1 == str2);//false

    }
    
    
    
   

}
