package dao.schedule;

import java.io.Serializable;
import java.util.Date;

public class ScheduleVO implements Serializable{

	private static final long serialVersionUID = 565402312757734695L;
	private String scheduleCode;
	private String memberCode;
	private String scheduleTitle;
	private String schedulePlace;
	private Date scheduleStartDate;
	private Date scheduleEndDate;
	private Date scheduleAlarm;
	private int scheduleRepeat;
	private String scheduleMemo;
	
	public ScheduleVO() {
		super();
	}
	public ScheduleVO(String scheduleCode, String memberCode, String scheduleTitle, String schedulePlace, Date scheduleStartDate, Date scheduleEndDate, Date scheduleAlarm, int scheduleRepeat, String scheduleMemo) {
		super();
		this.scheduleCode = scheduleCode;
		this.memberCode = memberCode;
		this.scheduleTitle = scheduleTitle;
		this.schedulePlace = schedulePlace;
		this.scheduleStartDate = scheduleStartDate;
		this.scheduleEndDate = scheduleEndDate;
		this.scheduleAlarm = scheduleAlarm;
		this.scheduleRepeat = scheduleRepeat;
		this.scheduleMemo = scheduleMemo;
	}
	public String getScheduleCode() {
		return scheduleCode;
	}
	public String getMemberCode() {
		return memberCode;
	}
	public String getScheduleTitle() {
		return scheduleTitle;
	}
	public String getSchedulePlace() {
		return schedulePlace;
	}
	public Date getScheduleStartDate() {
		return scheduleStartDate;
	}
	public Date getScheduleEndDate() {
		return scheduleEndDate;
	}
	public Date getScheduleAlarm() {
		return scheduleAlarm;
	}
	public int getScheduleRepeat() {
		return scheduleRepeat;
	}
	public String getScheduleMemo() {
		return scheduleMemo;
	}
	public void setScheduleCode(String scheduleCode) {
		this.scheduleCode = scheduleCode;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	public void setScheduleTitle(String scheduleTitle) {
		this.scheduleTitle = scheduleTitle;
	}
	public void setSchedulePlace(String schedulePlace) {
		this.schedulePlace = schedulePlace;
	}
	public void setScheduleStartDate(Date scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}
	public void setScheduleEndDate(Date scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}
	public void setScheduleAlarm(Date scheduleAlarm) {
		this.scheduleAlarm = scheduleAlarm;
	}
	public void setScheduleRepeat(int scheduleRepeat) {
		this.scheduleRepeat = scheduleRepeat;
	}
	public void setScheduleMemo(String scheduleMemo) {
		this.scheduleMemo = scheduleMemo;
	}
	
	public String toString() {
		return "ScheduleVO [scheduleCode=" + scheduleCode + ", memberCode="
				+ memberCode + ", scheduleTitle=" + scheduleTitle
				+ ", schedulePlace=" + schedulePlace + ", scheduleStartDate="
				+ scheduleStartDate + ", scheduleEndDate=" + scheduleEndDate
				+ ", scheduleAlarm=" + scheduleAlarm + ", scheduleRepeat="
				+ scheduleRepeat + ", scheduleMemo=" + scheduleMemo + "]";
	}
	
	
	
	
	
}
