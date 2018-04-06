package bms.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bms.jsp.dto.BookDTO;
import bms.jsp.dto.OrderDTO;
import bms.jsp.service.Code;

public class OrderDAOImp implements OrderDAO {
	
	DataSource ds;
	private static OrderDAOImp instance;
	
	public static OrderDAOImp getInstance() {
		if(instance == null) instance = new OrderDAOImp();
		return instance;
	}
	
	public OrderDAOImp() {
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
				case Code.SELECT_ORDER_TOTAL:
					// 전체 주문건수 조회
				case Code.SELECT_ORDER_NEXTVAL:
					// 주문 번호 생성
					rs = pstmt.executeQuery();

					if(rs.next()) res = rs.getInt(1);
					else res = 0;
					break;
				case Code.SELECT_ORDER_LIST:
					// 주문 목록
					pstmt.setInt(1, (int) objs[2]);
					pstmt.setInt(2, (int) objs[3]);
					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						ArrayList<OrderDTO> dtos = new ArrayList<OrderDTO>((int) objs[3] - (int) objs[2] + 1);
						
						do {
							OrderDTO dto = new OrderDTO();
							
							dto.setMbno(rs.getInt("mbno"));
							dto.setMid(rs.getString("mid"));
							dto.setOdstat(rs.getInt("odstat"));
							dto.setOdregdate(rs.getDate("odregdate"));
							dto.setCntref(rs.getInt("cntref"));
							dto.setSumprice(rs.getInt("sumprice"));
							dto.setOdref(rs.getInt("odref"));
							dtos.add(dto);
							
							res = dtos;
						} while(rs.next());
					}
					break;
				case Code.SELECT_ORDER_DETAIL:
					// 주문 상세
					pstmt.setInt(1, (int) objs[2]);
					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						ArrayList<OrderDTO> dtos = new ArrayList<OrderDTO>();
						
						do {
							OrderDTO dto = new OrderDTO();
							
							dto.setOdno(rs.getInt("odno"));
							dto.setOdname(rs.getString("odname"));
							dto.setOdcontact(rs.getString("odcontact"));
							dto.setRcname(rs.getString("rcname"));
							dto.setRccontact(rs.getString("rccontact"));
							dto.setRcaddr(rs.getString("rcaddr"));
							dto.setPymd(rs.getInt("pymd"));
							dto.setBkno(rs.getInt("bkno"));
							dto.setOdqty(rs.getInt("odqty"));
							dto.setOdregdate(rs.getDate("odregdate"));
							dto.setOdstat(rs.getInt("odstat"));
							
							BookDTO dto2 = new BookDTO();
							
							dto2.setBkauthor(rs.getString("bkauthor"));
							dto2.setBkcontent(rs.getString("bkcontent"));
							dto2.setBkimg(rs.getString("bkimg"));
							dto2.setBkname(rs.getString("bkname"));
							dto2.setBkno(rs.getInt("bkno"));
							dto2.setBkprice(rs.getInt("bkprice"));
							dto2.setBkpublisher(rs.getString("bkpublisher"));
							dto2.setBkqty(rs.getInt("bkqty"));
							dto.setBookDTO(dto2);
							
							dtos.add(dto);
							res = dtos;
						} while(rs.next());
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
		OrderDTO dto = (OrderDTO) objs[2];
		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			String sql = (String) objs[1];
			pstmt = conn.prepareStatement(sql);
			
			switch((int) objs[0]) {
				case Code.INSERT_ORDER:
					// 주문 등록
					pstmt.setInt(1, (int) objs[3]);
					pstmt.setString(2, dto.getOdname());
					pstmt.setString(3, dto.getOdcontact());
					pstmt.setString(4, dto.getRcname());
					pstmt.setString(5, dto.getRccontact());
					pstmt.setString(6, dto.getRcaddr());
					pstmt.setInt(7, dto.getPymd());
					pstmt.setInt(8, dto.getBkno());
					pstmt.setInt(9, dto.getOdqty());
					pstmt.setInt(10, dto.getMbno());
					break;
				case Code.UPDATE_ORDER:
					// 주문 수정
					pstmt.setString(1, dto.getOdname());
					pstmt.setString(2, dto.getOdcontact());
					pstmt.setString(3, dto.getRcname());
					pstmt.setString(4, dto.getRccontact());
					pstmt.setString(5, dto.getRcaddr());
					pstmt.setInt(6, dto.getPymd());
					pstmt.setInt(7, dto.getBkno());
					pstmt.setInt(8, dto.getOdqty());
					pstmt.setInt(9, (int) objs[3]);
					break;
				case Code.DELETE_ORDER:
					// 주문 삭제
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
