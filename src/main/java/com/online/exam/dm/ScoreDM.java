package com.online.exam.dm;

import java.util.Date;

public class ScoreDM {

	private String id;
	private String stuId;
	private String examPaperNum;
	private Double score;
	private Date startTime;
	private Date endTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getExamPaperNum() {
		return examPaperNum;
	}
	public void setExamPaperNum(String examPaperNum) {
		this.examPaperNum = examPaperNum;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	
}
