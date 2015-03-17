package com.online.exam.controller.manage;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage/view")
public class ManageItemController {

	private static Logger log = Logger.getLogger(ManageItemController.class);
	
	private static String path = "/manage/item";
	
	@RequestMapping("/itemList")
	public String itemList(HttpServletRequest req,Model m){
		
		return path + "/list";
	}
	
	@RequestMapping("/itemToAdd")
	public String itemToAdd(){
		
		return path + "/add";
	}
	
	@RequestMapping("/itemAdd")
	public String itemAdd(HttpServletRequest req,Model m){
		
		return path + "/add";
	}
	
	@RequestMapping("/itemToEdit")
	public String itemToEdit(){
		
		return path + "/edit";
	}
	
	@RequestMapping("/itemEdit")
	public String itemEdit(HttpServletRequest req,Model m){
		
		return path + "/edit";
	}
}
