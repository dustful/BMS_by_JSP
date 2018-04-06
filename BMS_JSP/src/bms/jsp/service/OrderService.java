package bms.jsp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface OrderService {

	// 주문 등록
	public String registerOrder(HttpServletRequest req, HttpServletResponse res);
	
	// 주문 수정
	// public String modifyOrder(HttpServletRequest req, HttpServletResponse res);
	
	// 주문 삭제
	// public String deleteOrder(HttpServletRequest req, HttpServletResponse res);
	
	// 주문 목록
	public String viewList(HttpServletRequest req, HttpServletResponse res, int pageCode);
	
	// 주문 상세
	public String viewDetail(HttpServletRequest req, HttpServletResponse res, int pageCode);

}
