package com.online.exam.tb;

// Generated 2015-1-22 23:19:06 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ExamPaperTb generated by hbm2java
 */
@SuppressWarnings({"serial","rawtypes"})
@Entity
@Table(name = "exam_paper_tb", catalog = "exam")
public class ExamPaperTb implements java.io.Serializable {

	private String examPaperNum;
	private Integer totalScore;
	private String status;
	private Integer number;
	private Date createTime;
//	private Set scoreTbs = new HashSet(0);
	private Set examPaperItemTbs = new HashSet(0);

	public ExamPaperTb() {
	}

	public ExamPaperTb(String examPaperNum, Integer totalScore, String status,
			int number) {
		this.examPaperNum = examPaperNum;
		this.totalScore = totalScore;
		this.status = status;
		this.number = number;
	}

	@Id
	@Column(name = "EXAM_PAPER_NUM", unique = true, nullable = false, length = 36)
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
	public String getExamPaperNum() {
		return this.examPaperNum;
	}

	public void setExamPaperNum(String examPaperNum) {
		this.examPaperNum = examPaperNum;
	}

	@Column(name = "TOTAL_SCORE", nullable = false)
	public Integer getTotalScore() {
		return this.totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	@Column(name = "STATUS", nullable = false, length = 4)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "NUMBER", nullable = false)
	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Column(name = "CREATE_TIME", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "examPaperTb",targetEntity=ScoreTb.class)
//	public Set getScoreTbs() {
//		return this.scoreTbs;
//	}
//
//	public void scoreTbs(Set scoreTbs) {
//		this.scoreTbs = scoreTbs;
//	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "examPaperTb",targetEntity=ExamPaperItemTb.class)
	public Set getExamPaperItemTbs() {
		return this.examPaperItemTbs;
	}

	public void setExamPaperItemTbs(Set examPaperItemTbs) {
		this.examPaperItemTbs = examPaperItemTbs;
	}

}