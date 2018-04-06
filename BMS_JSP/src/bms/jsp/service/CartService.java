package bms.jsp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CartService {

	// 장바구니에 추가
	public String addCart(HttpServletRequest req, HttpServletResponse res);
	
	// 장바구니에서 주문/제거
	public String cartAction(HttpServletRequest req, HttpServletResponse res);
	
	// 장바구니 목록
	public String viewList(HttpServletRequest req, HttpServletResponse res);

}
