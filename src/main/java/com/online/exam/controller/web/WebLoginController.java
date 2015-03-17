package com.online.exam.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.online.exam.controller.BaseController;
import com.online.exam.dm.MessageMD;
import com.online.exam.dm.UserDM;
import com.online.exam.util.ObjectTools;
import com.online.exam.util.SessionUtil;

@Controller
@RequestMapping("/web")
public class WebLoginController extends BaseController{

	@RequestMapping("/login")
	public String toLogin(HttpServletRequest req,Model m){
		
		
		return null;
	}
	
	@RequestMapping("/login")
	public void login(HttpServletRequest req, HttpServletResponse res , Model m ,UserDM user){
		ObjectTools.show(user);
		MessageMD message = new MessageMD();
		if(user != null){
			if("admin".equals(user.getUserName())){
				if("123456".equals(user.getPassword())){
					SessionUtil.setManageLoginUser(req, user);
					message.setStatus(0);
					message.setMessage("获取成功！");
				}else{
					message.setStatus(1);
					message.setMessage("密码错误，请重新输入!");
				}
			}else{
				message.setStatus(1);
				message.setMessage("用户名不正确，请重新输入！");
			}
		}else{
			message.setStatus(1);
			message.setMessage("非法请求！");
		}
		this.writeJson(JSON.toJSONString(message), res);
	}
	
}
