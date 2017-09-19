package manager.letter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import manager.SotongManager;
import dao.letter.LetterDAO;
import dao.letter.LetterVO;
import dao.letter.LetterViewDAO;
import dao.letter.LetterViewVO;

public class LetterManager {
	private LetterDAO letterDAO;
	private LetterViewDAO letterViewDAO;
	//
	
	public LetterManager()
	{
		letterDAO=new LetterDAO();
		letterViewDAO=new LetterViewDAO();
	}
	public String[][] getStringLetterInfoList(String memberCode)
	{
		String[][] letterList=null;
		List<LetterVO> list=letterDAO.selectByMember(memberCode);
		
		List<LetterViewVO> list2=new ArrayList<LetterViewVO>();
		for(int i=0;i<list.size();i++)
		{
			list2.add(letterViewDAO.select(list.get(i).getLetterCode()));
		}
		
		letterList=new String[list.size()][];
		for(int i=0;i<list.size();i++)
		{
			String sendDate=list.get(i).getSendDate();
			String date=new SimpleDateFormat("yy-MM-dd").format(sendDate);
			letterList[i]=new String[]{list.get(i).getLetterTitle(),date,list2.get(i).getReceiver(),list.get(i).getLetterCode()};
		}
		return letterList;
	}
	public LetterViewVO getLetterInfo(String letterCode)
	{
		LetterViewVO letterInfo=null;
		letterInfo=letterViewDAO.select(letterCode);
		return letterInfo;
	}
	public boolean addLetterInfo(String senderCode,String receiverCode,String title,String contents,String imageName,String emoticonCode,String letterWrittenDate)
	{
		
		SotongManager manager=new SotongManager();
		String code=manager.addSotongContents("h1", "sc1", contents, imageName, letterWrittenDate, emoticonCode);
		int res=letterDAO.insert(code, senderCode, receiverCode, title, letterWrittenDate);
		System.out.println("code:"+code);
		if(res!=0)
			return true;
		else
			return false;
		
	}
	public boolean deleteLetterInfo(String letterCode)
	{
		
		SotongManager manager=new SotongManager();
		manager.deleteSotongContents(letterDAO.select(letterCode).getSotongContentsCode());
		int res=letterDAO.delete(letterCode);
		if(res!=0)
		return true;
		else return false;
		
	}
	public static void main(String args[])
	{
		LetterManager manager=new LetterManager();
		manager.addLetterInfo("m1", "m2", "力格力格", "郴侩郴侩", "images/story/pic4", "em1", "15-08-05");
		/*
		String[][] list=manager.getStringLetterInfoList("m4");
		
		for(int i=0;i<list.length;i++)
		{
			for(int j=0;j<list[i].length;j++)
			{
					System.out.println(list[i][j]);
			}
		}*/
		
	}

}
