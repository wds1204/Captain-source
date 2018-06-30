package com.captain.wds.set;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by wds on 2018-4-18.
 */

public class HashMapTest {

    private int a;

    public HashMapTest(int a) {
        this.a = a;
    }

    public static void main(String[] args) {


        //hash_equal();


        Map<String, Integer> tempMap = new HashMap<String, Integer>();
        tempMap.put("a",12);
        tempMap.put("b",24);
        tempMap.put("c", 48);
        tempMap.put("d", 96);

        /*===========Map多种遍历方式============*/
        //方式一：map.entrySet 遍历
        Iterator<Map.Entry<String, Integer>> iterator = tempMap.entrySet().iterator();
        System.out.println("方式一");
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            String k = entry.getKey().toString();
            String v = entry.getValue().toString();
            System.out.print("  k :" + k + " ,v :" + v+"\t");
        }
        System.out.println("\n方式二");
        //方式二：for-each 新特性
        for (Map.Entry<String, Integer> entry : tempMap.entrySet()) {
            String v = entry.getValue().toString();
            String k = entry.getKey().toString();
            System.out.print("  k :" + k + " ,v :" + v+"\t");
        }
        System.out.println("\n方式三-1");
        //方式三：keySet 遍历
        for (Iterator<String>  i= tempMap.keySet().iterator(); i.hasNext(); ) {
            String k = i.next();//循环输出key
            Integer v = tempMap.get(k);
            System.out.print("  k :" + k + " ,v :" + v+"\t");
        }
        System.out.println("\n方式三-2");
        for (Iterator<Integer> i = tempMap.values().iterator(); i.hasNext(); ) {
            Integer v = i.next();//循环输出value
            System.out.print("   v :" + v+"\t");
        }
        System.out.println("\n方式四-1");
        //方式四 keySet foreach 遍历
        for (String k : tempMap.keySet()) {
            System.out.print("  k :" + k + " ,v :" + tempMap.get(k)+"\t");

        }
        System.out.println("\n方式四-2");
        for (Integer v : tempMap.values()) {
            System.out.print("  v :" + v+"\t");
        }
        tempMap.values();


    }

    private static void hash_equal() {
        Map<HashMapTest, Integer> map = new HashMap<HashMapTest, Integer>();
        HashMapTest instance = new HashMapTest(1);
        System.out.println("instance.hashcode:" + instance.hashCode());
        map.put(instance, 1);

        HashMapTest newInstance = new HashMapTest(1);
        map.put(newInstance, 2);
        System.out.println("newInstance.hashcode:" + newInstance.hashCode());

        HashMapTest hashMapTest = new HashMapTest(2);
        map.put(hashMapTest, 3);
        System.out.println("hashMapTest hashcode" + hashMapTest.hashCode());
        Integer value = map.get(instance);

        Integer value1 = map.get(newInstance);
        Integer value2 = map.get(hashMapTest);

        if (value != null) {
            System.out.println(value);
        } else {
            /*没有重写equals和hashCode方法   运行结果为空*/
            System.out.println("value is null");
        }
        if (value1 != null) {
            System.out.println(value1);
        } else {
            /*没有重写equals和hashCode方法   运行结果为空*/
            System.out.println("value is null");
        }

        if (value2 != null) {
            System.out.println(value2);
        } else {
            /*没有重写equals和hashCode方法   运行结果为空*/
            System.out.println("value is null");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HashMapTest that = (HashMapTest) o;

        return a == that.a;
    }

    @Override
    public int hashCode() {
        return a;
    }
}
