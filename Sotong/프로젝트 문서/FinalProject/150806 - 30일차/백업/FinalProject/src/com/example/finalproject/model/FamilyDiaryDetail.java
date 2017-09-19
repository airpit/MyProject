package com.example.finalproject.model;

import java.util.Date;

public class FamilyDiaryDetail {
	private String name;
	private String profileImage;
	private String content;
	private Date writeDate;
	
	public FamilyDiaryDetail() {
		super();
	}
	public FamilyDiaryDetail(String name, String profileImage, String content, Date writeDate) {
		super();
		this.name = name;
		this.profileImage = profileImage;
		this.content = content;
		this.writeDate = writeDate;
	}
	public String getName() {
		return name;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public String getContent() {
		return content;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public String getWriteDateToString(){
		String year = new String(""+(writeDate.getYear()-1900));
		return year.substring(1)+"-"+writeDate.getMonth()+"-"+writeDate.getDate();
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	@Override
	public String toString() {
		return "FamilyDiaryDetail [name=" + name + ", profileImage="
				+ profileImage + ", content=" + content + ", writeDate="
				+ writeDate + "]";
	}
	
	
	
	
}
