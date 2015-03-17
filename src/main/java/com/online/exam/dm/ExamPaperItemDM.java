package com.online.exam.dm;

public class ExamPaperItemDM {

	private String id;
	private ItemDM itemDM;
	private ExamPaperDM examPaperDM;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ItemDM getItemDM() {
		return itemDM;
	}
	public void setItemDM(ItemDM itemDM) {
		this.itemDM = itemDM;
	}
	public ExamPaperDM getExamPaperDM() {
		return examPaperDM;
	}
	public void setExamPaperDM(ExamPaperDM examPaperDM) {
		this.examPaperDM = examPaperDM;
	}
	
}
