package com.online.exam.dm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ExamPaperDM {

	private String examPaperNum;
	private Integer totalScore;
	private String status;
	private Integer number;
	private Date createTime;
	private List<ScoreDM> gradeList = new ArrayList<ScoreDM>();
	private List<ExamPaperItemDM> examList = new ArrayList<ExamPaperItemDM>();
	
	public String getExamPaperNum() {
		return examPaperNum;
	}
	public void setExamPaperNum(String examPaperNum) {
		this.examPaperNum = examPaperNum;
	}
	public Integer getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public List<ScoreDM> getGradeList() {
		return gradeList;
	}
	public void setGradeList(List<ScoreDM> gradeList) {
		this.gradeList = gradeList;
	}
	public List<ExamPaperItemDM> getExamList() {
		return examList;
	}
	public void setExamList(List<ExamPaperItemDM> examList) {
		this.examList = examList;
	}
	
	
	
}
