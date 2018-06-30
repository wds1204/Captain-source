package com.captain.wds.degsinpattern.singleton;

/**
 * Created by wds on 2018/4/4.
 */

/**
 * 单例模式的七中实现方式
 */
public class Singleton {

	private static Singleton		instance;

	/* 饿汉单例模式 */

	private static final Singleton	singleton	= new Singleton();

	public static Singleton getSingleton() {
		return singleton;
	}

	private Singleton() {}

	/**
	 * 懒汉式实现单例
	 * <p>产生不必要的开销，即便是已经实例化了Singleton，每次都会进行同步</p>
	 * @return
	 */
	public static synchronized Singleton getInstance1() {

		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}

	/**
	 * Double Check Lock实现单例
	 * <P>单例模式</P>
	 * @return
	 */
	public static Singleton getInstance() {

		if (instance == null) {//判空，避免不必要的同步操作
			synchronized (Singleton.class) {
				if (instance == null) {//instance 为null时创建实例对象
					instance = new Singleton();
				}
			}
		}
		return instance;
	}

	/**
	 * 静态内部类单例模式
	 */
	public static Singleton getInstance2() {
		return SingletonHolder.instance;
	}

	private static class SingletonHolder {

		private static final Singleton instance = new Singleton();

	}

}
