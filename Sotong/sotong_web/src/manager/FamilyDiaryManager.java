package manager;

import dao.diary.FamilyDiaryDAO;
import dao.diary.FamilyDiaryPartDAO;
import dao.diary.FamilyDiaryPartVO;
import dao.diary.FamilyDiaryVO;
import dao.diary.FamilyDiaryViewDAO;
import dao.diary.FamilyDiaryViewVO;

public class FamilyDiaryManager 
{
	private FamilyDiaryViewDAO familyDiaryViewDAO;
	private FamilyDiaryDAO familyDiaryDAO;
	private FamilyDiaryPartDAO familyDiaryPartDAO;
	
	public FamilyDiaryManager() {
		familyDiaryViewDAO = new FamilyDiaryViewDAO();
		familyDiaryDAO = new FamilyDiaryDAO();
		familyDiaryPartDAO = new FamilyDiaryPartDAO();
	}
	
	public FamilyDiaryVO[] getSimpleFamilyDiaryList(String homeCode)
	{
		return familyDiaryDAO.selectAllDiaryInfo(homeCode);
	}
	
	public FamilyDiaryViewVO[] getFamilyDiaryInfo(String familyDiaryCode)
	{
		return familyDiaryViewDAO.selectDiaryInfo(familyDiaryCode);
	}
	
	public int addFamilyDiary(String familyHomeCode, String familyMemberCode, String familyDiaryDate, String contents, String imageName,String imageWrittenDate, String emoticonCode)
	{
		String familyDiaryCode = null;
		
		FamilyDiaryVO fDiary = familyDiaryDAO.selectDiaryInfo(familyHomeCode, familyDiaryDate);
		if(fDiary == null)
		{
			familyDiaryCode = familyDiaryDAO.insertDiary(familyHomeCode, familyDiaryDate);
		}
		else
		{
			familyDiaryCode = fDiary.getFamilyDiaryCode();
			
			FamilyDiaryPartVO fDiaryPart = familyDiaryPartDAO.selectDiaryInfo(familyDiaryCode, familyMemberCode);
			
			if(fDiaryPart != null)
			{
				System.out.println("여기 걸리니?");
				return 2;	// 기존에 작성한 파트가 있다면 2값을 리턴하고 수정하기로 넘어간다.
			}
		}
		
		SotongManager sManager = new SotongManager();
		String sotongContentsCode = sManager.addSotongContents(familyHomeCode, "sc2", contents, imageName, imageWrittenDate, emoticonCode);
		
		return familyDiaryPartDAO.insertFamilyDiaryPart(familyDiaryCode, familyMemberCode, sotongContentsCode, familyDiaryDate);
	}
}
