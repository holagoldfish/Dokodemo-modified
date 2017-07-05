package com.zendaimoney.Dokodemo.html;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class NullObjectPageModel {
	private static Map<Class<?>, Object> caches = new HashMap<Class<?>, Object>();

	@SuppressWarnings("unchecked")
	public static <T> T getInstance(Class<T> type) {
		if (caches.containsKey(type)) {
			return (T) caches.get(type);
		}
		try {
			Constructor<T> constructor = type.getConstructor(String.class,
					PageModel.class, String.class, String.class);
			T newInstance = constructor.newInstance(null, null, null, null);
			caches.put((Class<?>) type, newInstance);
			return newInstance;
		} catch (Exception ex) {
			throw new RuntimeException(type.getName() + "必须含有双参构造函数", ex);
		}
	}
}
