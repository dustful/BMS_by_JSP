package bms.jsp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MemberService {

	// �α���
	public String signin(HttpServletRequest req, HttpServletResponse res);
	
	// ȸ�� ���
	public String signup(HttpServletRequest req, HttpServletResponse res);
	
	// ȸ�� ����
	// public String modifyMember(HttpServletRequest req, HttpServletResponse res);
	
	// ȸ�� ����
	// public String optout(HttpServletRequest req, HttpServletResponse res);
	
	// ȸ�� ���
	public String viewList(HttpServletRequest req, HttpServletResponse res);
	
	// ȸ�� ��
	public String viewDetail(HttpServletRequest req, HttpServletResponse res);

}
