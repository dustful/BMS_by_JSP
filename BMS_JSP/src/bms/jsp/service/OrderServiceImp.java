package bms.jsp.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bms.jsp.dao.BookDAO;
import bms.jsp.dao.BookDAOImp;
import bms.jsp.dao.OrderDAO;
import bms.jsp.dao.OrderDAOImp;
import bms.jsp.dto.BookDTO;
import bms.jsp.dto.OrderDTO;

public class OrderServiceImp implements OrderService {
	
	private static OrderServiceImp instance;
	
	public static OrderServiceImp getInstance() {
		if(instance == null) instance = new OrderServiceImp();
		return instance;
	}
	
	// 주문 등록
	@Override
	public String registerOrder(HttpServletRequest req, HttpServletResponse res) {
		String[] bkno = req.getParameterValues("bkno");
		String[] odqty = req.getParameterValues("odqty");
		int nextv = Integer.parseInt(req.getParameter("nextv"));
		OrderDTO dto = new OrderDTO();
		OrderDAOImp oddao = OrderDAOImp.getInstance();
		int result = 0;
		BookDAOImp bkdao = BookDAOImp.getInstance();
		
		dto.setOdname(req.getParameter("odname"));
		dto.setOdcontact(req.getParameter("odcontact"));
		dto.setRcname(req.getParameter("rcname"));
		dto.setRccontact(req.getParameter("rccontact"));
		dto.setRcaddr(req.getParameter("rcaddr"));
		dto.setPymd(Integer.parseInt(req.getParameter("pymd")));
		
		for(int i = 0; i < bkno.length; i++) {
			dto.setBkno(Integer.parseInt(bkno[i]));
			dto.setOdqty(Integer.parseInt(odqty[i]));

			BookDTO bkdto = (BookDTO) bkdao.execQuery(Code.SELECT_BOOK_DETAIL, BookDAO.SELECT_BOOK_DETAIL, Integer.parseInt(bkno[i]));
			int result3 = bkdto.getBkqty() - Integer.parseInt(odqty[i]);

			// 재고 수량 대 주문 수량 비교
			if(result3 >= 0) {
				// 재고 수량에서 주문 수량만큼 차감
				int result2 = bkdao.execUpdate(Code.UPDATE_BOOK_QTY, BookDAO.UPDATE_BOOK_QTY, result3, Integer.parseInt(bkno[i]));
				
				if(result2 == 1) {
					result = oddao.execUpdate(Code.INSERT_ORDER, OrderDAO.INSERT_ORDER, dto, nextv);
				}
			} else {
				// 재고 수량 미달로 주문 도서중 일부 도서의 주문이 취소되었습니다.
			}
		}
		
		req.setAttribute("result", result);
		return "orderComplete.do";
	}

	// 주문 목록
	@Override
	public String viewList(HttpServletRequest req, HttpServletResponse res, int pageCode) {
		int divContent = 10; // 보여줄 주문수
		int divPage = 5; // 보여줄 페이지수
		int num = 0; // 출력할 주문 번호
		String pageNum = null; // 페이지 번호
		int tot = 0; // 전체 주문수
		int beginNum = 0; // 시작 번호
		int endNum = 0; // 종료 번호
		int totPage = 0; // 페이지수
		int currPage = 0; // 현재 페이지
		int beginPage = 0; // 시작 페이지
		int endPage = 0; // 종료 페이지
		OrderDAOImp dao = OrderDAOImp.getInstance();
		
		// ===== 페이지 처리 시작 =====
		tot = (int) dao.execQuery(Code.SELECT_ORDER_TOTAL, OrderDAO.SELECT_ORDER_TOTAL); // 전체 주문수
		pageNum = req.getParameter("pageNum"); // 페이지 번호

		if(pageNum == null) pageNum = "1"; // 페이지 번호 초기화

		currPage = Integer.parseInt(pageNum); // 현재 페이지 번호에 페이지 번호를 반영
		totPage = (tot / divContent) + (tot % divContent > 0? 1:0); // 페이지수
		beginNum = (currPage - 1) * divContent + 1; // 시작 번호
		endNum = beginNum + divContent - 1; // 종료 번호
		
		if(endNum > tot) endNum = tot;
		
		num = tot - (currPage - 1) * divContent; // 출력할 주문 번호
		// ===== 페이지 처리 종료 =====
		
		// 주문 목록 정보 호출
		if(tot > 0) {
			ArrayList<OrderDTO> orders = (ArrayList<OrderDTO>) dao.execQuery(Code.SELECT_ORDER_LIST, OrderDAO.SELECT_ORDER_LIST, beginNum, endNum);
			
			req.setAttribute("orders", orders);
		}
		
		beginPage = (currPage / divPage) * divPage + 1; // 시작 페이지
		
		if(currPage % divPage == 0) beginPage -= divPage;

		endPage = beginPage + divPage - 1; // 종료 페이지

		if(endPage > totPage) endPage = totPage;
		
		req.setAttribute("tot", tot);
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		
		if(tot > 0) {
			req.setAttribute("tot", tot);
			req.setAttribute("beginPage", beginPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("divPage", divPage);
			req.setAttribute("totPage", totPage);
			req.setAttribute("currPage", currPage);
		}
		
		String ret = null;
		
		switch(pageCode) {
			case Code.SELLER_ORDER_LIST:
				ret = "/order/seller/list.jsp";
				break;
			case Code.CUSTOMER_ORDER_LIST:
				ret = "/order/customer/list.jsp";
				break;
		}
		return ret;
	}

	// 주문 상세
	@Override
	public String viewDetail(HttpServletRequest req, HttpServletResponse res, int pageCode) {
		int orgNum = Integer.parseInt(req.getParameter("orgNum"));
		OrderDAOImp dao = OrderDAOImp.getInstance();
		int includedDeliveryCharge = 50000; // 배송비를 포함하는 최저 주문가
		int deliveryCharge = 3000; // 배송비
		
		// 주문 상세 정보 호출
		ArrayList<OrderDTO> orders = (ArrayList<OrderDTO>) dao.execQuery(Code.SELECT_ORDER_DETAIL, OrderDAO.SELECT_ORDER_DETAIL, orgNum);
		
		req.setAttribute("orgNum", orgNum);
		req.setAttribute("orders", orders);
		req.setAttribute("includedDeliveryCharge", includedDeliveryCharge);
		req.setAttribute("deliveryCharge", deliveryCharge);
		
		String ret = null;
		
		switch(pageCode) {
			case Code.SELLER_ORDER_DETAIL:
				ret = "/order/seller/detail.jsp";
				break;
			case Code.CUSTOMER_ORDER_DETAIL:
				ret = "/order/customer/detail.jsp";
				break;
		}
		return ret;
	}

}
