package manager.schedule;

import java.util.Date;

import dao.schedule.AlarmFamilyDAO;
import dao.schedule.ScheduleDAO;
import dao.schedule.ScheduleVO;
import dao.schedule.ScheduleViewDAO;
import dao.schedule.ScheduleViewVO;

public class ScheduleManager {
	
	private ScheduleDAO scheduleDAO;
	private ScheduleViewDAO scheduleViewDAO;
	private AlarmFamilyDAO alarmFamilyDAO;
	
	public ScheduleManager() {
		super();
		this.scheduleDAO = new ScheduleDAO();
		this.scheduleViewDAO = new ScheduleViewDAO();
		this.alarmFamilyDAO = new AlarmFamilyDAO();
	}

	public ScheduleManager(ScheduleDAO scheduleDAO,
		ScheduleViewDAO scheduleViewDAO, AlarmFamilyDAO alarmFamilyDAO) {
		super();
		this.scheduleDAO = scheduleDAO;
		this.scheduleViewDAO = scheduleViewDAO;
		this.alarmFamilyDAO = alarmFamilyDAO;
	}
	
	public ScheduleVO[] getSimpleIndividualScheduleInfoListWeb(String memberCode, String year, String mon)
	{
		return scheduleDAO.selectSimpleScheduleInfoListWeb(memberCode, year, mon);
	}
	
	public ScheduleViewVO[] getIndividualScheduleInfoListWeb(String memberCode, String year, String mon)
	{
		return scheduleViewDAO.selectScheduleInfoListWeb(memberCode, year, mon);
	}
	
	public ScheduleViewVO getShceduleViewInfo(String scheduleCode)
	{
		return scheduleViewDAO.selectScheduleView(scheduleCode);
	}
	
	public String[][] getSimpleIndividualScheduleInfoList(String memberCode, String year, String mon, String date)
	{
		return scheduleDAO.selectSimpleScheduleInfoByDate(memberCode, year, mon, date);
	}
	
	public ScheduleVO getIndividualScheduleInfo(String scheduleCode)
	{
		return scheduleDAO.selectSchedule(scheduleCode);
	}
	
	public int addIndividualScheduleInfo(String memberCode, String scheduleTitle, String schedulePlace, Date scheduleStartDate, Date scheduleEndDate, Date scheduleAlarm, int scheduleRepeat, String scheduleMemo, String[] alarmFamilyMemberCode)
	{		
		String scheduleCode = scheduleDAO.insertScheduleAndReturnCode(memberCode, scheduleTitle, schedulePlace, scheduleStartDate, scheduleEndDate, scheduleAlarm, scheduleRepeat, scheduleMemo);
		
		if(scheduleCode==null) // ������ �ڵ� ���� null�̸� insert�� �ȵǾ����Ƿ� -1 ����
		{
			return -1;
		}
		else // ������ �ڵ� ���� ������ ������
		{
			if(alarmFamilyMemberCode != null) // �˶� ����� null�� �ƴϸ�
			{
				for(int i=0; i<alarmFamilyMemberCode.length;i++) // �˶���� ��� ��ŭ �ݺ�
				{
					int rowNum = alarmFamilyDAO.insertAlarmFamily(alarmFamilyMemberCode[i], scheduleCode);	// �˶���� �߰�
					if(rowNum != 1) // �˶���� �߰��� �����Ͽ����� 
					{
						return -1;
					}
				}
			}
		}
		return 1;
	}
	
	public int updateIndividualScheduleInfo(String scheduleCode, String memberCode, String scheduleTitle, String schedulePlace,  Date scheduleStartDate, Date scheduleEndDate, Date scheduleAlarm, int scheduleRepeat, String scheduleMemo, String[] alarmFamilyMemberCode)
	{
		int num = scheduleDAO.updateSchedule(scheduleCode, scheduleTitle, schedulePlace, scheduleStartDate, scheduleEndDate, scheduleAlarm, scheduleRepeat, scheduleMemo);
		if(num <= 0)
		{
			return -1;
		}
		else
		{
			if(alarmFamilyMemberCode != null)
			{
				int deleteNum = alarmFamilyDAO.deleteAlarmFamilyByScheduleCode(scheduleCode); // ��ϵǾ� �ִ� �˶���� ��ü�� �����Ѵ�.
				if(deleteNum<=0)
				{
					return -1; // ������ �����ϸ� -1 ����
				}
				else
				{
					for(int i=0;i<alarmFamilyMemberCode.length;i++) // �˶���� ��� �� ��ŭ �ݺ�
					{
						int insertNum = alarmFamilyDAO.insertAlarmFamily(alarmFamilyMemberCode[i], memberCode, scheduleCode); // �˶���� �߰�
						if(insertNum <= 0) {return -1;} // �߰� ���н� -1 ����
					}
				}
			}
			return 1; 
		}
		
	}
	
	public int deleteIndividualScheduleInfo(String scheduleCode)
	{
		int deleteNum = alarmFamilyDAO.deleteAlarmFamilyByScheduleCode(scheduleCode); // �������ڵ�� �˶� ��ü ����
		if(deleteNum<=0) // ������ �ȵǾ��� ���
		{
			return -1;
		}
		else
		{
			int deleteScheNum = scheduleDAO.deleteSchedule(scheduleCode);
			if(deleteScheNum<=0)
			{
				return -1;
			}
		}
		return 1;
	}
	
//	public String[] doScheduleMatching(Date date)
//	{
//		s
//	}
	
	public static void main(String[] args)
	{
		String[] member = {"m1", "m2", "m3"};
		ScheduleManager man = new ScheduleManager();
		//System.out.println(man.addIndividualScheduleInfo("m2", "���������̶��", "��Ҷ��", new Date(), new Date(), new Date(), 5, "�޸���", member));
		//System.out.println(man.getShceduleViewInfo("SD14415812337"));
		System.out.println(man.deleteIndividualScheduleInfo("SD144158123624"));
//		System.out.println(man.updateIndividualScheduleInfo(scheduleCode, memberCode, scheduleTitle, schedulePlace, scheduleStartDate, scheduleEndDate, scheduleAlarm, scheduleRepeat, scheduleMemo, alarmFamilyMemberCode));
	}

}
