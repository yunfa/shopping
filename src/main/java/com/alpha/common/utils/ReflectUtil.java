package com.alpha.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * 反射类
 * 
 * @author Li Yunfa
 * @date 2017年6月24日
 */
@SuppressWarnings("rawtypes")
public class ReflectUtil {

	private static Logger logger = LoggerFactory.getLogger(ReflectUtil.class);

	private static int count = 0;

	private final static int LIST_SIZE = 5;

	private final static int LAYER = 3;

	private final static String DEFAULT_STRING_VALUE = "R支付项目";

	private final static Random rd = new Random();

	private ReflectUtil() {
	}

	public static Object setObject(Class class1) {
		if (class1 == String.class) {
			return DEFAULT_STRING_VALUE + new Random().nextInt(100);
		} else if (class1 == Integer.class || class1 == int.class) {
			return new Random().nextInt(100) + 1;
		} else if (class1 == Date.class) {
			return new Date();
		} else if (class1 == Boolean.class || class1 == boolean.class) {
			return rd.nextBoolean();
		} else if (class1 == Double.class || class1 == double.class) {
			return rd.nextDouble();
		} else if (class1 == Float.class || class1 == float.class) {
			return rd.nextFloat() * 100;
		} else if (class1 == Long.class || class1 == long.class) {
			return rd.nextInt(100) + 1;
		} else {
			Object object = null;
			try {
				object = class1.newInstance();
				Field[] fields = class1.getDeclaredFields();
				setFiled(class1, object, fields);
			} catch (Exception e) {
				logger.error("反射机制设置对象值出错！", e);
			}
			return object;
		}
	}

	@SuppressWarnings("unchecked")
	private static void setFiled(Class class1, Object object, Field[] fields) throws Exception {
		for (Field field : fields) {
			if ("serialVersionUID".equals(field.getName())) {
				continue;
			}
			field.setAccessible(true);
			if (field.getType() == class1) {
				count++;
			}
			boolean flag;
			try {
				String methodName = "set" + field.getName().substring(0, 1).toUpperCase()
						+ field.getName().substring(1);
				class1.getMethod(methodName, field.getType());
				flag = true;
			} catch (Exception e) {
				flag = false;
				logger.debug("属性--" + field.getName() + "--未定义set方法！", e);
			}
			if (flag) {
				Class class2 = field.getType();
				if (class2 == String.class) {
					field.set(object, DEFAULT_STRING_VALUE + new Random().nextInt(100));
				} else if (class2 == Integer.class || class2 == int.class) {
					setInt(object, field);
				} else if (class2 == Date.class) {
					field.set(object, new Date());
				} else if (class2 == Boolean.class || class2 == boolean.class) {
					field.set(object, rd.nextBoolean());
				} else if (class2 == Double.class || class2 == double.class) {
					field.set(object, rd.nextDouble());
				} else if (class2 == Float.class || class2 == float.class) {
					field.set(object, rd.nextFloat() * 100);
				} else if (class2 == Long.class || class2 == long.class) {
					field.set(object, rd.nextInt(100) + 1l);
				} else if (class2 == List.class) {
					setList(class1, object, field);
				} else if (class2 == Set.class) {
					setSet(class1, object, field);
				} else if (class2 == Map.class) {
					setMap(class1, object, field);
				} else {
					field.set(object, setObject(field.getType()));
				}
			}
		}
	}

	private static void setMap(Class class1, Object object, Field field) throws Exception {
		ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
		Class class3 = (Class) parameterizedType.getActualTypeArguments()[0];
		Class class4 = (Class) parameterizedType.getActualTypeArguments()[1];
		Map<Object, Object> map = Maps.newHashMap();
		if (class3 == class1) {
			count++;
			if (count < LAYER) {
				for (int a = 0; a < LIST_SIZE; a++) {
					map.put(setObject(class3), setObject(class4));
				}
				field.set(object, map);
				count = 0;
			}
		}
	}

	private static void setSet(Class class1, Object object, Field field) throws Exception {
		ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
		Class class3 = (Class) parameterizedType.getActualTypeArguments()[0];
		Set<Object> set = Sets.newHashSet();
		if (class3 == class1) {
			count++;
			if (count < LAYER) {
				for (int a = 0; a < LIST_SIZE; a++) {
					set.add(setObject(class3));
				}
				field.set(object, set);
				count = 0;
			}
		}
	}

	private static void setList(Class class1, Object object, Field field) throws Exception {
		ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
		Class class3 = (Class) parameterizedType.getActualTypeArguments()[0];
		List<Object> list = Lists.newArrayList();
		if (class3 == class1) {
			count++;
			if (count < LAYER) {
				for (int a = 0; a < LIST_SIZE; a++) {
					list.add(setObject(class3));
				}
				field.set(object, list);
				count = 0;
			}
		}
	}

	private static void setInt(Object object, Field field) throws IllegalAccessException {
		field.set(object, new Random().nextInt(1000) + 1);
	}
}
