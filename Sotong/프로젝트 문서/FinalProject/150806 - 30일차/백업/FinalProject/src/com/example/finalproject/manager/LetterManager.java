package com.example.finalproject.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import dao.letter.LetterDAO;
import dao.letter.LetterVO;
import dao.letter.LetterViewDAO;
import dao.letter.LetterViewVO;

public class LetterManager {
	private LetterDAO letterDAO;
	private LetterViewDAO letterViewDAO;
	public LetterManager()
	{
		letterDAO=new LetterDAO();
		letterViewDAO=new LetterViewDAO();
	}
	public String[][] getStringLetterInfoList(String memberCode)
	{
		String[][] letterList=null;
		List<LetterViewVO> list=letterViewDAO.selectByMember(memberCode);
		letterList=new String[list.size()][];
		for(int i=0;i<list.size();i++)
		{
			Date sendDate=list.get(i).getSendDate();
			String date=new SimpleDateFormat("yy-MM-dd").format(sendDate);
			letterList[i]=new String[]{list.get(i).getLetterTitle(),date,list.get(i).getSender()};
		}
		return letterList;
	}
	public LetterViewVO getLetterInfo(String letterCode)
	{
		LetterViewVO letterInfo=null;
		letterInfo=letterViewDAO.select(letterCode);
		return letterInfo;
	}
	public boolean addLetterInfo(String senderCode,String receiverCode,String title,String contents,String imageName,String emoticonCode,Date letterWrittenDate)
	{
		SotongManager manager=new SotongManager();
		manager.addSotongContents("h1", "sc1", contents);
		return true;
	}
}
