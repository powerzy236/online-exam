/**
 * 
 */
package com.online.exam.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @作者： 朱伟亮
 * @创建时间：2013-5-17 下午3:00:37
 */
public interface IBaseDao<T> {
	/**
	 * 保存一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public void save(T o);

	/**
	 * 清除缓存 持久对象变成脱管对象
	 */
	public void evict(T o);

	/**
	 * 更新一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public void update(T o);

	/**
	 * 保存或更新对象
	 * 
	 * @param o
	 *            对象
	 */
	public void saveOrUpdate(T o);

	/**
	 * 合并一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public void merge(T o);

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public void delete(T o);

	/**
	 * 查找对象集合
	 * 
	 * @param hql
	 * @param param
	 * @return List<T>
	 */
	public List<T> find(String hql, Object... param);

	/**
	 * 查找对象集合
	 * 
	 * @param hql
	 * @param param
	 * @return List<T>
	 */
	public List<T> find(String hql, List<Object> param);

	/**
	 * 查找对象集合,带分页
	 * 
	 * @param hql
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页显示记录数
	 * @param param
	 * @return 分页后的List<T>
	 */
	public List<T> find(String hql, int page, int rows, List<Object> param);

	/**
	 * 查找对象集合,带分页
	 * 
	 * @param hql
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页显示记录数
	 * @param param
	 * @return 分页后的List<T>
	 */
	public List<T> find(String hql, int page, int rows, Object... param);

	/**
	 * 获得一个对象
	 * 
	 * @param c
	 *            对象类型
	 * @param id
	 * @return Object
	 */
	public T only(Class<T> c, Serializable id);

	/**
	 * 获得一个对象
	 * 
	 * @param hql
	 * @param param
	 * @return Object
	 */
	public T only(String hql, Object... param);

	/**
	 * 获得一个对象
	 * 
	 * @param hql
	 * @param param
	 * @return Object
	 */
	public T only(String hql, List<Object> param);

	/**
	 * select count(*) from 类
	 * 
	 * @param hql
	 * @param param
	 * @return Long
	 */
	public Long count(String hql, Object... param);

	/**
	 * select count(*) from 类
	 * 
	 * @param hql
	 * @param param
	 * @return Long
	 */
	public Long count(String hql, List<Object> param);

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @return 相应数目
	 */
	public Integer executeHql(String hql);

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Integer executeHql(String hql, Object... param);

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Integer executeHql(String hql, List<Object> param);


}
