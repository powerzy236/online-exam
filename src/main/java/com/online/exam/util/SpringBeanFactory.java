/**
 * 
 */
package com.online.exam.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * 
 * @作者： 朱伟亮
 * @创建时间：2013-4-10 下午5:26:15
 */
public class SpringBeanFactory implements BeanFactoryAware {

	private static BeanFactory beanFactory = null;

	@Override
	public void setBeanFactory(BeanFactory bf) throws BeansException {
		SpringBeanFactory.beanFactory = bf;
	}

	public static Object getBean(String beanName) {
		return beanFactory.getBean(beanName);
	}

}
