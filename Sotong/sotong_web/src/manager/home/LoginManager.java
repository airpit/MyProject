package manager.home;

import dao.home.FamilyMemberDAO;
import dao.home.FamilyMemberVO;
import dao.home.HomeInfoViewDAO;

public class LoginManager {
	private FamilyMemberDAO familyMemberDAO;
	private HomeInfoViewDAO homeInfoViewDAO;
	
	public LoginManager() {
		this.familyMemberDAO = new FamilyMemberDAO();
		this.homeInfoViewDAO = new HomeInfoViewDAO();
	}
	
	//���̵� ��й�ȣ�� ������ �α��� Ȯ���� �Ѵ�.
	public FamilyMemberVO checkLogin(String id, String pw) {
		return this.familyMemberDAO.checkLogin(id, pw);
	}
	
	//home �Ŵ��� ������ �������� �޼ҵ� �߰��ƴ�.
	public FamilyMemberVO homeManagerInfo(String homeCode) {
		return this.familyMemberDAO.homeManagerInfo(homeCode);
	}
	
	//���̵� ���� �ش� ������ �� ������ �����´�.
	public FamilyMemberVO getMemberInfo(String id) {
		return this.familyMemberDAO.getMemberInfoById(id);
	}
	
	//homeCode�� ���� ���� ���� �������� �������� �����´�.Web
	public String[][] getMemberListWeb(String homeCode, String member) {
		return this.homeInfoViewDAO.selectHomeMemberSimpleInfo(homeCode, member);
	}
	
	//homeCode�� ���� ���� ���� �������� �������� �����´�. App
	public String[][] getMemberListApp(String homeCode) {
		return this.homeInfoViewDAO.selectHomeMemberSimpleInfoApp(homeCode);
	}
	
	//homeCode�� ���� Ȩ �̸��� �����´�.
	public String getHomeName(String homeCode) {
		return homeInfoViewDAO.selectHomeName(homeCode);
	}
}
