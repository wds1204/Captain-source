package com.captain.wds.set;

import android.support.annotation.NonNull;

/**
 * Created by wudongsheng on 2018/6/3.
 */
// 实现Comparable接口
public class Student implements Comparable<Student> {

	public Student(String name, int score, int age) {
		this.name = name;
		this.score = score;
		this.age = age;
	}

	public String	name;

	public int		score;

	public int		age;

	@Override public String toString() {
		return "Student{" + "name='" + name + '\'' + ", score=" + score + ", age=" + age + '}';
	}

	// 年龄排序
	@Override public int compareTo(@NonNull Student o) {
		return this.age - o.age;
	}
}
