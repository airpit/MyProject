package dao.schedule;

import java.io.Serializable;
import java.util.Date;

public class ScheduleViewVO implements Serializable{

	private static final long serialVersionUID = 140909102521968164L;
	private String scheduleCode;
	private String scheduleMember;
	private String scheduleTitle;
	private String schedulePlace;
	private Date scheduleStartDate;
	private Date scheduleEndDate;
	private Date scheduleAlarm;
	private int scheduleRepeat;
	private String scheduleMemo;
	private String alarmMember;
		
	public ScheduleViewVO() {
		super();
	}

	public ScheduleViewVO(String scheduleCode, String scheduleMember,
			String scheduleTitle, String schedulePlace, Date scheduleStartDate,
			Date scheduleEndDate, Date scheduleAlarm, int scheduleRepeat,
			String scheduleMemo, String alarmMember) {
		super();
		this.scheduleCode = scheduleCode;
		this.scheduleMember = scheduleMember;
		this.scheduleTitle = scheduleTitle;
		this.schedulePlace = schedulePlace;
		this.scheduleStartDate = scheduleStartDate;
		this.scheduleEndDate = scheduleEndDate;
		this.scheduleAlarm = scheduleAlarm;
		this.scheduleRepeat = scheduleRepeat;
		this.scheduleMemo = scheduleMemo;
		this.alarmMember = alarmMember;
	}

	public String getScheduleCode() {
		return scheduleCode;
	}

	public String getScheduleMember() {
		return scheduleMember;
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

	public String getAlarmMember() {
		return alarmMember;
	}

	public void setScheduleCode(String scheduleCode) {
		this.scheduleCode = scheduleCode;
	}

	public void setScheduleMember(String scheduleMember) {
		this.scheduleMember = scheduleMember;
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

	public void setAlarmMember(String alarmMember) {
		this.alarmMember = alarmMember;
	}

	public String toString() {
		return "ScheduleViewVO [scheduleCode=" + scheduleCode
				+ ", scheduleMember=" + scheduleMember + ", scheduleTitle="
				+ scheduleTitle + ", schedulePlace=" + schedulePlace
				+ ", scheduleStartDate=" + scheduleStartDate
				+ ", scheduleEndDate=" + scheduleEndDate + ", scheduleAlarm="
				+ scheduleAlarm + ", scheduleRepeat=" + scheduleRepeat
				+ ", scheduleMemo=" + scheduleMemo + ", alarmMember="
				+ alarmMember + "]";
	}
	
	
	
}
