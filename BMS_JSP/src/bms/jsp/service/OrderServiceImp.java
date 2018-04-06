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
	
	// �ֹ� ���
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

			// ��� ���� �� �ֹ� ���� ��
			if(result3 >= 0) {
				// ��� �������� �ֹ� ������ŭ ����
				int result2 = bkdao.execUpdate(Code.UPDATE_BOOK_QTY, BookDAO.UPDATE_BOOK_QTY, result3, Integer.parseInt(bkno[i]));
				
				if(result2 == 1) {
					result = oddao.execUpdate(Code.INSERT_ORDER, OrderDAO.INSERT_ORDER, dto, nextv);
				}
			} else {
				// ��� ���� �̴޷� �ֹ� ������ �Ϻ� ������ �ֹ��� ��ҵǾ����ϴ�.
			}
		}
		
		req.setAttribute("result", result);
		return "orderComplete.do";
	}

	// �ֹ� ���
	@Override
	public String viewList(HttpServletRequest req, HttpServletResponse res, int pageCode) {
		int divContent = 10; // ������ �ֹ���
		int divPage = 5; // ������ ��������
		int num = 0; // ����� �ֹ� ��ȣ
		String pageNum = null; // ������ ��ȣ
		int tot = 0; // ��ü �ֹ���
		int beginNum = 0; // ���� ��ȣ
		int endNum = 0; // ���� ��ȣ
		int totPage = 0; // ��������
		int currPage = 0; // ���� ������
		int beginPage = 0; // ���� ������
		int endPage = 0; // ���� ������
		OrderDAOImp dao = OrderDAOImp.getInstance();
		
		// ===== ������ ó�� ���� =====
		tot = (int) dao.execQuery(Code.SELECT_ORDER_TOTAL, OrderDAO.SELECT_ORDER_TOTAL); // ��ü �ֹ���
		pageNum = req.getParameter("pageNum"); // ������ ��ȣ

		if(pageNum == null) pageNum = "1"; // ������ ��ȣ �ʱ�ȭ

		currPage = Integer.parseInt(pageNum); // ���� ������ ��ȣ�� ������ ��ȣ�� �ݿ�
		totPage = (tot / divContent) + (tot % divContent > 0? 1:0); // ��������
		beginNum = (currPage - 1) * divContent + 1; // ���� ��ȣ
		endNum = beginNum + divContent - 1; // ���� ��ȣ
		
		if(endNum > tot) endNum = tot;
		
		num = tot - (currPage - 1) * divContent; // ����� �ֹ� ��ȣ
		// ===== ������ ó�� ���� =====
		
		// �ֹ� ��� ���� ȣ��
		if(tot > 0) {
			ArrayList<OrderDTO> orders = (ArrayList<OrderDTO>) dao.execQuery(Code.SELECT_ORDER_LIST, OrderDAO.SELECT_ORDER_LIST, beginNum, endNum);
			
			req.setAttribute("orders", orders);
		}
		
		beginPage = (currPage / divPage) * divPage + 1; // ���� ������
		
		if(currPage % divPage == 0) beginPage -= divPage;

		endPage = beginPage + divPage - 1; // ���� ������

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

	// �ֹ� ��
	@Override
	public String viewDetail(HttpServletRequest req, HttpServletResponse res, int pageCode) {
		int orgNum = Integer.parseInt(req.getParameter("orgNum"));
		OrderDAOImp dao = OrderDAOImp.getInstance();
		int includedDeliveryCharge = 50000; // ��ۺ� �����ϴ� ���� �ֹ���
		int deliveryCharge = 3000; // ��ۺ�
		
		// �ֹ� �� ���� ȣ��
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
