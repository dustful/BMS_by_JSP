package bms.jsp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MemberService {

	// 로그인
	public String signin(HttpServletRequest req, HttpServletResponse res);
	
	// 회원 등록
	public String signup(HttpServletRequest req, HttpServletResponse res);
	
	// 회원 수정
	// public String modifyMember(HttpServletRequest req, HttpServletResponse res);
	
	// 회원 삭제
	// public String optout(HttpServletRequest req, HttpServletResponse res);
	
	// 회원 목록
	public String viewList(HttpServletRequest req, HttpServletResponse res);
	
	// 회원 상세
	public String viewDetail(HttpServletRequest req, HttpServletResponse res);

}
