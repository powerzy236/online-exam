package com.online.exam.controller.manage;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage/view")
public class ManageExamineeController {

	private static Logger log = Logger.getLogger(ManageExamineeController.class);
	
	private static String path = "/manage/examinee";
	
	@RequestMapping("/examineeList")
	public String examineeList(HttpServletRequest req,Model m){
		
		return path + "/list";
	}
	
	@RequestMapping("/examineeToAdd")
	public String examineeToAdd(){
		
		return path + "/add";
	}
	
	@RequestMapping("/examineeAdd")
	public String examineeAdd(HttpServletRequest req,Model m){
		
		return path + "/add";
	}
	
	@RequestMapping("/examineeToEdit")
	public String examineeToEdit(){
		
		return path + "/edit";
	}
	
	@RequestMapping("/examineeEdit")
	public String examineeEdit(HttpServletRequest req,Model m){
		
		return path + "/edit";
	}
}
