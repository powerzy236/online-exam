package com.online.exam.intercetor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.online.exam.dm.UserDM;
import com.online.exam.util.SessionUtil;

public class ManageLoginIntercetor implements HandlerInterceptor{

	private static String LOGIN_URL = "/manage/toLogin";
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2) throws Exception {
		UserDM loginUser = SessionUtil.getManageLoginUser(req);
		if(loginUser != null){
			return true;
		}else{
			res.sendRedirect(req.getContextPath() +LOGIN_URL );
		}
		return false;
	}
	

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
