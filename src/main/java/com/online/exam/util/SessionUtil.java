package com.online.exam.util;

import javax.servlet.http.HttpServletRequest;

import com.online.exam.dm.UserDM;

public class SessionUtil {

	public static  String MANAGE_LOGIN_USER = "manageLoginUser";
	
	public static  String WEB_LOGIN_USER = "webLoginUser";
	
	/**
	 * 设置和获取manage用户
	 * @author 黄韦谋
	 * @crate 2015年2月16日  上午9:58:37
	 * @param req
	 * @param user
	 */
	public static void setManageLoginUser(HttpServletRequest req,UserDM user){
		req.getSession().setAttribute(MANAGE_LOGIN_USER, user);
	}
	
	public static UserDM getManageLoginUser(HttpServletRequest req){
		return (UserDM) req.getSession().getAttribute(MANAGE_LOGIN_USER);
	}
	
	/**
	 * 设置和获取web用户
	 * @author 黄韦谋
	 * @crate 2015年2月16日  上午9:58:15
	 * @param req
	 * @param user
	 */
	public static void setWebLoginUser(HttpServletRequest req,UserDM user){
		req.getSession().setAttribute(WEB_LOGIN_USER, user);
	}
	
	public static UserDM getWebLoginUser(HttpServletRequest req){
		return (UserDM) req.getSession().getAttribute(WEB_LOGIN_USER);
	}
}
