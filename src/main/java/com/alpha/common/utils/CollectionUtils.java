package com.alpha.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * 集合类工具类
 *
 * @author Li Yunfa
 * @since 1.0
 * @date 2015年11月15日
 *
 */
public final class CollectionUtils {

	private static Logger logger = LoggerFactory.getLogger(CollectionUtils.class);

	private CollectionUtils() {
	}

	/**
	 * 往map中添加值
	 * 
	 * @param map 经纪人业绩map
	 * @param agentId 经纪人ID
	 * @param dto 业绩列表DTO
	 */
	public static <T> void putList(LinkedHashMap<Integer, List<T>> map, int objId, T dto) {
		List<T> list = map.get(objId);
		if (list != null) {
			list.add(dto);
		} else {
			map.put(objId, Lists.newArrayList(dto));
		}
	}

	/**
	 * 判断集合是否为空<br>
	 *
	 * @param collection
	 * @return boolean 如果集合null或者为空则返回true，否则返回false
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

	/**
	 * 判断集合是否非空
	 *
	 * @param collection
	 * @return boolean 如果集合null或者为空则返回false，否则返回true
	 */
	public static boolean isNotEmpty(Collection<?> collection) {
		return !isEmpty(collection);
	}

	/**
	 * 将set转换为list
	 *
	 * @param set 集合
	 * @return
	 */
	public static List<Integer> convertList(Set<Integer> set) {
		List<Integer> list = Lists.newArrayList();
		if (isEmpty(set)) {
			return list;
		}
		Iterator<Integer> iterator = set.iterator();
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}
		return list;
	}

	public static List<Long> convertListLong(Set<Long> set) {
		List<Long> list = Lists.newArrayList();
		if (isEmpty(set)) {
			return list;
		}
		Iterator<Long> iterator = set.iterator();
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}
		return list;
	}

	/**
	 * 将list转换为set
	 *
	 * @param list 集合
	 * @return
	 */
	public static Set<Integer> convertSet(List<Integer> list) {
		Set<Integer> set = Sets.newHashSet();
		if (isEmpty(list)) {
			return set;
		}
		Iterator<Integer> iterator = list.iterator();
		while (iterator.hasNext()) {
			set.add(iterator.next());
		}
		return set;
	}

	/**
	 * 将list转换为set
	 *
	 * @param list 集合
	 * @return
	 */
	public static Set<Long> convertSetLong(List<Long> list) {
		Set<Long> set = Sets.newHashSet();
		if (isEmpty(list)) {
			return set;
		}
		Iterator<Long> iterator = list.iterator();
		while (iterator.hasNext()) {
			set.add(iterator.next());
		}
		return set;
	}

	/**
	 * 将字符串转换到整数列表
	 *
	 * @param str 将要转换的字符串
	 * @param pattern 表达式
	 * @return 数字列表
	 */
	public static List<Integer> splitIntList(String str, String pattern) {
		List<Integer> list = Lists.newArrayList();
		return (List<Integer>) splitMethod(str, pattern, list);
	}

	/**
	 * 将字符串转换到整数列表
	 *
	 * @param str 将要转换的字符串
	 * @param pattern 表达式
	 * @return 数字列表
	 */
	public static Set<Integer> splitIntSet(String str, String pattern) {
		Set<Integer> list = Sets.newHashSet();
		return (Set<Integer>) splitMethod(str, pattern, list);
	}

	private static Collection<Integer> splitMethod(String str, String pattern, Collection<Integer> list) {
		if (StringUtil.isEmpty(str)) {
			return list;
		}
		String[] strs = str.split(pattern);
		if (strs == null || strs.length < 1) {
			return list;
		}
		for (String s : strs) {
			if (StringUtil.isNotEmpty(s)) {
				try {
					list.add(Integer.parseInt(s));
				} catch (Exception e) {
					logger.error("数字转换出错:{}无法转换成数字.", e);
				}
			}
		}
		return list;
	}

	/**
	 * 将字符串转换到整数列表
	 *
	 * @param str 将要转换的字符串
	 * @param pattern 表达式
	 * @return 数字列表
	 */
	public static List<Integer> splitIntListNot0(String str, String pattern) {
		List<Integer> list = new ArrayList<>();
		return (List<Integer>) splitNot0Method(str, pattern, list);
	}

	/**
	 * 将字符串转换到整数列表
	 *
	 * @param str 将要转换的字符串
	 * @param pattern 表达式
	 * @return 数字列表
	 */
	public static Set<Integer> splitIntSetNot0(String str, String pattern) {
		Set<Integer> list = Sets.newHashSet();
		return (Set<Integer>) splitNot0Method(str, pattern, list);
	}

	private static Collection<Integer> splitNot0Method(String str, String pattern, Collection<Integer> list) {
		if (StringUtil.isEmpty(str)) {
			return list;
		}
		String[] strs = str.split(pattern);
		if (strs == null || strs.length < 1) {
			return list;
		}
		for (String s : strs) {
			if (StringUtil.isNotEmpty(s) && !"0".equals(s)) {
				try {
					list.add(Integer.parseInt(s));
				} catch (Exception e) {
					logger.error("数字转换出错:{}无法转换成数字.", e);
				}
			}
		}
		return list;
	}

	/**
	 * set转换为string
	 *
	 * @param set 列表
	 * @return
	 */
	public static String convertString(Set<Integer> set) {
		StringBuilder sb = new StringBuilder();
		if (set != null) {
			sb.append(",");
			for (Integer i : set) {
				sb.append(i).append(",");
			}
		}
		return sb.toString();
	}

	/**
	 * list转换为string
	 *
	 * @param list 列表
	 * @return
	 */
	public static String convertString(List<Integer> list) {
		StringBuilder sb = new StringBuilder();
		if (list != null) {
			sb.append(",");
			for (Integer i : list) {
				sb.append(i).append(",");
			}
		}
		return sb.toString();
	}

	public static String convertString(Collection<String> collection, String pattern) {
		StringBuilder builder = new StringBuilder();
		if (CollectionUtils.isNotEmpty(collection)) {
			collection.forEach(user -> {
				if (StringUtil.isNotEmpty(user)) {
					builder.append(user).append(pattern);
				}
			});
		}
		return builder.toString();
	}

	/**
	 * set转换为string
	 *
	 * @param set 列表
	 * @return
	 */
	public static String convertStringLong(Set<Long> set) {
		StringBuilder sb = new StringBuilder();
		if (set != null) {
			sb.append(",");
			for (Long i : set) {
				sb.append(i).append(",");
			}
		}
		return sb.toString();
	}

	/**
	 * set转换为string
	 *
	 * @param list 列表
	 * @return
	 */
	public static String convertStringLong(List<Long> list) {
		StringBuilder sb = new StringBuilder();
		if (CollectionUtils.isNotEmpty(list)) {
			sb.append(",");
			for (Long i : list) {
				sb.append(i).append(",");
			}
		}
		return sb.toString();
	}

	public static String convertStringNoPrefix(Set<Integer> set) {
		String str = convertString(set);
		if (str != null) {
			return str.substring(1, str.length() - 1);
		}
		return "";
	}

	public static String convertStringNoPrefix(List<Integer> list) {
		String str = convertString(list);
		if (str != null) {
			return str.substring(1, str.length() - 1);
		}
		return "";
	}

	public static String convertStringNoPrefixLong(Set<Long> set) {
		String str = convertStringLong(set);
		if (str != null) {
			return str.substring(1, str.length() - 1);
		}
		return "";
	}

	public static String convertStringNoPrefixLong(List<Long> list) {
		String str = convertStringLong(list);
		if (StringUtil.isNotEmpty(str)) {
			return str.substring(1, str.length() - 1);
		}
		return "";
	}

	/**
	 * 删除0元素
	 *
	 * @param clt
	 */
	public static void removeZeroItem(Collection<Integer> clt) {
		if (isEmpty(clt)) {
			return;
		}
		for (Integer obj : clt) {
			if (0 == obj) {
				clt.remove(obj);
				return;
			}
		}
	}

	public static void removeZeroItemLong(Collection<Long> clt) {
		if (isEmpty(clt)) {
			return;
		}
		for (Long obj : clt) {
			if (0 == obj) {
				clt.remove(obj);
				return;
			}
		}
	}

	/**
	 * 将字符串转换到字符串列表
	 *
	 * @param str 将要转换的字符串
	 * @param pattern 表达式
	 * @return 字符串列表
	 */
	public static Set<String> splitStringSet(String str, String pattern) {
		Set<String> list = Sets.newHashSet();
		if (StringUtil.isEmpty(str)) {
			return list;
		}
		String[] strs = str.split(pattern);
		if (strs != null && strs.length > 0) {
			for (String s : strs) {
				if (StringUtil.isNotEmpty(s)) {
					list.add(s);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		String test = "a@.sdf,ahdg@jkds.nfds,";
		Set<String> set = splitStringSet(test, ",");
		for (String string : set) {
			logger.info(string);
		}
	}
}
