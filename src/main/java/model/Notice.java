package model;

public class Notice {

	private int id;
	private String Topic;
	private String areasAffected;
	private String Date;
	private String Details;
	
	
	public Notice(String topic, String areasAffected, String date, String details) {
		super();
		Topic = topic;
		this.areasAffected = areasAffected;
		Date = date;
		Details = details;
	}
	public Notice(int id, String topic, String areasAffected, String date, String details) {
		super();
		this.id = id;
		Topic = topic;
		this.areasAffected = areasAffected;
		Date = date;
		Details = details;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getDetails() {
		return Details;
	}
	public void setDetails(String details) {
		Details = details;
	}
	
	
}
