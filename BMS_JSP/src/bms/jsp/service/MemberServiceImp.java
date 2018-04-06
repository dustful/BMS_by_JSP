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

	// �α���
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

	// ȸ�� ���
	@Override
	public String signup(HttpServletRequest req, HttpServletResponse res) {
		MultipartRequest mr = null;
		
		// ���ε������� �ִ������(=10MB)
		int maxSize = 10 * 1024 * 1024;
	
		// ���ε������� �����
		String saveDir = req.getRealPath("/uploadedFiles/member/");
		
		// ���ε������� �������
		String realDir = "C:\\Users\\Ȳ��ȯ\\eclipse-workspace\\BMS_JSP\\WebContent\\uploadedFiles\\member\\";

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

	// ȸ�� ���
	@Override
	public String viewList(HttpServletRequest req, HttpServletResponse res) {
		int divContent = 10; // ������ ȸ����
		int divPage = 5; // ������ ��������
		int num = 0; // ����� ȸ�� ��ȣ
		String pageNum = null; // ������ ��ȣ
		int tot = 0; // ��ü ȸ����
		int beginNum = 0; // ���� ��ȣ
		int endNum = 0; // ���� ��ȣ
		int totPage = 0; // ��������
		int currPage = 0; // ���� ������
		int beginPage = 0; // ���� ������
		int endPage = 0; // ���� ������
		MemberDAOImp dao = MemberDAOImp.getInstance();
		
		// ===== ������ ó�� ���� =====
		tot = (int) dao.execQuery(Code.SELECT_MEMBER_TOTAL, MemberDAO.SELECT_MEMBER_TOTAL); // ��ü ȸ����
		pageNum = req.getParameter("pageNum"); // ������ ��ȣ

		if(pageNum == null) pageNum = "1"; // ������ ��ȣ �ʱ�ȭ

		currPage = Integer.parseInt(pageNum); // ���� ������ ��ȣ�� ������ ��ȣ�� �ݿ�
		totPage = (tot / divContent) + (tot % divContent > 0? 1:0); // ��������
		beginNum = (currPage - 1) * divContent + 1; // ���� ��ȣ
		endNum = beginNum + divContent - 1; // ���� ��ȣ
		
		if(endNum > tot) endNum = tot;
		
		num = tot - (currPage - 1) * divContent; // ����� ȸ�� ��ȣ
		// ===== ������ ó�� ���� =====
		
		// ȸ�� ��� ���� ȣ��
		if(tot > 0) {
			ArrayList<MemberDTO> members = (ArrayList<MemberDTO>) dao.execQuery(Code.SELECT_MEMBER_LIST, MemberDAO.SELECT_MEMBER_LIST, beginNum, endNum);
			
			req.setAttribute("members", members);
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
		return "/member/list.jsp";
	}

	// ȸ�� ��
	@Override
	public String viewDetail(HttpServletRequest req, HttpServletResponse res) {
		int orgNum = Integer.parseInt(req.getParameter("orgNum"));
		MemberDAOImp dao = MemberDAOImp.getInstance();
		
		// ȸ�� �� ���� ȣ��
		MemberDTO member = (MemberDTO) dao.execQuery(Code.SELECT_MEMBER_DETAIL, MemberDAO.SELECT_MEMBER_DETAIL, orgNum);
		
		req.setAttribute("orgNum", orgNum);
		req.setAttribute("member", member);
		
		return "/member/detail.jsp";
	}

}
