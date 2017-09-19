package dao.schedule;

import java.io.Serializable;
import java.util.Date;

public class FamilyScheduleVO implements Serializable{

	private static final long serialVersionUID = -1651747871688446510L;
	private String familyScheduleCode;
	private String familyHomeCode;
	private String memberCode;
	private String familyScheduleTitle;
	private String familySchedulePlace;
	private Date familyScheduleStartDate;
	private Date familyScheduleEndDate;
	private Date familyScheduleAlarm;
	private int familyScheduleRepeat;
	private String familyScheduleMemo;
	
	public FamilyScheduleVO() {
		super();
	}

	public FamilyScheduleVO(String familyScheduleCode, String familyHomeCode,
			String memberCode, String familyScheduleTitle,
			String familySchedulePlace, Date familyScheduleStartDate,
			Date familyScheduleEndDate, Date familyScheduleAlarm,
			int familyScheduleRepeat, String familyScheduleMemo) {
		super();
		this.familyScheduleCode = familyScheduleCode;
		this.familyHomeCode = familyHomeCode;
		this.memberCode = memberCode;
		this.familyScheduleTitle = familyScheduleTitle;
		this.familySchedulePlace = familySchedulePlace;
		this.familyScheduleStartDate = familyScheduleStartDate;
		this.familyScheduleEndDate = familyScheduleEndDate;
		this.familyScheduleAlarm = familyScheduleAlarm;
		this.familyScheduleRepeat = familyScheduleRepeat;
		this.familyScheduleMemo = familyScheduleMemo;
	}

	public String getFamilyScheduleCode() {
		return familyScheduleCode;
	}

	public String getFamilyHomeCode() {
		return familyHomeCode;
	}

	public String getMemberCode() {
		return memberCode;
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

	public void setFamilyScheduleCode(String familyScheduleCode) {
		this.familyScheduleCode = familyScheduleCode;
	}

	public void setFamilyHomeCode(String familyHomeCode) {
		this.familyHomeCode = familyHomeCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
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

	public String toString() {
		return "FamilyScheduleVO [familyScheduleCode=" + familyScheduleCode
				+ ", familyHomeCode=" + familyHomeCode + ", memberCode="
				+ memberCode + ", familyScheduleTitle=" + familyScheduleTitle
				+ ", familySchedulePlace=" + familySchedulePlace
				+ ", familyScheduleStartDate=" + familyScheduleStartDate
				+ ", familyScheduleEndDate=" + familyScheduleEndDate
				+ ", familyScheduleAlarm=" + familyScheduleAlarm
				+ ", familyScheduleRepeat=" + familyScheduleRepeat
				+ ", familyScheduleMemo=" + familyScheduleMemo + "]";
	}

	
	
	
	
	
	
	
}
