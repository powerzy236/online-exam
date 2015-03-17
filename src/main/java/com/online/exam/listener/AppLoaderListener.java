/**
 * 
 */
package com.online.exam.listener;

import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.online.exam.util.AppConstants;

/**
 * 项目启动加载项
 * 
 * @author： 朱伟亮
 * @create 2014-3-5 下午1:09:47
 */
public class AppLoaderListener implements ServletContextListener {
	private Logger log = Logger.getLogger(AppLoaderListener.class);

	private static ResourceBundle link = ResourceBundle.getBundle("link");

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		// ABSOLUTE_PATH
		AppConstants.ABSOLUTE_PATH = sc.getRealPath("/").replace("\\",
				"/");
		log.info("AppConstants.ABSOLUTE_PATH:"
				+ AppConstants.ABSOLUTE_PATH);
		// CTX
		AppConstants.CTX = sc.getContextPath();
		sc.setAttribute("ctx", AppConstants.CTX);
		log.info("AppConstants.CTX:" + AppConstants.CTX);
		// STC
		try {
			AppConstants.STC = link.getString("stc");
		} catch (Exception e) {
			AppConstants.STC = AppConstants.CTX;
		}
		sc.setAttribute("stc", AppConstants.STC);
		log.info("AppConstants.STC:" + AppConstants.STC);
		
		String realPath = sce.getServletContext().getRealPath("/");
		realPath = realPath.replace("\\", "/");
		AppConstants.ABSOLUTE_PATH = realPath;
		log.info("AppConstant.ABSOLUTE_PATH:" + AppConstants.ABSOLUTE_PATH);
		String contextPath = sc.getContextPath();
		AppConstants.CONTEXT_PATH = contextPath;
		log.info("AppConstant.CONTEXT_PATH:" + AppConstants.CONTEXT_PATH);
		sc.setAttribute("ctx", contextPath);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}
}
