package dao.sotong;

import java.io.Serializable;
import java.util.Date;

public class CommentVO implements Serializable{

	private static final long serialVersionUID = 3297700490996486057L;
	private String commentCode;
	private String memberCode;
	
	private String commentContets;
	private Date commentDate;
	
	private String emoticonCode;
	private String familyDiaryCode;
	private String storyCode;
	
	public CommentVO() {
		super();
	}
	public CommentVO(String commentCode, String memberCode,
			String commentContets, Date coomentDate, String emoticonCode,
			String familyDiaryCode, String storyCode) {
		super();
		this.commentCode = commentCode;
		this.memberCode = memberCode;
		this.commentContets = commentContets;
		this.commentDate = coomentDate;
		this.emoticonCode = emoticonCode;
		this.familyDiaryCode = familyDiaryCode;
		this.storyCode = storyCode;
	}
	
	public String getCommentCode() {
		return commentCode;
	}
	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
	}
	public String getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	public String getCommentContets() {
		return commentContets;
	}
	public void setCommentContets(String commentContets) {
		this.commentContets = commentContets;
	}
	public Date getCoomentDate() {
		return commentDate;
	}
	public void setCoomentDate(Date coomentDate) {
		this.commentDate = coomentDate;
	}
	public String getEmoticonCode() {
		return emoticonCode;
	}
	public void setEmoticonCode(String emoticonCode) {
		this.emoticonCode = emoticonCode;
	}
	public String getFamilyDiaryCode() {
		return familyDiaryCode;
	}
	public void setFamilyDiaryCode(String familyDiaryCode) {
		this.familyDiaryCode = familyDiaryCode;
	}
	public String getStoryCode() {
		return storyCode;
	}
	public void setStoryCode(String storyCode) {
		this.storyCode = storyCode;
	}
	
	public String toString() {
		return "CommentVO [commentCode=" + commentCode + ", memberCode="
				+ memberCode + ", commentContets=" + commentContets
				+ ", coomentDate=" + commentDate + ", emoticonCode="
				+ emoticonCode + ", familyDiaryCode=" + familyDiaryCode
				+ ", storyCode=" + storyCode + "]";
	}
	
}
