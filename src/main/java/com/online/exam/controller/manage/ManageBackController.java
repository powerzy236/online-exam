package com.online.exam.controller.manage;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage/view")
public class ManageBackController {

	private static String path = "/manage";
	
	@RequestMapping("/index")
	public String index(HttpServletRequest req,Model m){
		
		return path + "/index";
	}
	
	@RequestMapping("/form")
	public String form(HttpServletRequest req,Model m){
		
		return path + "/form";
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest req,Model m){
		
		return path + "/list";
	}
}
