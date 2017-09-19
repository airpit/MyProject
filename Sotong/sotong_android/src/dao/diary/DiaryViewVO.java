package dao.diary;

import java.io.Serializable;
import java.util.Date;

public class DiaryViewVO implements Serializable
{
	private static final long serialVersionUID = -2565527454099067293L;
	private String diaryCode;
	private String memberNickname;
	private String diaryTitle;
	private Date diaryDate;
	private String sotongContentsCode;
	private String contents;
	private String imageName;
	private Date imgaeWrittenDate;
	private String emoticonName;
	private String emoticonRoute;
	
	public DiaryViewVO() {
		// TODO Auto-generated constructor stub
	}

	public DiaryViewVO(String diaryCode, String memberNickname,
			String diaryTitle, Date diaryDate, String sotongContentsCode,
			String contents, String imageName, Date imgaeWrittenDate,
			String emoticonName, String emoticonRoute) {
		super();
		this.diaryCode = diaryCode;
		this.memberNickname = memberNickname;
		this.diaryTitle = diaryTitle;
		this.diaryDate = diaryDate;
		this.sotongContentsCode = sotongContentsCode;
		this.contents = contents;
		this.imageName = imageName;
		this.imgaeWrittenDate = imgaeWrittenDate;
		this.emoticonName = emoticonName;
		this.emoticonRoute = emoticonRoute;
	}

	public String getDiaryCode() {
		return diaryCode;
	}

	public void setDiaryCode(String diaryCode) {
		this.diaryCode = diaryCode;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getDiaryTitle() {
		return diaryTitle;
	}

	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}

	public Date getDiaryDate() {
		return diaryDate;
	}

	public void setDiaryDate(Date diaryDate) {
		this.diaryDate = diaryDate;
	}

	public String getSotongContentsCode() {
		return sotongContentsCode;
	}

	public void setSotongContentsCode(String sotongContentsCode) {
		this.sotongContentsCode = sotongContentsCode;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getImgaeWrittenDate() {
		return imgaeWrittenDate;
	}

	public void setImgaeWrittenDate(Date imgaeWrittenDate) {
		this.imgaeWrittenDate = imgaeWrittenDate;
	}

	public String getEmoticonName() {
		return emoticonName;
	}

	public void setEmoticonName(String emoticonName) {
		this.emoticonName = emoticonName;
	}

	public String getEmoticonRoute() {
		return emoticonRoute;
	}

	public void setEmoticonRoute(String emoticonRoute) {
		this.emoticonRoute = emoticonRoute;
	}

	@Override
	public String toString() {
		return "DiaryViewVO [diaryCode=" + diaryCode + ", memberNickname="
				+ memberNickname + ", diaryTitle=" + diaryTitle
				+ ", diaryDate=" + diaryDate + ", sotongContentsCode="
				+ sotongContentsCode + ", contents=" + contents
				+ ", imageName=" + imageName + ", imgaeWrittenDate="
				+ imgaeWrittenDate + ", emoticonName=" + emoticonName
				+ ", emoticonRoute=" + emoticonRoute + "]";
	}
}

