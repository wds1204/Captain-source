package com.captain.wds.degsinpattern.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wds on 2018/4/4.
 */

/**
 * 使用容器方式实现单例模式
 */
public class SingletonManger {

	private static Map<String, Object> objectMap = new HashMap<>();

	private SingletonManger() {}


	public static void registerService(String key, Object instance) {
		if (!objectMap.containsKey(key)) {
			objectMap.put(key, instance);
		}
	}

	public static Object getService(String key) {
		return objectMap.get(key);
	}
}
