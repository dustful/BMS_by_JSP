package bms.jsp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CartService {

	// ��ٱ��Ͽ� �߰�
	public String addCart(HttpServletRequest req, HttpServletResponse res);
	
	// ��ٱ��Ͽ��� �ֹ�/����
	public String cartAction(HttpServletRequest req, HttpServletResponse res);
	
	// ��ٱ��� ���
	public String viewList(HttpServletRequest req, HttpServletResponse res);

}
