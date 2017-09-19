package model.bean;

import java.util.Date;

public class Event {
	private String title;
	private Date start;
	private Date end;
	
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Event(String title, Date start, Date end) {
		super();
		this.title = title;
		this.start = start;
		this.end = end;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	
	
}
