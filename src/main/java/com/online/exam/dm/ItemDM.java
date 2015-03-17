package com.online.exam.dm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemDM {

	private String itemNum;
	private String title;
	private String option;
	private String type;
	private Integer difficulty;
	private String answer;
	private Date addTime;
	private  List<ExamPaperItemDM> examPaperItemList = new ArrayList<ExamPaperItemDM>();
	
	public String getItemNum() {
		return itemNum;
	}
	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public List<ExamPaperItemDM> getExamPaperItemList() {
		return examPaperItemList;
	}
	public void setExamPaperItemList(List<ExamPaperItemDM> examPaperItemList) {
		this.examPaperItemList = examPaperItemList;
	}
	
	
}
