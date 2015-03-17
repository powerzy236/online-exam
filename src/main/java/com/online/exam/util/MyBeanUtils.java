package com.online.exam.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MyBeanUtils  {


	/**
	 * 传递变量，更新用，source对象字段值为null则不赋值
	 * 要求source与target字段值名称和类型相同
	 * 
	 * @作者：朱伟亮 
	 * @创建时间：2013-2-4 下午4:58:55
	 * 
	 * @param source
	 * @param target
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void copyNotNullProperties(Object source, Object target) {
		Method[] sMethods = source.getClass().getMethods();
		Method[] tMethods = target.getClass().getMethods();
		Map<String, Method> tMap = new HashMap<String, Method>();
		for (int i = 0; i < tMethods.length; i++) {
			tMap.put(tMethods[i].getName(), tMethods[i]);
		}
		String methodName = "";
		Method tMethod = null;
		for (int i = 0; i < sMethods.length; i++) {
			if (!sMethods[i].getName().startsWith("get")) {
				continue;
			}
			methodName = sMethods[i].getName();
//			log.info("name:" + methodName);
			Object sValue;
			try {
				sValue = sMethods[i].invoke(source, new Object[0]);
				if (null == sValue) {
					continue;
				}
				tMethod = tMap.get(methodName);
				if (null == tMethod) {
					continue;
				}
				Object tValue = tMethod.invoke(target, new Object[0]);
				if (sValue.equals(tValue)) {
					continue;
				}
				String fieldName = methodName.substring(3);
				Method tarMet = tMap.get("set" + fieldName);
				if(null == tarMet){
					continue;
				}
				tarMet.invoke(target, sMethods[i].invoke(source, new Object[0]));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				continue;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				continue;
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				continue;
			}
		}
	}
	/**
	 * 验证List
	 * 
	 * @作者：朱伟亮
	 * @创建时间：2013-2-17 下午5:33:57
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isValid(List list){
		if(null == list || 0 == list.size()){
			return false;
		}
		else{
			return true;
		}
	}
	/**
	 * 今天日期，转字符串
	 * 
	 * @作者：朱伟亮
	 * @创建时间：2013-2-19 上午8:56:44
	 * @param format
	 * @return
	 */
	public static String todayFormat(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	/**
	 * 二进制比对，source包含target值的返回true，不含的返回false
	 * 
	 * @作者：朱伟亮
	 * @创建时间：2013-2-25 下午2:03:03
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean binaryMatch(String source,String target){
		int sourceLength = source.length();
		int targetLength = target.length();
		if (targetLength > sourceLength) {
			return false;
		}
		int index = sourceLength - targetLength;
		String mbit = source.substring(index, index + 1);
		String pbit = target.substring(0,1);
		if(pbit.equals(mbit)){
			return true;
		}
		return false;
	}
}
