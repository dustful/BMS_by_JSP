package bms.jsp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BookService {

	// 도서 등록
	public String registerBook(HttpServletRequest req, HttpServletResponse res);
	
	// 도서 수정
	// public String modifyBook(HttpServletRequest req, HttpServletResponse res);
	
	// 도서 삭제
	// public String deleteBook(HttpServletRequest req, HttpServletResponse res);
	
	// 도서 목록
	public String viewList(HttpServletRequest req, HttpServletResponse res, int pageCode);
	
	// 도서 상세
	public String viewDetail(HttpServletRequest req, HttpServletResponse res, int pageCode);

}
