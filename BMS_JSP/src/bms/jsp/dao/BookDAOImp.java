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
import bms.jsp.service.Code;

public class BookDAOImp implements BookDAO {
	
	DataSource ds;
	private static BookDAOImp instance;
	
	public static BookDAOImp getInstance() {
		if(instance == null) instance = new BookDAOImp();
		return instance;
	}
	
	public BookDAOImp() {
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
				case Code.SELECT_BOOK_TOTAL:
					// 전체 도서수 조회
					rs = pstmt.executeQuery();

					if(rs.next()) res = rs.getInt(1);
					else res = 0;
					break;
				case Code.SELECT_BOOK_LIST:
					// 도서 목록
					pstmt.setInt(1, (int) objs[2]);
					pstmt.setInt(2, (int) objs[3]);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						ArrayList<BookDTO> dtos = new ArrayList<BookDTO>((int) objs[3] - (int) objs[2] + 1);
						
						do {
							BookDTO dto = new BookDTO();
							
							dto.setBkno(rs.getInt("bkno"));
							dto.setBkname(rs.getString("bkname"));
							dto.setBkimg(rs.getString("bkimg"));
							dto.setBkauthor(rs.getString("bkauthor"));
							dto.setBkpublisher(rs.getString("bkpublisher"));
							dto.setBkcontent(rs.getString("bkcontent"));
							dto.setBkprice(rs.getInt("bkprice"));
							dto.setBkqty(rs.getInt("bkqty"));
							dto.setBkregdate(rs.getDate("bkregdate"));
							dtos.add(dto);
							
							res = dtos;
						} while(rs.next());
					}
					break;
				case Code.SELECT_BOOK_DETAIL:
					// 도서 상세
					pstmt.setInt(1, (int) objs[2]);
					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						BookDTO dto = new BookDTO();
						
						dto.setBkno(rs.getInt("bkno"));
						dto.setBkname(rs.getString("bkname"));
						dto.setBkimg(rs.getString("bkimg"));
						dto.setBkauthor(rs.getString("bkauthor"));
						dto.setBkpublisher(rs.getString("bkpublisher"));
						dto.setBkcontent(rs.getString("bkcontent"));
						dto.setBkprice(rs.getInt("bkprice"));
						dto.setBkqty(rs.getInt("bkqty"));
						dto.setBkregdate(rs.getDate("bkregdate"));
						
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
		BookDTO dto = null;
		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			String sql = (String) objs[1];
			pstmt = conn.prepareStatement(sql);
			
			switch((int) objs[0]) {
				case Code.INSERT_BOOK:
					// 도서 등록
					dto = (BookDTO) objs[2];
					
					pstmt.setString(1, dto.getBkname());
					pstmt.setString(2, dto.getBkimg());
					pstmt.setString(3, dto.getBkauthor());
					pstmt.setString(4, dto.getBkpublisher());
					pstmt.setInt(5, dto.getBkprice());
					pstmt.setInt(6, dto.getBkqty());
					pstmt.setString(7, dto.getBkcontent());
					break;
				case Code.UPDATE_BOOK:
					// 도서 수정
					dto = (BookDTO) objs[2];
					
					pstmt.setString(1, dto.getBkname());
					pstmt.setString(2, dto.getBkimg());
					pstmt.setString(3, dto.getBkauthor());
					pstmt.setString(4, dto.getBkpublisher());
					pstmt.setInt(5, dto.getBkprice());
					pstmt.setInt(6, dto.getBkqty());
					pstmt.setString(7, dto.getBkcontent());
					pstmt.setInt(8, (int) objs[3]);
					break;
				case Code.UPDATE_BOOK_QTY:
					// 도서 수량 수정
					pstmt.setInt(1, (int) objs[2]);
					pstmt.setInt(2, (int) objs[3]);
					break;
				case Code.DELETE_BOOK:
					// 도서 삭제
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
