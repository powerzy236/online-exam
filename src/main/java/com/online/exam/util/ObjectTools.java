package com.online.exam.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * object工具类
 * 
 */
public class ObjectTools {

	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private static DecimalFormat df = new DecimalFormat("0.00");
	
	private static String APP_KEY_NAME="appKey";
	
	/**
	 * 把object对象的属性名和值转换成map对象
	 * 
	 * @param o
	 *            要转换的对象
	 * @return 转换后的map对象
	 * 
	 */
	public static Map<String, String> object2Map(Object o) {
		Map<String, String> map = new HashMap<String, String>();
		Class<?> c = o.getClass();
		Method[] methods = c.getMethods();
		for (Method m : methods) {
			String mName = m.getName();
			if (!mName.startsWith("get") || "getClass".equals(mName)
					|| "get".equals(mName)) {
				continue;
			} else {
				String key = mName.replace("get", "");
				key = key.substring(0, 1).toLowerCase() + key.substring(1);
				Object valueObj;
				try {
					valueObj = m.invoke(o);
					if (null == valueObj) {
						continue;
					}
					String value = m.invoke(o).toString();
					map.put(key, value);
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					System.out.println("赋值" + "");
				}
			}
		}
		return map;
	}
	
	/**
	 * 签名 把object对象的属性名和值转换成map对象
	 * 
	 * @author 朱伟亮
	 * @create 2014-5-29 下午5:34:10
	 * @param o
	 *            要转换的对象
	 * @return 转换后的map对象
	 * 
	 */
	public static Map<String, String> object2MapSign(Object o,String appKey) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(APP_KEY_NAME, appKey);
		Class<?> c = o.getClass();
		Method[] methods = c.getMethods();
		for (Method m : methods) {
			String mName = m.getName();
			if (!mName.startsWith("get") || "getClass".equals(mName)) {
				continue;
			} else {
				String key = mName.replace("get", "");
				key = key.substring(0, 1).toLowerCase() + key.substring(1);
				try {
					Object valueObj = m.invoke(o);
					if (null == valueObj) {
						continue;
					}
					String value = "";
					Object objvalue = m.invoke(o);
					Class<?> objvalueClass = objvalue.getClass();
					if (Date.class.equals(objvalueClass)) {
						value = sdf.format(objvalue);
					} else if (BigDecimal.class.equals(objvalueClass)){
						value = df.format(objvalue);
					}else {
						value = m.invoke(o).toString();
					}
					map.put(key, value);
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}
		return map;
	}

	/**
	 * 输出object对象属性
	 * 
	 * @param o
	 *            要输出属性的对象
	 */
	public static void show(Object o) {
		Class<?> clazz = o.getClass();
		Method[] methods = clazz.getMethods();
		System.out.println("");
		System.out.println("");
		System.out.println("-- " + clazz.getName() + "的属性值 --");
		for (Method method : methods) {
			String methodName = method.getName();
			if (!methodName.startsWith("get") || "getClass".equals(methodName)
					|| "get".equals(methodName)) {
				continue;
			} else {
				String fieldName = method.getName().replaceFirst("get", "");
				fieldName = fieldName.substring(0, 1).toLowerCase()
						+ fieldName.substring(1);
				try {
					Object valueObject = method.invoke(o);
					if (null == valueObject) {
						continue;
					} else if (valueObject.getClass().isArray()) {
						List<?> list = Arrays.asList(valueObject);
						for (Object ol : list) {
							show(ol);
						}
					} else if (Collection.class.isAssignableFrom(valueObject
							.getClass())) {
						Collection<?> valueCollection = (Collection<?>) valueObject;
						List<?> list = new ArrayList(valueCollection);
						for (Object ol : list) {
							show(ol);
						}
					} else {
						String fieldValue = valueObject.toString();
						System.out.println("'" + fieldName + "':'" + fieldValue
								+ "'");
					}
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					System.out.println("输出：" + fieldName + "的值时出错");
				}
			}
		}
		System.out.println("-- --");
		System.out.println("");
		System.out.println("");
	}

	/**
	 * 通过反射获取某一对象指定的属性值
	 * @author 黄韦谋
	 * @crate 2014年8月1日  上午9:13:53
	 * @param obj 对象
	 * @param methodName 属性方法名 （get方法）
	 * @return String 属性值
	 */
	public static String getValueByMethod(Object obj, String methodName) {
		try {
			Method method = obj.getClass().getMethod(methodName);
			return  method.invoke(obj).toString();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过反射为某一对象的属性进行赋值操作
	 * @author 黄韦谋
	 * @crate 2014年8月1日  上午9:17:56
	 * @param obj 对象
	 * @param methodName 属性的方法名称 (set方法名)
	 * @param value 设置内容
	 * @param parameterTypes  属性的数据类型
	 * @return Object 返回已赋值对象
	 */
	public static Object setValueByMethod(Object obj, String methodName,String value, Class<?> parameterTypes) {
		Class<?> clas = obj.getClass().getSuperclass();
		Method methods;
		try {
			methods = clas.getMethod(methodName, parameterTypes);
			methods.invoke(obj, value);
		}  catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return obj;
	}

}
