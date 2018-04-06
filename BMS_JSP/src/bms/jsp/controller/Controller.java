package bms.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bms.jsp.service.BookService;
import bms.jsp.service.BookServiceImp;
import bms.jsp.service.CartService;
import bms.jsp.service.CartServiceImp;
import bms.jsp.service.Code;
import bms.jsp.service.MemberService;
import bms.jsp.service.MemberServiceImp;
import bms.jsp.service.OrderService;
import bms.jsp.service.OrderServiceImp;

/*
 * ========== ������Ʈ ���� ==========
 * ��Ī : BMS JSP(Book Management System for Bookstore)
 * ���� : JSP ��� �¶��� ȸ���� ������ ���� ���� �ý���
 * ������ : Ȳ��ȯ(dustful@naver.com)
 * 
 * 
 * ========== �۾� ���� ==========
 * 2017.09.30
 * ���� ������ ����(��ǰ �з��� ��� ó��)
 * ��ǰ �ı�/���� �Խ��� �߰�
 * ������ > ��ٱ��� > üũ �ڽ��� �������� �ʰ� ��ư Ŭ���� ���� ó��
 * 
 * 2017.09.29
 * ������ > �α���/�ƿ�, ȸ�� ����/Ż��, ���� ���� ����/����
 * �Ǹ��� > ȸ�� ���/���� ����, ȸ�� ����
 * 
 * 2017.09.28
 * �Ǹ���, ������ > �ֹ� �� > �ֹ��� ���� ���� ó��(�ֹ� ���/���� Ȯ��/�߼� �Ϸ�/ȯ�� ��û/ȯ�� ����)
 * 
 * 2017.09.27
 * ������ > ��ǰ �� > ��� ����, ��ٱ��Ͽ� �߰�
 * ������ > ��ٱ��� > ��ٱ��Ͽ��� �ֹ�/����
 * �Ǹ���, ������ > �ֹ� ���/��
 * 
 * 2017.09.26
 * �Ǹ��� > ��ǰ ��� > ��ȿ�� �˻�
 * �Ǹ���, ������ > ��ǰ ���/��
 * 
 * 
 * ========== ���� ���� ���� ==========
 * ��ǰ ������ ������, ���� ����
 * ���� ��Ŀ� ��ġ��, ���� ����
 * Oracle 4000 byte �̻� ������ ó��
 * ���� ó��(��� ��ȣ ��ȣȭ ��)
 */

