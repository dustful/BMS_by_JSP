package bms.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bms.jsp.dto.MemberDTO;
import bms.jsp.service.Code;

public class MemberDAOImp implements MemberDAO {
	
	DataSource ds;
	private static MemberDAOImp instance;
	
	public static MemberDAOImp getInstance() {
		if(instance == null) instance = new MemberDAOImp();
		return instance;
	}
	
	public MemberDAOImp() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g_bms");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object execQuery(Object... objs) {
		Object res = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			String sql = (String) objs[1];
			pstmt = conn.prepareStatement(sql);
			
			switch((int) objs[0]) {
				case Code.SELECT_MEMBER_TOTAL:
					// 전체 회원수 조회
					rs = pstmt.executeQuery();

					if(rs.next()) res = rs.getInt(1);
					else res = 0;
					break;
				case Code.SELECT_MEMBER_LIST:
					// 회원 목록
					pstmt.setInt(1, (int) objs[2]);
					pstmt.setInt(2, (int) objs[3]);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>((int) objs[3] - (int) objs[2] + 1);
						
						do {
							MemberDTO dto = new MemberDTO();
							
							dto.setMno(rs.getInt("mno"));
							dto.setMid(rs.getString("mid"));
							dto.setMgrd(rs.getInt("mgrd"));
							dto.setMname(rs.getString("mname"));
							dto.setMprofimg(rs.getString("mprofimg"));
							dto.setMbirth(rs.getDate("mbirth"));
							dto.setMemail(rs.getString("memail"));
							dto.setMcp(rs.getString("mcp"));
							dto.setMaddr(rs.getString("maddr"));
							dto.setMmileage(rs.getInt("mmileage"));
							dto.setMbalance(rs.getInt("mbalance"));
							dto.setMregdate(rs.getDate("mregdate"));
							dto.setMrecdate(rs.getDate("mrecdate"));
							dto.setMstat(rs.getInt("mstat"));
							dtos.add(dto);
							
							res = dtos;
						} while(rs.next());
					}
					break;
				case Code.SELECT_MEMBER:
					// 회원 조회
					pstmt.setString(1, (String) objs[2]);
					pstmt.setString(2, (String) objs[3]);
					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						MemberDTO dto = new MemberDTO();
						
						dto.setMno(rs.getInt("mno"));
						dto.setMid(rs.getString("mid"));
						dto.setMgrd(rs.getInt("mgrd"));
						dto.setMname(rs.getString("mname"));
						dto.setMprofimg(rs.getString("mprofimg"));
						dto.setMbirth(rs.getDate("mbirth"));
						dto.setMemail(rs.getString("memail"));
						dto.setMcp(rs.getString("mcp"));
						dto.setMaddr(rs.getString("maddr"));
						dto.setMmileage(rs.getInt("mmileage"));
						dto.setMbalance(rs.getInt("mbalance"));
						dto.setMregdate(rs.getDate("mregdate"));
						dto.setMrecdate(rs.getDate("mrecdate"));
						dto.setMstat(rs.getInt("mstat"));
						
						res = dto;
					}
					break;
				case Code.SELECT_MEMBER_DETAIL:
					// 회원 상세
					pstmt.setInt(1, (int) objs[2]);
					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						MemberDTO dto = new MemberDTO();
						
						dto.setMno(rs.getInt("mno"));
						dto.setMid(rs.getString("mid"));
						dto.setMgrd(rs.getInt("mgrd"));
						dto.setMname(rs.getString("mname"));
						dto.setMprofimg(rs.getString("mprofimg"));
						dto.setMbirth(rs.getDate("mbirth"));
						dto.setMemail(rs.getString("memail"));
						dto.setMcp(rs.getString("mcp"));
						dto.setMaddr(rs.getString("maddr"));
						dto.setMmileage(rs.getInt("mmileage"));
						dto.setMbalance(rs.getInt("mbalance"));
						dto.setMregdate(rs.getDate("mregdate"));
						dto.setMrecdate(rs.getDate("mrecdate"));
						dto.setMstat(rs.getInt("mstat"));
						
						res = dto;
					}
					break;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException ee) {
				ee.printStackTrace();
			}
		}
		return res;
	}

	@Override
	public int execUpdate(Object... objs) {
		MemberDTO dto = null;
		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			String sql = (String) objs[1];
			pstmt = conn.prepareStatement(sql);
			
			switch((int) objs[0]) {
				case Code.INSERT_MEMBER:
					// 회원 등록
					dto = (MemberDTO) objs[2];
					
					pstmt.setString(1, dto.getMid());
					pstmt.setString(2, dto.getMpw());
					pstmt.setString(3, dto.getMname());
					pstmt.setString(4, dto.getMprofimg());
					pstmt.setDate(5, dto.getMbirth());
					pstmt.setString(6, dto.getMemail());
					pstmt.setString(7, dto.getMcp());
					pstmt.setString(8, dto.getMaddr());
					break;
				case Code.UPDATE_MEMBER:
					// 회원 수정
					dto = (MemberDTO) objs[2];
					
					pstmt.setString(1, dto.getMpw());
					pstmt.setString(2, dto.getMname());
					pstmt.setString(3, dto.getMprofimg());
					pstmt.setString(4, dto.getMemail());
					pstmt.setString(5, dto.getMcp());
					pstmt.setString(6, dto.getMaddr());
					pstmt.setInt(7, (int) objs[3]);
					break;
				case Code.UPDATE_MEMBER_STAT:
					// 회원 상태 수정
					pstmt.setInt(1, (int) objs[2]);
					pstmt.setInt(2, (int) objs[3]);
					break;
				case Code.DELETE_MEMBER:
					// 회원 삭제
					pstmt.setInt(1, (int) objs[2]);
					break;
			}
			
			res = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException ee) {
				ee.printStackTrace();
			}
		}
		return res;
	}

}
