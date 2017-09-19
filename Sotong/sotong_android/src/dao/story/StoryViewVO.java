package dao.story;

import java.io.Serializable;
import java.util.Date;

public class StoryViewVO implements Serializable{

	private static final long serialVersionUID = -1773581059160251030L;
	private String storyCode;
	private String familyHomeCode;
	
	private String familyHomeName;
	private String memberNickname;
	
	private String contents;
	private String imageName;
	
	private Date imageWritenDate;
	private String emotioconName;
	
	private String emoticonRoute;
	private Date storyDate;
	
	private int stroyHeart;
	private Date storyModifyDate;
	
	private Boolean storyScope;
	
	public StoryViewVO() {
		super();
	}
	public StoryViewVO(String storyCode, String familyHomeCode,
			String familyHomeName, String memberNickname, String contents,
			String imageName, Date imageWritenDate, String emotioconName,
			String emoticonRoute, Date storyDate, int stroyHeart,
			Date storyModifyDate, Boolean storyScope) {
		super();
		this.storyCode = storyCode;
		this.familyHomeCode = familyHomeCode;
		this.familyHomeName = familyHomeName;
		this.memberNickname = memberNickname;
		this.contents = contents;
		this.imageName = imageName;
		this.imageWritenDate = imageWritenDate;
		this.emotioconName = emotioconName;
		this.emoticonRoute = emoticonRoute;
		this.storyDate = storyDate;
		this.stroyHeart = stroyHeart;
		this.storyModifyDate = storyModifyDate;
		this.storyScope = storyScope;
	}
	public String getStoryCode() {
		return storyCode;
	}
	public void setStoryCode(String storyCode) {
		this.storyCode = storyCode;
	}
	public String getFamilyHomeCode() {
		return familyHomeCode;
	}
	public void setFamilyHomeCode(String familyHomeCode) {
		this.familyHomeCode = familyHomeCode;
	}
	public String getFamilyHomeName() {
		return familyHomeName;
	}
	public void setFamilyHomeName(String familyHomeName) {
		this.familyHomeName = familyHomeName;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
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
	public Date getImageWritenDate() {
		return imageWritenDate;
	}
	public void setImageWritenDate(Date imageWritenDate) {
		this.imageWritenDate = imageWritenDate;
	}
	public String getemotioconName() {
		return emotioconName;
	}
	public void setemotioconName(String emotioconName) {
		this.emotioconName = emotioconName;
	}
	public String getEmoticonRoute() {
		return emoticonRoute;
	}
	public void setEmoticonRoute(String emoticonRoute) {
		this.emoticonRoute = emoticonRoute;
	}
	public Date getStoryDate() {
		return storyDate;
	}
	public void setStoryDate(Date storyDate) {
		this.storyDate = storyDate;
	}
	public int getStroyHeart() {
		return stroyHeart;
	}
	public void setStroyHeart(int stroyHeart) {
		this.stroyHeart = stroyHeart;
	}
	public Date getStoryModifyDate() {
		return storyModifyDate;
	}
	public void setStoryModifyDate(Date storyModifyDate) {
		this.storyModifyDate = storyModifyDate;
	}
	public Boolean getStoryScope() {
		return storyScope;
	}
	public void setStoryScope(Boolean storyScope) {
		this.storyScope = storyScope;
	}
	
	public String toString() {
		return "StoryViewVO [storyCode=" + storyCode + ", familyHomeCode="
				+ familyHomeCode + ", familyHomeName=" + familyHomeName
				+ ", memberNickname=" + memberNickname + ", contents="
				+ contents + ", imageName=" + imageName + ", imageWritenDate="
				+ imageWritenDate + ", emotioconName=" + emotioconName
				+ ", emoticonRoute=" + emoticonRoute + ", storyDate="
				+ storyDate + ", stroyHeart=" + stroyHeart
				+ ", storyModifyDate=" + storyModifyDate + ", storyScope="
				+ storyScope + "]";
	}
	
}
