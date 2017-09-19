package com.example.finalproject.manager;

import java.util.Date;

import dao.sotong.EmoticonDAO;
import dao.sotong.GalleryCategoryDAO;
import dao.sotong.ImageDAO;
import dao.sotong.SotongCategoryDAO;
import dao.sotong.SotongContentsDAO;

public class SotongManager {
	private ImageDAO imageDAO;
	private EmoticonDAO emoticonDAO;
	private SotongContentsDAO sotongContentsDAO;
	private SotongCategoryDAO sotongCategoryDAO;
	private GalleryCategoryDAO galleryCategoryDAO;
	public SotongManager()
	{
		imageDAO=new ImageDAO();
		emoticonDAO=new EmoticonDAO();
		sotongContentsDAO=new SotongContentsDAO();
		sotongCategoryDAO=new SotongCategoryDAO();
		galleryCategoryDAO=new GalleryCategoryDAO();
	}
	
	public String addSotongContents(String familyHomeCode,String sotongCategoryCode, String contents,String imageName,String emoticonCode)
	{	
		String imageCode=imageDAO.insertImageAndReturnImageCode(imageName, new Date(),"c2");
		//imageDAO.insertImage(imageName, imageWrittenDate, galleryCategoryCode)
		String sotongContentsCode=sotongContentsDAO.insertSotongContentsCode(familyHomeCode, sotongCategoryCode, contents, imageCode, emoticonCode);
		return sotongContentsCode;
		
	}
	public String addSotongContents(String familyHomeCode,String sotongCategoryCode,String contents)
	{
		String sotongContentsCode=sotongContentsDAO.insertSotongContentsCode(familyHomeCode, sotongCategoryCode, contents, "i1", "e1");
		return sotongContentsCode;
		
	}
	
	
}
