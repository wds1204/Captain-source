package com.captain.wds.set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by wudongsheng on 2018/6/3.
 */

public class SortList {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("张三", 98, 16));
        students.add(new Student("李四",60,15));
        students.add(new Student("王五", 59, 17));

        System.out.println("===============年龄排序===============");
        Collections.sort(students);//年龄排序
        for (Student student : students) {
            System.out.println(student.toString());
            
        }
        System.out.println("==============分数排序==============");
        
        Collections.sort(students, new SortScore());

        for (Student student : students) {
            System.out.println(student.toString());
            
        }
        System.out.println("==============名字排序==============");

        Collections.sort(students, new SortName());

        for (Student student : students) {
            System.out.println(student.toString());

        }
    }
    //分数排序
    public  static class SortScore implements Comparator<Student> {

        @Override public int compare(Student o1, Student o2) {
            return o1.score - o2.score;
        }
    }
    //名字排序
    public static class SortName implements Comparator<Student> {

        @Override public int compare(Student o1, Student o2) {
            return o1.name.compareTo(o2.name);
        }
    }
}
