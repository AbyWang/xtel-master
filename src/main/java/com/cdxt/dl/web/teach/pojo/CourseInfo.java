package com.cdxt.dl.web.teach.pojo;

import java.util.List;

/**
 * 
 * 
 * @ClassName: CourseInfo.java
 * @Description: 课程信息
 * @author wangxiaolong
 * @Copyright: Copyright (c) 2017
 * @Company:成都信通网易医疗科技发展有限公司
 * @date 2018年8月8日
 */
public class CourseInfo {
	//课程ID
	private int courseID;
	//课程所属的用户ID
	private int lecturerID;
	//课程名称
	private String name;
	//课程状态
	//0 – 未提交，未审核
	//1 – 已提交，审核中
	//2 – 审核通过，等待开课
	//3 – 课程进行中
	//4 – 课程已结束
	private int status;
	//总课时
	private int totalClass;
	//价格
	private int price;
	//课程类型
	//0 – 传统直播授课
	//1 – 智能授课
	private int type;
	//已售出数量
	private int sold;
	//已通过该门课程的人数
	private int pass;
	//最近一次开课时间，以s为单位的时间戳
	private int lastClassTime;
	//审核人ID
	private int reviewerID;
	//课程简介
	private String brief;

	private List<CoursePlan>coursePlan;
	
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public int getLecturerID() {
		return lecturerID;
	}
	public void setLecturerID(int lecturerID) {
		this.lecturerID = lecturerID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getTotalClass() {
		return totalClass;
	}
	public void setTotalClass(int totalClass) {
		this.totalClass = totalClass;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	public int getPass() {
		return pass;
	}
	public void setPass(int pass) {
		this.pass = pass;
	}
	public int getLastClassTime() {
		return lastClassTime;
	}
	public void setLastClassTime(int lastClassTime) {
		this.lastClassTime = lastClassTime;
	}
	public int getReviewerID() {
		return reviewerID;
	}
	public void setReviewerID(int reviewerID) {
		this.reviewerID = reviewerID;
	}
	
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public List<CoursePlan> getCoursePlan() {
		return coursePlan;
	}
	public void setCoursePlan(List<CoursePlan> coursePlan) {
		this.coursePlan = coursePlan;
	}

}
