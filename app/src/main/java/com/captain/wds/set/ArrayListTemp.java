package com.captain.wds.set;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wds on 2018-5-8.
 */

public class ArrayListTemp {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add(1, "2");
//
//        list.set(1, "3");
//
//        list.contains("1");
//
//        list.get(1);
//        list.remove("1");
//        list.remove(1);
//        list.trimToSize();

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String string = iterator.next();
            if (string.equals("1")) {
//                list.remove(string);

                iterator.remove();
            }
        }
        Object[] objects = list.toArray();
        for (int i = 0; i < objects.length; i++) {

            System.out.println(""+objects[i]);

        }


    }

    //在末端添加
    public static void makeList1(List<Integer> list,int N) {
        list.clear();
        for(int i = 0; i <N ; i++) {
            list.add(i);
        }
    }
    //不管是
    //在前端添加

    /**<p>
     *    在前端添加
     *    对Linklist的运行时间O(N)
     *    对于Arraylist其运行时间则是O(N2)
     * </p>
     *
     * @param list
     * @param N
     */

    public static void makeList2(List<Integer> list,int N) {
        list.clear();
        for(int i = 0; i <N ; i++) {
            list.add(0,i);
        }
    }


}
