package bms.jsp.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bms.jsp.dao.MemberDAO;
import bms.jsp.dao.MemberDAOImp;
import bms.jsp.dto.MemberDTO;

public class MemberServiceImp implements MemberService {
	
	private static MemberServiceImp instance;
	
	public static MemberServiceImp getInstance() {
		if(instance == null) instance = new MemberServiceImp();
		return instance;
	}

	// 로그인
	@Override
	public String signin(HttpServletRequest req, HttpServletResponse res) {
		String mid = req.getParameter("mid");
		String mpw = req.getParameter("mpw");
		
		MemberDAO dao = MemberDAOImp.getInstance();
		MemberDTO dto = (MemberDTO) dao.execQuery(Code.SELECT_MEMBER, MemberDAO.SELECT_MEMBER, mid, mpw);
		
		req.getSession().setAttribute("mno", dto.getMno());
		req.getSession().setAttribute("mid", dto.getMid());
		req.getSession().setAttribute("mgrd", dto.getMgrd());
		req.getSession().setAttribute("mname", dto.getMname());
		req.getSession().setAttribute("mprofimg", dto.getMprofimg());
		req.getSession().setAttribute("mrecdate", dto.getMrecdate());
		
		return "index.do";
	}

	// 회원 등록
	@Override
	public String signup(HttpServletRequest req, HttpServletResponse res) {
		MultipartRequest mr = null;
		
		// 업로드파일의 최대사이즈(=10MB)
		int maxSize = 10 * 1024 * 1024;
	
		// 업로드파일의 논리경로
		String saveDir = req.getRealPath("/uploadedFiles/member/");
		
		// 업로드파일의 물리경로
		String realDir = "C:\\Users\\황성환\\eclipse-workspace\\BMS_JSP\\WebContent\\uploadedFiles\\member\\";

		String encType = "UTF-8";

		try {
			mr = new MultipartRequest(req, saveDir, maxSize, encType, new DefaultFileRenamePolicy());
			FileInputStream fis = new FileInputStream(saveDir + mr.getFilesystemName("mprofimg"));
			FileOutputStream fos = new FileOutputStream(realDir + mr.getFilesystemName("mprofimg"));
			int data = 0;
			
			while ((data = fis.read()) != -1) {
				fos.write(data);
			}

			fis.close();
			fos.close();

			MemberDTO dto = new MemberDTO();
			
			dto.setMid(mr.getParameter("mid"));
			dto.setMpw(mr.getParameter("mpw"));
			dto.setMname(mr.getParameter("mname"));
			dto.setMbirth(Date.valueOf(mr.getParameter("mbirth")));
			dto.setMemail(mr.getParameter("memail"));
			dto.setMcp(mr.getParameter("mcp"));
			dto.setMaddr(mr.getParameter("maddr1") + " " + mr.getParameter("maddr2"));
			dto.setMprofimg(mr.getFilesystemName("mprofimg"));
			
			MemberDAOImp dao = MemberDAOImp.getInstance();
			int result = dao.execUpdate(Code.INSERT_MEMBER, MemberDAO.INSERT_MEMBER, dto);
			
			req.setAttribute("result", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/member/complete.jsp";
	}

	// 회원 목록
	@Override
	public String viewList(HttpServletRequest req, HttpServletResponse res) {
		int divContent = 10; // 보여줄 회원수
		int divPage = 5; // 보여줄 페이지수
		int num = 0; // 출력할 회원 번호
		String pageNum = null; // 페이지 번호
		int tot = 0; // 전체 회원수
		int beginNum = 0; // 시작 번호
		int endNum = 0; // 종료 번호
		int totPage = 0; // 페이지수
		int currPage = 0; // 현재 페이지
		int beginPage = 0; // 시작 페이지
		int endPage = 0; // 종료 페이지
		MemberDAOImp dao = MemberDAOImp.getInstance();
		
		// ===== 페이지 처리 시작 =====
		tot = (int) dao.execQuery(Code.SELECT_MEMBER_TOTAL, MemberDAO.SELECT_MEMBER_TOTAL); // 전체 회원수
		pageNum = req.getParameter("pageNum"); // 페이지 번호

		if(pageNum == null) pageNum = "1"; // 페이지 번호 초기화

		currPage = Integer.parseInt(pageNum); // 현재 페이지 번호에 페이지 번호를 반영
		totPage = (tot / divContent) + (tot % divContent > 0? 1:0); // 페이지수
		beginNum = (currPage - 1) * divContent + 1; // 시작 번호
		endNum = beginNum + divContent - 1; // 종료 번호
		
		if(endNum > tot) endNum = tot;
		
		num = tot - (currPage - 1) * divContent; // 출력할 회원 번호
		// ===== 페이지 처리 종료 =====
		
		// 회원 목록 정보 호출
		if(tot > 0) {
			ArrayList<MemberDTO> members = (ArrayList<MemberDTO>) dao.execQuery(Code.SELECT_MEMBER_LIST, MemberDAO.SELECT_MEMBER_LIST, beginNum, endNum);
			
			req.setAttribute("members", members);
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
		return "/member/list.jsp";
	}

	// 회원 상세
	@Override
	public String viewDetail(HttpServletRequest req, HttpServletResponse res) {
		int orgNum = Integer.parseInt(req.getParameter("orgNum"));
		MemberDAOImp dao = MemberDAOImp.getInstance();
		
		// 회원 상세 정보 호출
		MemberDTO member = (MemberDTO) dao.execQuery(Code.SELECT_MEMBER_DETAIL, MemberDAO.SELECT_MEMBER_DETAIL, orgNum);
		
		req.setAttribute("orgNum", orgNum);
		req.setAttribute("member", member);
		
		return "/member/detail.jsp";
	}

}
