package com.online.exam.dm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDM {

	private String stuId;
	private String stuNum;
	private String name;
	private String grade;
	private String loginPassword;
	private Date createTime;
	private  List<ScoreDM> scoreList = new ArrayList<ScoreDM>();
	
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getStuNum() {
		return stuNum;
	}
	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public List<ScoreDM> getScoreList() {
		return scoreList;
	}
	public void setScoreList(List<ScoreDM> scoreList) {
		this.scoreList = scoreList;
	}
	
	
}
