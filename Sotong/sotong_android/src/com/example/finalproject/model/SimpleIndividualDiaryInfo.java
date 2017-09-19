package com.example.finalproject.model;

import java.util.Date;

public class SimpleIndividualDiaryInfo {
	private String diaryTitle;
	private Date diaryDate;
	
	public SimpleIndividualDiaryInfo() {
		super();
	}

	public SimpleIndividualDiaryInfo(String diaryTitle, Date diaryDate) {
		super();
		this.diaryTitle = diaryTitle;
		this.diaryDate = diaryDate;
	}

	public String getDiaryTitle() {
		return diaryTitle;
	}

	public Date getDiaryDate() {
		return diaryDate;
	}
	
	public String getDiaryDateToString(){
		String year = new String(""+(diaryDate.getYear()-1900));
		return year.substring(1)+"-"+diaryDate.getMonth()+"-"+diaryDate.getDate();
	}

	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}

	public void setDiaryDate(Date diaryDate) {
		this.diaryDate = diaryDate;
	}

	@Override
	public String toString() {
		return "SimpleIndividualDiaryInfo [diaryTitle=" + diaryTitle
				+ ", diaryDate=" + diaryDate + "]";
	}
	
	
	
}
