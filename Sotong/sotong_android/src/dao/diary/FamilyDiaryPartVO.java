package dao.diary;

import java.io.Serializable;
import java.util.Date;

public class FamilyDiaryPartVO implements Serializable{
	private static final long serialVersionUID = -3102662052665683951L;
	private String familyDiaryPartCode;
	private String familyDiaryCode;
	private String memberCode;
	private String sotongContentsCode;
	private Date familyDiaryPartDate;
	
	public FamilyDiaryPartVO() {
		// TODO Auto-generated constructor stub
	}

	public FamilyDiaryPartVO(String familyDiaryPartCode,
			String familyDiaryCode, String memberCode,
			String sotongContentsCode, Date familyDiaryPartDate) {
		super();
		this.familyDiaryPartCode = familyDiaryPartCode;
		this.familyDiaryCode = familyDiaryCode;
		this.memberCode = memberCode;
		this.sotongContentsCode = sotongContentsCode;
		this.familyDiaryPartDate = familyDiaryPartDate;
	}

	public String getFamilyDiaryPartCode() {
		return familyDiaryPartCode;
	}

	public void setFamilyDiaryPartCode(String familyDiaryPartCode) {
		this.familyDiaryPartCode = familyDiaryPartCode;
	}

	public String getFamilyDiaryCode() {
		return familyDiaryCode;
	}

	public void setFamilyDiaryCode(String familyDiaryCode) {
		this.familyDiaryCode = familyDiaryCode;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getSotongContentsCode() {
		return sotongContentsCode;
	}

	public void setSotongContentsCode(String sotongContentsCode) {
		this.sotongContentsCode = sotongContentsCode;
	}

	public Date getFamilyDiaryPartDate() {
		return familyDiaryPartDate;
	}

	public void setFamilyDiaryPartDate(Date familyDiaryPartDate) {
		this.familyDiaryPartDate = familyDiaryPartDate;
	}

	public String toString() {
		return "FamilyDiaryPartVO [familyDiaryPartCode=" + familyDiaryPartCode
				+ ", familyDiaryCode=" + familyDiaryCode + ", memberCode="
				+ memberCode + ", sotongContentsCode=" + sotongContentsCode
				+ ", familyDiaryPartDate=" + familyDiaryPartDate + "]";
	}
}
