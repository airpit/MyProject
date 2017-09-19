package dao.schedule;

import java.io.Serializable;
import java.util.Date;

public class ScheduleViewVO implements Serializable{

	private static final long serialVersionUID = 140909102521968164L;
	private String scheduleCode;
	private String scheduleMemberCode;
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

	public ScheduleViewVO(String scheduleCode, String scheduleMemberCode,
			String scheduleMember, String scheduleTitle, String schedulePlace,
			Date scheduleStartDate, Date scheduleEndDate, Date scheduleAlarm,
			int scheduleRepeat, String scheduleMemo, String alarmMember) {
		super();
		this.scheduleCode = scheduleCode;
		this.scheduleMemberCode = scheduleMemberCode;
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

	public void setScheduleCode(String scheduleCode) {
		this.scheduleCode = scheduleCode;
	}

	public String getScheduleMemberCode() {
		return scheduleMemberCode;
	}

	public void setScheduleMemberCode(String scheduleMemberCode) {
		this.scheduleMemberCode = scheduleMemberCode;
	}

	public String getScheduleMember() {
		return scheduleMember;
	}

	public void setScheduleMember(String scheduleMember) {
		this.scheduleMember = scheduleMember;
	}

	public String getScheduleTitle() {
		return scheduleTitle;
	}

	public void setScheduleTitle(String scheduleTitle) {
		this.scheduleTitle = scheduleTitle;
	}

	public String getSchedulePlace() {
		return schedulePlace;
	}

	public void setSchedulePlace(String schedulePlace) {
		this.schedulePlace = schedulePlace;
	}

	public Date getScheduleStartDate() {
		return scheduleStartDate;
	}

	public void setScheduleStartDate(Date scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	public Date getScheduleEndDate() {
		return scheduleEndDate;
	}

	public void setScheduleEndDate(Date scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}

	public Date getScheduleAlarm() {
		return scheduleAlarm;
	}

	public void setScheduleAlarm(Date scheduleAlarm) {
		this.scheduleAlarm = scheduleAlarm;
	}

	public int getScheduleRepeat() {
		return scheduleRepeat;
	}

	public void setScheduleRepeat(int scheduleRepeat) {
		this.scheduleRepeat = scheduleRepeat;
	}

	public String getScheduleMemo() {
		return scheduleMemo;
	}

	public void setScheduleMemo(String scheduleMemo) {
		this.scheduleMemo = scheduleMemo;
	}

	public String getAlarmMember() {
		return alarmMember;
	}

	public void setAlarmMember(String alarmMember) {
		this.alarmMember = alarmMember;
	}

	@Override
	public String toString() {
		return "ScheduleViewVO [scheduleCode=" + scheduleCode
				+ ", scheduleMemberCode=" + scheduleMemberCode
				+ ", scheduleMember=" + scheduleMember + ", scheduleTitle="
				+ scheduleTitle + ", schedulePlace=" + schedulePlace
				+ ", scheduleStartDate=" + scheduleStartDate
				+ ", scheduleEndDate=" + scheduleEndDate + ", scheduleAlarm="
				+ scheduleAlarm + ", scheduleRepeat=" + scheduleRepeat
				+ ", scheduleMemo=" + scheduleMemo + ", alarmMember="
				+ alarmMember + "]";
	}
}
	