package com.online.exam.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class BaseController {
	
	private Logger log = Logger.getLogger(BaseController.class);

	/**
	 * ajax返回json格式数据
	 * 
	 * @author 朱伟亮
	 * @create 2013-8-1 下午4:57:26
	 * @param data
	 * @param res
	 */
	protected void writeJson(String data, HttpServletResponse res) {
		writeJson(data, res, "utf-8");
	}

	protected void writeJson(String data, HttpServletResponse res,
			String charset) {
		res.setContentType("text/html; charset=" + charset.toLowerCase());
		PrintWriter pw = null;
		try {
			pw = res.getWriter();
			pw.print(data);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			if (null != pw) {
				pw.close();
			}
		}
	}


}
