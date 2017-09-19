package dao.memu;

import java.io.Serializable;
import java.util.Date;

public class MenuVO implements Serializable{	

	private static final long serialVersionUID = 574362065756671716L;
	private String menuCode;
	private String dinner;
	private Date shareDate;
	
	public MenuVO() {
		super();
	}
	public MenuVO(String menuCode, String dinner, Date shareDate) {
		super();
		this.menuCode = menuCode;
		this.dinner = dinner;
		this.shareDate = shareDate;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getDinner() {
		return dinner;
	}
	public void setDinner(String dinner) {
		this.dinner = dinner;
	}
	public Date getShareDate() {
		return shareDate;
	}
	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
	}
	
	public String toString() {
		return "MenuVO [menuCode=" + menuCode + ", dinner=" + dinner
				+ ", shareDate=" + shareDate + "]";
	}
}
