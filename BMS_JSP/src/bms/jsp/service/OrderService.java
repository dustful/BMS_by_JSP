package bms.jsp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface OrderService {

	// �ֹ� ���
	public String registerOrder(HttpServletRequest req, HttpServletResponse res);
	
	// �ֹ� ����
	// public String modifyOrder(HttpServletRequest req, HttpServletResponse res);
	
	// �ֹ� ����
	// public String deleteOrder(HttpServletRequest req, HttpServletResponse res);
	
	// �ֹ� ���
	public String viewList(HttpServletRequest req, HttpServletResponse res, int pageCode);
	
	// �ֹ� ��
	public String viewDetail(HttpServletRequest req, HttpServletResponse res, int pageCode);

}
