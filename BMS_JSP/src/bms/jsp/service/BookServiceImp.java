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
	
	// ���� ���
	@Override
	public String registerBook(HttpServletRequest req, HttpServletResponse res) {
		MultipartRequest mr = null;
		
		// ���ε� ������ �ִ� ������(=10MB)
		int maxSize = 10 * 1024 * 1024;
	
		// ���ε� ������ �� ���
		String saveDir = req.getRealPath("/uploadedFiles/books/");
		
		// ���ε� ������ ���� ���
		String realDir = "C:\\Users\\Ȳ��ȯ\\eclipse-workspace\\BMS_JSP\\WebContent\\uploadedFiles\\books\\";

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

	// ���� ���
	@Override
	public String viewList(HttpServletRequest req, HttpServletResponse res, int pageCode) {
		int divContent = 10; // ������ ������
		int divPage = 5; // ������ ��������
		int num = 0; // ����� ���� ��ȣ
		String pageNum = null; // ������ ��ȣ
		int tot = 0; // ��ü ������
		int beginNum = 0; // ���� ��ȣ
		int endNum = 0; // ���� ��ȣ
		int totPage = 0; // ��������
		int currPage = 0; // ���� ������
		int beginPage = 0; // ���� ������
		int endPage = 0; // ���� ������
		BookDAOImp dao = BookDAOImp.getInstance();
		
		// ===== ������ ó�� ���� =====
		tot = (int) dao.execQuery(Code.SELECT_BOOK_TOTAL, BookDAO.SELECT_BOOK_TOTAL); // ��ü ������
		pageNum = req.getParameter("pageNum"); // ������ ��ȣ

		if(pageNum == null) pageNum = "1"; // ������ ��ȣ �ʱ�ȭ

		currPage = Integer.parseInt(pageNum); // ���� ������ ��ȣ�� ������ ��ȣ�� �ݿ�
		totPage = (tot / divContent) + (tot % divContent > 0? 1:0); // ��������
		beginNum = (currPage - 1) * divContent + 1; // ���� ��ȣ
		endNum = beginNum + divContent - 1; // ���� ��ȣ
		
		if(endNum > tot) endNum = tot;
		
		num = tot - (currPage - 1) * divContent; // ����� ���� ��ȣ
		// ===== ������ ó�� ���� =====
		
		// ���� ��� ���� ȣ��
		if(tot > 0) {
			ArrayList<BookDTO> books = (ArrayList<BookDTO>) dao.execQuery(Code.SELECT_BOOK_LIST, BookDAO.SELECT_BOOK_LIST, beginNum, endNum);
			
			req.setAttribute("books", books);
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
			case Code.SELLER_BOOK_LIST:
				ret = "/book/seller/list.jsp";
				break;
			case Code.CUSTOMER_BOOK_LIST:
				ret = "/book/customer/list.jsp";
				break;
		}
		return ret;
	}

	// ���� ��
	@Override
	public String viewDetail(HttpServletRequest req, HttpServletResponse res, int pageCode) {
		int orgNum = Integer.parseInt(req.getParameter("orgNum"));
		BookDAOImp dao = BookDAOImp.getInstance();
		HashMap<Integer, BookDTO> books = new HashMap<Integer, BookDTO>();
		int includedDeliveryCharge = 50000; // ��ۺ� �����ϴ� ���� �ֹ���
		int deliveryCharge = 3000; // ��ۺ�
		int totPrice = 0;
		
		// ���� �� ���� ȣ��
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
				// �� ���� �ݾ��� ��ۺ� �����ϴ� ���� �ֹ������� ���� ���, ��ۺ� �߰�
				totPrice = book.getBkprice();
				
				if(totPrice < includedDeliveryCharge) req.setAttribute("deliveryCharge", deliveryCharge);
				else req.setAttribute("deliveryCharge", 0);
				
				// �ֹ� ��ȣ ����
				OrderDAOImp oddao = OrderDAOImp.getInstance();
				int nextv = (int) oddao.execQuery(Code.SELECT_ORDER_NEXTVAL, OrderDAO.SELECT_ORDER_NEXTVAL);

				// ��ǰ ���� ����
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
