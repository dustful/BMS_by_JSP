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
 * ========== 프로젝트 개요 ==========
 * 명칭 : BMS JSP(Book Management System for Bookstore)
 * 내용 : JSP 기반 온라인 회원제 서점용 도서 관리 시스템
 * 개발자 : 황성환(dustful@naver.com)
 * 
 * 
 * ========== 작업 내역 ==========
 * 2017.09.30
 * 메인 페이지 개선(상품 분류별 목록 처리)
 * 상품 후기/문의 게시판 추가
 * 구매자 > 장바구니 > 체크 박스를 선택하지 않고 버튼 클릭시 오류 처리
 * 
 * 2017.09.29
 * 구매자 > 로그인/아웃, 회원 가입/탈퇴, 개인 정보 열람/수정
 * 판매자 > 회원 등급/상태 변경, 회원 삭제
 * 
 * 2017.09.28
 * 판매자, 구매자 > 주문 상세 > 주문에 대한 상태 처리(주문 취소/결제 확인/발송 완료/환불 요청/환불 승인)
 * 
 * 2017.09.27
 * 구매자 > 상품 상세 > 즉시 구입, 장바구니에 추가
 * 구매자 > 장바구니 > 장바구니에서 주문/제거
 * 판매자, 구매자 > 주문 목록/상세
 * 
 * 2017.09.26
 * 판매자 > 상품 등록 > 유효성 검사
 * 판매자, 구매자 > 상품 목록/상세
 * 
 * 
 * ========== 향후 개선 방향 ==========
 * 상품 정보에 적립율, 할인 적용
 * 결제 방식에 예치금, 쿠폰 적용
 * Oracle 4000 byte 이상 데이터 처리
 * 보안 처리(비밀 번호 암호화 등)
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
		// 유니코드 문자 인코딩
		req.setCharacterEncoding("UTF-8");
		
		/*뷰 페이지 경로관련 변수 설정 시작*/
		String viewPage = null;
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String url = uri.substring(contextPath.length());
		/*뷰 페이지 경로관련 변수 설정 종료*/
		
		if(url.equals("/index.do")) {
			// 메인 페이지 ==========> 작업중 ==========> 상품 분류에 따라 목록 출력
			System.out.println("/index.do");
			
			viewPage = "/index.jsp";
		} else if(url.equals("/myPage.do")) {
			// 마이 페이지 ==========> 작업중
			System.out.println("/myPage.do");
			
			viewPage = "/myPage.jsp";
		} else if(url.equals("/signinForm.do")) {
			// 로그인 양식
			System.out.println("/signinForm.do");
			
			viewPage = "/member/signinForm.jsp";
		} else if(url.equals("/signinPro.do")) {
			// 로그인
			System.out.println("/signinPro.do");
			
			viewPage = member.signin(req, res);
		} else if(url.equals("/findMemberInfo.do")) {
			// 아이디/비밀 번호 찾기 ==========> 작업 예정
			System.out.println("/findMemberInfo.do");
		} else if(url.equals("/signout.do")) {
			// 로그아웃
			System.out.println("/signout.do");
			req.getSession().invalidate(); // 비지니스 로직으로 이동 예정
			
			viewPage = "index.do";
		} else if(url.equals("/signupForm.do")) {
			// 회원 가입 양식 ==========> 작업중 ==========> 유효성 검사
			System.out.println("/signupForm.do");
			
			viewPage = "/member/signupForm.jsp";
		} else if(url.equals("/signupPro.do")) {
			// 회원 가입 ==========> 작업중
			System.out.println("/signupPro.do");
			
			viewPage = member.signup(req, res);
		} else if(url.equals("/memberModifyForm.do")) {
			// 회원 정보 수정 ==========> 작업 예정
			System.out.println("/memberModifyForm.do");
		} else if(url.equals("/optoutForm.do")) {
			// 회원 탈퇴 확인 ==========> 작업중
			System.out.println("/optoutForm.do");
			
			viewPage = "/member/optoutForm.jsp";
		} else if(url.equals("/optoutPro.do")) {
			// 회원 탈퇴 ==========> 작업 예정
			System.out.println("/optoutPro.do");
		} else if(url.equals("/memberList.do")) {
			// 회원 목록
			System.out.println("/memberList.do");
			
			viewPage = member.viewList(req, res);
		} else if(url.equals("/memberDetail.do")) {
			// 회원 상세
			System.out.println("/memberDetail.do");
			
			viewPage = member.viewDetail(req, res);
		} else if(url.equals("/bookRegisterForm.do")) {
			// 판매자 > 상품 입고 양식
			System.out.println("/bookRegisterForm.do");
			
			viewPage = "/book/seller/registerForm.jsp";
		} else if(url.equals("/bookRegisterPro.do")) {
			// 판매자 > 상품 입고
			System.out.println("/bookRegisterPro.do");
			
			viewPage = book.registerBook(req, res);
		} else if(url.equals("/sellerBookList.do")) {
			// 판매자 > 재고 목록
			System.out.println("/sellerBookList.do");
			
			viewPage = book.viewList(req, res, Code.SELLER_BOOK_LIST);
		} else if(url.equals("/sellerBookDetail.do")) {
			// 판매자 > 재고 상세 ==========> 작업 예정 ==========> 상품 후기 처리
			System.out.println("/sellerBookDetail.do");
			
			viewPage = book.viewDetail(req, res, Code.SELLER_BOOK_DETAIL);
		} else if(url.equals("/bookModifyForm.do")) {
			// 판매자 > 재고 정보 수정 양식
			System.out.println("/bookModifyForm.do");
			
			viewPage = book.viewDetail(req, res, Code.SELLER_BOOK_MODIFY_FORM);
		} else if(url.equals("/booModifyPro.do")) {
			// 판매자 > 재고 정보 수정 ==========> 작업 예정
			System.out.println("/bookModifyPro.do");
			
		} else if(url.equals("/bookDeletePro.do")) {
			// 판매자 > 재고 삭제 ==========> 작업 예정
			System.out.println("/bookDeletePro.do");
			
		} else if(url.equals("/bookList.do")) {
			// 구매자 > 상품 목록
			System.out.println("/bookList.do");
			
			viewPage = book.viewList(req, res, Code.CUSTOMER_BOOK_LIST);
		} else if(url.equals("/bookDetail.do")) {
			// 구매자 > 상품 상세
			System.out.println("/bookDetail.do");
			
			viewPage = book.viewDetail(req, res, Code.CUSTOMER_BOOK_DETAIL);
		} else if(url.equals("/orderBookForm.do")) {
			// 구매자 > 상품 주문 양식
			System.out.println("/orderBookForm.do");
			
			viewPage = book.viewDetail(req, res, Code.CUSTOMER_ORDER_FORM);
		} else if(url.equals("/orderBookPro.do")) {
			// 구매자 > 상품 주문
			System.out.println("/orderBookPro.do");
			
			viewPage = order.registerOrder(req, res);
		} else if(url.equals("/orderComplete.do")) {
			// 구매자 > 주문 완료 페이지
			System.out.println("/orderComplete.do");
			
			viewPage = "/order/customer/complete.jsp";
		} else if(url.equals("/sellerOrderList.do")) {
			// 판매자 > 주문 목록
			System.out.println("/sellerOrderList.do");
			
			viewPage = order.viewList(req, res, Code.SELLER_ORDER_LIST);
		} else if(url.equals("/sellerOrderDetail.do")) {
			// 판매자 > 주문 상세
			System.out.println("/sellerOrderDetail.do");
			
			viewPage = order.viewDetail(req, res, Code.SELLER_ORDER_DETAIL);
		} else if(url.equals("/orderList.do")) {
			// 구매자 > 주문 목록
			System.out.println("/orderList.do");
			
			viewPage = order.viewList(req, res, Code.CUSTOMER_ORDER_LIST);
		} else if(url.equals("/orderDetail.do")) {
			// 구매자 > 주문 상세
			System.out.println("/orderDetail.do");
			
			viewPage = order.viewDetail(req, res, Code.CUSTOMER_ORDER_DETAIL);
		} else if(url.equals("/orderModifyPro.do")) {
			// 주문 정보 수정 ==========> 작업중 ==========> 판매자와 구매자 간의 상호 작용
		} else if(url.equals("/orderDeletePro.do")) {
			// 판매자 > 주문 삭제 ==========> 작업중
		} else if(url.equals("/cartAddPro.do")) {
			// 구매자 > 장바구니에 추가
			System.out.println("/cartAddPro.do");
			
			viewPage = cart.addCart(req, res);
		} else if(url.equals("/cartList.do")) {
			// 구매자 > 장바구니 목록
			System.out.println("/cartList.do");
			
			viewPage = cart.viewList(req, res);
		} else if(url.equals("/cartAction.do")) {
			// 구매자 > 장바구니에서 선택 주문/제거
			System.out.println("/cartAction.do");
			
			viewPage = cart.cartAction(req, res);
		} else if(url.equals("/boardList.do")) {
			// 공통 > 게시물 목록 ==========> 작업 예정
			System.out.println("/boardList.do");
		} else if(url.equals("/boardList.do")) {
			// 공통 > 게시물 상세 ==========> 작업 예정
			System.out.println("/boardDetail.do");
		} else if(url.equals("/boardWriteForm.do")) {
			// 공통 > 게시물 작성 양식 ==========> 작업 예정 
			System.out.println("/boardWriteForm.do");
		} else if(url.equals("/boardWritePro.do")) {
			// 공통 > 게시물 작성 ==========> 작업 예정 ==========> 댓글 처리
			System.out.println("/boardWritePro.do");
		} else if(url.equals("/boardModifyForm.do")) {
			// 공통 > 게시물 수정 양식 ==========> 작업 예정
			System.out.println("/boardModifyForm.do");
		} else if(url.equals("/boardModifyPro.do")) {
			// 공통 > 게시물 수정 ==========> 작업 예정
			System.out.println("/boardModifyPro.do");
		} else if(url.equals("/boardDeleteForm.do")) {
			// 공통 > 게시물 삭제 확인 ==========> 작업 예정
			System.out.println("/boardDeleteForm.do");
		} else if(url.equals("/boardDeletePro.do")) {
			// 공통 > 게시물 삭제 ==========> 작업 예정
			System.out.println("/boardDeletePro.do");
		}
		
		RequestDispatcher rd = req.getRequestDispatcher(viewPage);
		
		rd.forward(req, res);
	}

}
