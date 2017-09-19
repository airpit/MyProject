package manager.home;

import java.util.Date;

import dao.home.FamilyMemberDAO;

public class JoinManager {
	private FamilyMemberDAO familyMemberDAO;
	
	public JoinManager() {
		this.familyMemberDAO = new FamilyMemberDAO();
	}
	
	//id Ȯ�� �޼ҵ�
	public byte checkId(String id) {
		return familyMemberDAO.checkId(id);
	}
	
	//email Ȯ���� �Ѵ�. ���߿� �־���Ѵ�.
	
	//�ʴ� �޾����� ȸ�� ��� �Ǵ� �޼ҵ��̴�.
	public int joinMember(String id, String pw, String name, String email, String phoneNum, String homeCode) {
		byte role = 0;
		return familyMemberDAO.insertMember(homeCode, name, phoneNum, email, id, pw, role);
	}
	
	//�ʴ� ���� �ʰ� ȸ����� �ϴ� �޼ҵ�
	public int joinMember(String id, String pw, String name, String email, String phoneNum) {
		byte role = 0;
		return this.familyMemberDAO.insertMember(name, phoneNum, email, id, pw, role);
	}
	
	//Ȩ�ڵ� ���� Ȯ��
	public int checkInviterHomeCode(String homeCode) {
		return familyMemberDAO.checkInviterHomeCode(homeCode);
	}
}