@WebServlet("*.do")
public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public MemberService member = MemberServiceImp.getInstance();
	public BookService book = BookServiceImp.getInstance();
	public OrderService order = OrderServiceImp.getInstance();
	public CartService cart = CartServiceImp.getInstance();
	
	public Controller() { super(); }
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		doAct(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		doAct(req, res);
	}
	
	public void doAct(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		// �����ڵ� ���� ���ڵ�
		req.setCharacterEncoding("UTF-8");
		
		/*�� ������ ��ΰ��� ���� ���� ����*/
		String viewPage = null;
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String url = uri.substring(contextPath.length());
		/*�� ������ ��ΰ��� ���� ���� ����*/
		
		if(url.equals("/index.do")) {
			// ���� ������ ==========> �۾��� ==========> ��ǰ �з��� ���� ��� ���
			System.out.println("/index.do");
			
			viewPage = "/index.jsp";
		} else if(url.equals("/myPage.do")) {
			// ���� ������ ==========> �۾���
			System.out.println("/myPage.do");
			
			viewPage = "/myPage.jsp";
		} else if(url.equals("/signinForm.do")) {
			// �α��� ���
			System.out.println("/signinForm.do");
			
			viewPage = "/member/signinForm.jsp";
		} else if(url.equals("/signinPro.do")) {
			// �α���
			System.out.println("/signinPro.do");
			
			viewPage = member.signin(req, res);
		} else if(url.equals("/findMemberInfo.do")) {
			// ���̵�/��� ��ȣ ã�� ==========> �۾� ����
			System.out.println("/findMemberInfo.do");
		} else if(url.equals("/signout.do")) {
			// �α׾ƿ�
			System.out.println("/signout.do");
			req.getSession().invalidate(); // �����Ͻ� �������� �̵� ����
			
			viewPage = "index.do";
		} else if(url.equals("/signupForm.do")) {
			// ȸ�� ���� ��� ==========> �۾��� ==========> ��ȿ�� �˻�
			System.out.println("/signupForm.do");
			
			viewPage = "/member/signupForm.jsp";
		} else if(url.equals("/signupPro.do")) {
			// ȸ�� ���� ==========> �۾���
			System.out.println("/signupPro.do");
			
			viewPage = member.signup(req, res);
		} else if(url.equals("/memberModifyForm.do")) {
			// ȸ�� ���� ���� ==========> �۾� ����
			System.out.println("/memberModifyForm.do");
		} else if(url.equals("/optoutForm.do")) {
			// ȸ�� Ż�� Ȯ�� ==========> �۾���
			System.out.println("/optoutForm.do");
			
			viewPage = "/member/optoutForm.jsp";
		} else if(url.equals("/optoutPro.do")) {
			// ȸ�� Ż�� ==========> �۾� ����
			System.out.println("/optoutPro.do");
		} else if(url.equals("/memberList.do")) {
			// ȸ�� ���
			System.out.println("/memberList.do");
			
			viewPage = member.viewList(req, res);
		} else if(url.equals("/memberDetail.do")) {
			// ȸ�� ��
			System.out.println("/memberDetail.do");
			
			viewPage = member.viewDetail(req, res);
		} else if(url.equals("/bookRegisterForm.do")) {
			// �Ǹ��� > ��ǰ �԰� ���
			System.out.println("/bookRegisterForm.do");
			
			viewPage = "/book/seller/registerForm.jsp";
		} else if(url.equals("/bookRegisterPro.do")) {
			// �Ǹ��� > ��ǰ �԰�
			System.out.println("/bookRegisterPro.do");
			
			viewPage = book.registerBook(req, res);
		} else if(url.equals("/sellerBookList.do")) {
			// �Ǹ��� > ��� ���
			System.out.println("/sellerBookList.do");
			
			viewPage = book.viewList(req, res, Code.SELLER_BOOK_LIST);
		} else if(url.equals("/sellerBookDetail.do")) {
			// �Ǹ��� > ��� �� ==========> �۾� ���� ==========> ��ǰ �ı� ó��
			System.out.println("/sellerBookDetail.do");
			
			viewPage = book.viewDetail(req, res, Code.SELLER_BOOK_DETAIL);
		} else if(url.equals("/bookModifyForm.do")) {
			// �Ǹ��� > ��� ���� ���� ���
			System.out.println("/bookModifyForm.do");
			
			viewPage = book.viewDetail(req, res, Code.SELLER_BOOK_MODIFY_FORM);
		} else if(url.equals("/booModifyPro.do")) {
			// �Ǹ��� > ��� ���� ���� ==========> �۾� ����
			System.out.println("/bookModifyPro.do");
			
		} else if(url.equals("/bookDeletePro.do")) {
			// �Ǹ��� > ��� ���� ==========> �۾� ����
			System.out.println("/bookDeletePro.do");
			
		} else if(url.equals("/bookList.do")) {
			// ������ > ��ǰ ���
			System.out.println("/bookList.do");
			
			viewPage = book.viewList(req, res, Code.CUSTOMER_BOOK_LIST);
		} else if(url.equals("/bookDetail.do")) {
			// ������ > ��ǰ ��
			System.out.println("/bookDetail.do");
			
			viewPage = book.viewDetail(req, res, Code.CUSTOMER_BOOK_DETAIL);
		} else if(url.equals("/orderBookForm.do")) {
			// ������ > ��ǰ �ֹ� ���
			System.out.println("/orderBookForm.do");
			
			viewPage = book.viewDetail(req, res, Code.CUSTOMER_ORDER_FORM);
		} else if(url.equals("/orderBookPro.do")) {
			// ������ > ��ǰ �ֹ�
			System.out.println("/orderBookPro.do");
			
			viewPage = order.registerOrder(req, res);
		} else if(url.equals("/orderComplete.do")) {
			// ������ > �ֹ� �Ϸ� ������
			System.out.println("/orderComplete.do");
			
			viewPage = "/order/customer/complete.jsp";
		} else if(url.equals("/sellerOrderList.do")) {
			// �Ǹ��� > �ֹ� ���
			System.out.println("/sellerOrderList.do");
			
			viewPage = order.viewList(req, res, Code.SELLER_ORDER_LIST);
		} else if(url.equals("/sellerOrderDetail.do")) {
			// �Ǹ��� > �ֹ� ��
			System.out.println("/sellerOrderDetail.do");
			
			viewPage = order.viewDetail(req, res, Code.SELLER_ORDER_DETAIL);
		} else if(url.equals("/orderList.do")) {
			// ������ > �ֹ� ���
			System.out.println("/orderList.do");
			
			viewPage = order.viewList(req, res, Code.CUSTOMER_ORDER_LIST);
		} else if(url.equals("/orderDetail.do")) {
			// ������ > �ֹ� ��
			System.out.println("/orderDetail.do");
			
			viewPage = order.viewDetail(req, res, Code.CUSTOMER_ORDER_DETAIL);
		} else if(url.equals("/orderModifyPro.do")) {
			// �ֹ� ���� ���� ==========> �۾��� ==========> �Ǹ��ڿ� ������ ���� ��ȣ �ۿ�
		} else if(url.equals("/orderDeletePro.do")) {
			// �Ǹ��� > �ֹ� ���� ==========> �۾���
		} else if(url.equals("/cartAddPro.do")) {
			// ������ > ��ٱ��Ͽ� �߰�
			System.out.println("/cartAddPro.do");
			
			viewPage = cart.addCart(req, res);
		} else if(url.equals("/cartList.do")) {
			// ������ > ��ٱ��� ���
			System.out.println("/cartList.do");
			
			viewPage = cart.viewList(req, res);
		} else if(url.equals("/cartAction.do")) {
			// ������ > ��ٱ��Ͽ��� ���� �ֹ�/����
			System.out.println("/cartAction.do");
			
			viewPage = cart.cartAction(req, res);
		} else if(url.equals("/boardList.do")) {
			// ���� > �Խù� ��� ==========> �۾� ����
			System.out.println("/boardList.do");
		} else if(url.equals("/boardList.do")) {
			// ���� > �Խù� �� ==========> �۾� ����
			System.out.println("/boardDetail.do");
		} else if(url.equals("/boardWriteForm.do")) {
			// ���� > �Խù� �ۼ� ��� ==========> �۾� ���� 
			System.out.println("/boardWriteForm.do");
		} else if(url.equals("/boardWritePro.do")) {
			// ���� > �Խù� �ۼ� ==========> �۾� ���� ==========> ��� ó��
			System.out.println("/boardWritePro.do");
		} else if(url.equals("/boardModifyForm.do")) {
			// ���� > �Խù� ���� ��� ==========> �۾� ����
			System.out.println("/boardModifyForm.do");
		} else if(url.equals("/boardModifyPro.do")) {
			// ���� > �Խù� ���� ==========> �۾� ����
			System.out.println("/boardModifyPro.do");
		} else if(url.equals("/boardDeleteForm.do")) {
			// ���� > �Խù� ���� Ȯ�� ==========> �۾� ����
			System.out.println("/boardDeleteForm.do");
		} else if(url.equals("/boardDeletePro.do")) {
			// ���� > �Խù� ���� ==========> �۾� ����
			System.out.println("/boardDeletePro.do");
		}
		
		RequestDispatcher rd = req.getRequestDispatcher(viewPage);
		
		rd.forward(req, res);
	}

}
