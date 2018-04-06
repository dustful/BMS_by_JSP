package bms.jsp.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bms.jsp.dao.BookDAO;
import bms.jsp.dao.BookDAOImp;
import bms.jsp.dao.OrderDAO;
import bms.jsp.dao.OrderDAOImp;
import bms.jsp.dto.BookDTO;

public class CartServiceImp implements CartService {
	
	private static CartServiceImp instance;
	
	public static CartServiceImp getInstance() {
		if(instance == null) instance = new CartServiceImp();
		return instance;
	}
	
	// ��ٱ��Ͽ� �߰�
	@Override
	public String addCart(HttpServletRequest req, HttpServletResponse res) {
		int mbno = 2;
		HashMap<Integer, BookDTO> carts = (HashMap<Integer, BookDTO>) req.getSession().getAttribute("cart" + mbno);
		int orgNum = Integer.parseInt(req.getParameter("orgNum"));
		BookDAOImp dao = BookDAOImp.getInstance();
		BookDTO dto = (BookDTO) dao.execQuery(Code.SELECT_BOOK_DETAIL, BookDAO.SELECT_BOOK_DETAIL, orgNum);
		int result = 0;
		
		if(carts == null) {
			HashMap<Integer, BookDTO> newCarts = new HashMap<Integer, BookDTO>();
			
			newCarts.put(orgNum, dto);
			req.getSession().setAttribute("cart" + mbno, newCarts);
			
			result = 1;
			
			/*
			 * ��ٱ��� �����ϼ� ����
			 * 30�� = 60 * 60 * 24 * 30
			 * : session �⺻ ��ü�� ������ ���� ���� : getId(), getCreationTime(), getLastAccessedTime()
			 */
			req.getSession().setMaxInactiveInterval(60 * 60 * 24 * 30);
		} else {
			if(!carts.containsKey(orgNum)) {
				carts.put(orgNum, dto);
				req.getSession().setAttribute("cart" + mbno, carts);
				
				result = 1;
			} else {
				result = 2;
			}
		}
		
		req.setAttribute("orgNum", orgNum);
		req.setAttribute("result", result);
		return "bookDetail.do";
	}
	
	// ��ٱ��Ͽ��� �ֹ�/����
	@Override
	public String cartAction(HttpServletRequest req, HttpServletResponse res) {
		int mbno = 2;
		HashMap<Integer, BookDTO> carts = (HashMap<Integer, BookDTO>) req.getSession().getAttribute("cart" + mbno);
		String[] chkEach = req.getParameterValues("check_each");
		String[] odqty = req.getParameterValues("odqty");
		int includedDeliveryCharge = 50000; // ��ۺ� �����ϴ� ���� �ֹ���
		int deliveryCharge = 3000; // ��ۺ�
		BookDAOImp dao = BookDAOImp.getInstance();
		OrderDAOImp oddao = OrderDAOImp.getInstance();
		String cartAction = req.getParameter("cartAction");
		HashMap<Integer, BookDTO> books = new HashMap<Integer, BookDTO>();
		int totPrice = 0;
		String ret = null;
		
		switch(cartAction) {
			// ��ü �ֹ� �Ǵ� ������ ��ǰ�� �ֹ���
			case "orderSelected":
				// �� ���� �ݾ� ���� �� ��ǰ ���� ����
				for(int i = 0; i < chkEach.length; i++) {
					BookDTO book = (BookDTO) dao.execQuery(Code.SELECT_BOOK_DETAIL, BookDAO.SELECT_BOOK_DETAIL, Integer.parseInt(chkEach[i]));
					int bkprice = book.getBkprice();
					totPrice += bkprice * Integer.parseInt(odqty[i]);
					book.setBkqty(Integer.parseInt(odqty[i]));
					books.put(Integer.parseInt(chkEach[i]), book);
				}
				
				// �� ���� �ݾ��� ��ۺ� �����ϴ� ���� �ֹ������� ���� ���, ��ۺ� �߰�
				if(totPrice < includedDeliveryCharge) req.setAttribute("deliveryCharge", deliveryCharge);
				else req.setAttribute("deliveryCharge", 0);

				// �ֹ� ��ȣ ����
				int nextv = (int) oddao.execQuery(Code.SELECT_ORDER_NEXTVAL, OrderDAO.SELECT_ORDER_NEXTVAL);

				req.setAttribute("nextv", nextv);
				req.setAttribute("totPrice", totPrice);
				req.setAttribute("books", books);
				
				ret = "/order/customer/orderForm.jsp";
				break;
			// ������ ��ǰ�� ��ٱ��Ͽ��� ���Ž�
			case "removeToCart":
				for(int i = 0; i < chkEach.length; i++) {
					if(carts.containsKey(Integer.parseInt(chkEach[i]))) {
						carts.remove(Integer.parseInt(chkEach[i]));
					}
				}
				
				ret = "cartList.do";
				break;
		}
		return ret;
	}

	// ��ٱ��� ���
	@Override
	public String viewList(HttpServletRequest req, HttpServletResponse res) {
		int mbno = 2;
		HashMap<Integer, BookDTO> carts = (HashMap<Integer, BookDTO>) req.getSession().getAttribute("cart" + mbno);
		int num = (carts == null? 0 : carts.size());
		
		req.setAttribute("num", num);
		req.setAttribute("carts", carts);
		return "/cartList.jsp";
	}
	
}
