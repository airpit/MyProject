package dao.schedule;

import java.io.Serializable;
import java.util.Date;

public class FamilyScheduleViewVO implements Serializable{

	private static final long serialVersionUID = 8221646093522624355L;
	private String familyScheduleCode;
	private String familyScheduleTitle;
	private String familySchedulePlace;
	private Date familyScheduleStartDate;
	private Date familyScheduleEndDate;
	private Date familyScheduleAlarm;
	private int familyScheduleRepeat;
	private String familyScheduleMemo;
	private String familyEventRequest;
	private String memberName;
	private String familyResponseContents;
		
	public FamilyScheduleViewVO() {
		super();
	}

	public FamilyScheduleViewVO(String familyScheduleCode,
			String familyScheduleTitle, String familySchedulePlace,
			Date familyScheduleStartDate, Date familyScheduleEndDate,
			Date familyScheduleAlarm, int familyScheduleRepeat,
			String familyScheduleMemo, String familyEventRequest,
			String memberName, String familyResponseContents) {
		super();
		this.familyScheduleCode = familyScheduleCode;
		this.familyScheduleTitle = familyScheduleTitle;
		this.familySchedulePlace = familySchedulePlace;
		this.familyScheduleStartDate = familyScheduleStartDate;
		this.familyScheduleEndDate = familyScheduleEndDate;
		this.familyScheduleAlarm = familyScheduleAlarm;
		this.familyScheduleRepeat = familyScheduleRepeat;
		this.familyScheduleMemo = familyScheduleMemo;
		this.familyEventRequest = familyEventRequest;
		this.memberName = memberName;
		this.familyResponseContents = familyResponseContents;
	}

	public String getFamilyScheduleCode() {
		return familyScheduleCode;
	}

	public String getFamilyScheduleTitle() {
		return familyScheduleTitle;
	}

	public String getFamilySchedulePlace() {
		return familySchedulePlace;
	}

	public Date getFamilyScheduleStartDate() {
		return familyScheduleStartDate;
	}

	public Date getFamilyScheduleEndDate() {
		return familyScheduleEndDate;
	}

	public Date getFamilyScheduleAlarm() {
		return familyScheduleAlarm;
	}

	public int getFamilyScheduleRepeat() {
		return familyScheduleRepeat;
	}

	public String getFamilyScheduleMemo() {
		return familyScheduleMemo;
	}

	public String getFamilyEventRequest() {
		return familyEventRequest;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getFamilyResponseContents() {
		return familyResponseContents;
	}

	public void setFamilyScheduleCode(String familyScheduleCode) {
		this.familyScheduleCode = familyScheduleCode;
	}

	public void setFamilyScheduleTitle(String familyScheduleTitle) {
		this.familyScheduleTitle = familyScheduleTitle;
	}

	public void setFamilySchedulePlace(String familySchedulePlace) {
		this.familySchedulePlace = familySchedulePlace;
	}

	public void setFamilyScheduleStartDate(Date familyScheduleStartDate) {
		this.familyScheduleStartDate = familyScheduleStartDate;
	}

	public void setFamilyScheduleEndDate(Date familyScheduleEndDate) {
		this.familyScheduleEndDate = familyScheduleEndDate;
	}

	public void setFamilyScheduleAlarm(Date familyScheduleAlarm) {
		this.familyScheduleAlarm = familyScheduleAlarm;
	}

	public void setFamilyScheduleRepeat(int familyScheduleRepeat) {
		this.familyScheduleRepeat = familyScheduleRepeat;
	}

	public void setFamilyScheduleMemo(String familyScheduleMemo) {
		this.familyScheduleMemo = familyScheduleMemo;
	}

	public void setFamilyEventRequest(String familyEventRequest) {
		this.familyEventRequest = familyEventRequest;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public void setFamilyResponseContents(String familyResponseContents) {
		this.familyResponseContents = familyResponseContents;
	}

	public String toString() {
		return "FamilyScheduleViewVO [familyScheduleCode=" + familyScheduleCode
				+ ", familyScheduleTitle=" + familyScheduleTitle
				+ ", familySchedulePlace=" + familySchedulePlace
				+ ", familyScheduleStartDate=" + familyScheduleStartDate
				+ ", familyScheduleEndDate=" + familyScheduleEndDate
				+ ", familyScheduleAlarm=" + familyScheduleAlarm
				+ ", familyScheduleRepeat=" + familyScheduleRepeat
				+ ", familyScheduleMemo=" + familyScheduleMemo
				+ ", familyEventRequest=" + familyEventRequest
				+ ", memberName=" + memberName + ", familyResponseContents="
				+ familyResponseContents + "]";
	}
	
	
	
	
}
