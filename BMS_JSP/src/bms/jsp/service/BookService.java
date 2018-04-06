package bms.jsp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BookService {

	// ���� ���
	public String registerBook(HttpServletRequest req, HttpServletResponse res);
	
	// ���� ����
	// public String modifyBook(HttpServletRequest req, HttpServletResponse res);
	
	// ���� ����
	// public String deleteBook(HttpServletRequest req, HttpServletResponse res);
	
	// ���� ���
	public String viewList(HttpServletRequest req, HttpServletResponse res, int pageCode);
	
	// ���� ��
	public String viewDetail(HttpServletRequest req, HttpServletResponse res, int pageCode);

}
