package dao.diary;

import java.io.Serializable;
import java.util.Date;

public class FamilyDiaryViewVO implements Serializable{

	private static final long serialVersionUID = -1348933048448352370L;
	private String familyDiaryCode;
	private String familyHomeCode;
	private Date familyDiaryDate;
	private String memberNickname;
	private Date familyDiaryPartDate;
	private String sotongContentsCode;
	private String contents;
	private String emoticonName;
	private String emoticonRoute;
	private String imageName;
	private Date imageWrittenDate;
	
	public FamilyDiaryViewVO() {
		// TODO Auto-generated constructor stub
	}

	public FamilyDiaryViewVO(String familyDiaryCode, String familyHomeCode,
			Date familyDiaryDate, String memberNickname,
			Date familyDiaryPartDate, String sotongContentsCode,
			String contents, String emoticonName, String emoticonRoute,
			String imageName, Date imageWrittenDate) {
		super();
		this.familyDiaryCode = familyDiaryCode;
		this.familyHomeCode = familyHomeCode;
		this.familyDiaryDate = familyDiaryDate;
		this.memberNickname = memberNickname;
		this.familyDiaryPartDate = familyDiaryPartDate;
		this.sotongContentsCode = sotongContentsCode;
		this.contents = contents;
		this.emoticonName = emoticonName;
		this.emoticonRoute = emoticonRoute;
		this.imageName = imageName;
		this.imageWrittenDate = imageWrittenDate;
	}

	public String getFamilyDiaryCode() {
		return familyDiaryCode;
	}

	public void setFamilyDiaryCode(String familyDiaryCode) {
		this.familyDiaryCode = familyDiaryCode;
	}

	public String getFamilyHomeCode() {
		return familyHomeCode;
	}

	public void setFamilyHomeCode(String familyHomeCode) {
		this.familyHomeCode = familyHomeCode;
	}

	public Date getFamilyDiaryDate() {
		return familyDiaryDate;
	}

	public void setFamilyDiaryDate(Date familyDiaryDate) {
		this.familyDiaryDate = familyDiaryDate;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public Date getFamilyDiaryPartDate() {
		return familyDiaryPartDate;
	}

	public void setFamilyDiaryPartDate(Date familyDiaryPartDate) {
		this.familyDiaryPartDate = familyDiaryPartDate;
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

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getImageWrittenDate() {
		return imageWrittenDate;
	}

	public void setImageWrittenDate(Date imageWrittenDate) {
		this.imageWrittenDate = imageWrittenDate;
	}

	@Override
	public String toString() {
		return "FamilyDiaryViewVO [familyDiaryCode=" + familyDiaryCode
				+ ", familyHomeCode=" + familyHomeCode + ", familyDiaryDate="
				+ familyDiaryDate + ", memberNickname=" + memberNickname
				+ ", familyDiaryPartDate=" + familyDiaryPartDate
				+ ", sotongContentsCode=" + sotongContentsCode + ", contents="
				+ contents + ", emoticonName=" + emoticonName
				+ ", emoticonRoute=" + emoticonRoute + ", imageName="
				+ imageName + ", imageWrittenDate=" + imageWrittenDate + "]";
	}	
}
