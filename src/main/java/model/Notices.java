package com.xadmin.notices.bean;

import java.util.Date;

public class Notices {
	
	private int noticeID;
	private String Topic;
	private String areasAffected;
	private String date;
	private String Details;
	
	
	public Notices(String topic, String areasAffected, String date, String details) {
		super();
		Topic = topic;
		this.areasAffected = areasAffected;
		this.date = date;
		Details = details;
	}
	public Notices(int noticeID, String topic, String areasAffected, String date, String details) {
		super();
		this.noticeID = noticeID;
		Topic = topic;
		this.areasAffected = areasAffected;
		this.date = date;
		Details = details;
	}
	public int getNoticeID() {
		return noticeID;
	}
	public void setNoticeID(int noticeID) {
		this.noticeID = noticeID;
	}
	public String getTopic() {
		return Topic;
	}
	public void setTopic(String topic) {
		Topic = topic;
	}
	public String getAreasAffected() {
		return areasAffected;
	}
	public void setAreasAffected(String areasAffected) {
		this.areasAffected = areasAffected;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDetails() {
		return Details;
	}
	public void setDetails(String details) {
		Details = details;
	}
	
	

}
