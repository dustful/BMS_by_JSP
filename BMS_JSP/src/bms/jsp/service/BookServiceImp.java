package bms.jsp.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bms.jsp.dao.BookDAO;
import bms.jsp.dao.BookDAOImp;
import bms.jsp.dao.OrderDAO;
import bms.jsp.dao.OrderDAOImp;
import bms.jsp.dto.BookDTO;

public class BookServiceImp implements BookService {
	
	private static BookServiceImp instance;
	
	public static BookServiceImp getInstance() {
		if(instance == null) instance = new BookServiceImp();
		return instance;
	}
	
	// 도서 등록
	@Override
	public String registerBook(HttpServletRequest req, HttpServletResponse res) {
		MultipartRequest mr = null;
		
		// 업로드 파일의 최대 사이즈(=10MB)
		int maxSize = 10 * 1024 * 1024;
	
		// 업로드 파일의 논리 경로
		String saveDir = req.getRealPath("/uploadedFiles/books/");
		
		// 업로드 파일의 물리 경로
		String realDir = "C:\\Users\\황성환\\eclipse-workspace\\BMS_JSP\\WebContent\\uploadedFiles\\books\\";

		String encType = "UTF-8";

		try {
			mr = new MultipartRequest(req, saveDir, maxSize, encType, new DefaultFileRenamePolicy());
			FileInputStream fis = new FileInputStream(saveDir + mr.getFilesystemName("bkimg"));
			FileOutputStream fos = new FileOutputStream(realDir + mr.getFilesystemName("bkimg"));
			int data = 0;
			
			while ((data = fis.read()) != -1) {
				fos.write(data);
			}

			fis.close();
			fos.close();

			BookDTO dto = new BookDTO();
			
			dto.setBkname(mr.getParameter("bkname"));
			dto.setBkimg(mr.getFilesystemName("bkimg"));
			dto.setBkauthor(mr.getParameter("bkauthor"));
			dto.setBkpublisher(mr.getParameter("bkpublisher"));
			dto.setBkprice(Integer.parseInt(mr.getParameter("bkprice")));
			dto.setBkqty(Integer.parseInt(mr.getParameter("bkqty")));
			dto.setBkcontent(mr.getParameter("bkcontent").replace("\r\n", "<br/>"));
			
			BookDAOImp dao = BookDAOImp.getInstance();
			int result = dao.execUpdate(Code.INSERT_BOOK, BookDAO.INSERT_BOOK, dto);
			
			req.setAttribute("result", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "bookRegisterForm.do";
	}

	// 도서 목록
	@Override
	public String viewList(HttpServletRequest req, HttpServletResponse res, int pageCode) {
		int divContent = 10; // 보여줄 도서수
		int divPage = 5; // 보여줄 페이지수
		int num = 0; // 출력할 도서 번호
		String pageNum = null; // 페이지 번호
		int tot = 0; // 전체 도서수
		int beginNum = 0; // 시작 번호
		int endNum = 0; // 종료 번호
		int totPage = 0; // 페이지수
		int currPage = 0; // 현재 페이지
		int beginPage = 0; // 시작 페이지
		int endPage = 0; // 종료 페이지
		BookDAOImp dao = BookDAOImp.getInstance();
		
		// ===== 페이지 처리 시작 =====
		tot = (int) dao.execQuery(Code.SELECT_BOOK_TOTAL, BookDAO.SELECT_BOOK_TOTAL); // 전체 도서수
		pageNum = req.getParameter("pageNum"); // 페이지 번호

		if(pageNum == null) pageNum = "1"; // 페이지 번호 초기화

		currPage = Integer.parseInt(pageNum); // 현재 페이지 번호에 페이지 번호를 반영
		totPage = (tot / divContent) + (tot % divContent > 0? 1:0); // 페이지수
		beginNum = (currPage - 1) * divContent + 1; // 시작 번호
		endNum = beginNum + divContent - 1; // 종료 번호
		
		if(endNum > tot) endNum = tot;
		
		num = tot - (currPage - 1) * divContent; // 출력할 도서 번호
		// ===== 페이지 처리 종료 =====
		
		// 도서 목록 정보 호출
		if(tot > 0) {
			ArrayList<BookDTO> books = (ArrayList<BookDTO>) dao.execQuery(Code.SELECT_BOOK_LIST, BookDAO.SELECT_BOOK_LIST, beginNum, endNum);
			
			req.setAttribute("books", books);
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
			case Code.SELLER_BOOK_LIST:
				ret = "/book/seller/list.jsp";
				break;
			case Code.CUSTOMER_BOOK_LIST:
				ret = "/book/customer/list.jsp";
				break;
		}
		return ret;
	}

	// 도서 상세
	@Override
	public String viewDetail(HttpServletRequest req, HttpServletResponse res, int pageCode) {
		int orgNum = Integer.parseInt(req.getParameter("orgNum"));
		BookDAOImp dao = BookDAOImp.getInstance();
		HashMap<Integer, BookDTO> books = new HashMap<Integer, BookDTO>();
		int includedDeliveryCharge = 50000; // 배송비를 포함하는 최저 주문가
		int deliveryCharge = 3000; // 배송비
		int totPrice = 0;
		
		// 도서 상세 정보 호출
		BookDTO book = (BookDTO) dao.execQuery(Code.SELECT_BOOK_DETAIL, BookDAO.SELECT_BOOK_DETAIL, orgNum);
		
		req.setAttribute("orgNum", orgNum);
		
		String ret = null;
		
		switch(pageCode) {
			case Code.SELLER_BOOK_DETAIL:
				req.setAttribute("book", book);
				ret = "/book/seller/detail.jsp";
				break;
			case Code.SELLER_BOOK_MODIFY_FORM:
				req.setAttribute("book", book);
				ret = "/book/seller/modifyForm.jsp";
				break;
			case Code.CUSTOMER_BOOK_DETAIL:
				req.setAttribute("book", book);
				ret = "/book/customer/detail.jsp";
				break;
			case Code.CUSTOMER_ORDER_FORM:
				// 총 결제 금액이 배송비를 포함하는 최저 주문가보다 작을 경우, 배송비 추가
				totPrice = book.getBkprice();
				
				if(totPrice < includedDeliveryCharge) req.setAttribute("deliveryCharge", deliveryCharge);
				else req.setAttribute("deliveryCharge", 0);
				
				// 주문 번호 생성
				OrderDAOImp oddao = OrderDAOImp.getInstance();
				int nextv = (int) oddao.execQuery(Code.SELECT_ORDER_NEXTVAL, OrderDAO.SELECT_ORDER_NEXTVAL);

				// 상품 정보 추출
				book.setBkqty(1);
				books.put(orgNum, book);
				
				req.setAttribute("totPrice", totPrice);
				req.setAttribute("nextv", nextv);
				req.setAttribute("books", books);
				ret = "/order/customer/orderForm.jsp";
				break;
		}
		return ret;
	}

}
