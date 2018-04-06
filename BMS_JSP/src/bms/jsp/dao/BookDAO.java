package bms.jsp.dao;

public interface BookDAO {
	
	// 전체 도서수 조회
	public static final String SELECT_BOOK_TOTAL = "SELECT COUNT(*) FROM BMS_BOOK";
	
	// 도서 목록 조회
	public static final String SELECT_BOOK_LIST = "SELECT * FROM (SELECT BB.*, ROW_NUMBER() OVER(ORDER BY BKNO DESC) AS RN FROM BMS_BOOK BB) WHERE RN BETWEEN ? AND ?";
	
	// 도서 상세 조회
	public static final String SELECT_BOOK_DETAIL = "SELECT * FROM BMS_BOOK WHERE BKNO = ?";
	
	// 도서 등록
	public static final String INSERT_BOOK = "INSERT INTO BMS_BOOK(BKNO, BKNAME, BKIMG, BKAUTHOR, BKPUBLISHER, BKPRICE, BKQTY, BKCONTENT) VALUES(BOOK_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
	
	// 도서 수정
	public static final String UPDATE_BOOK = "UPDATE BMS_BOOK SET";
	
	// 도서 수량 수정
	public static final String UPDATE_BOOK_QTY = "UPDATE BMS_BOOK SET BKQTY = ? WHERE BKNO = ?";
	
	// 도서 삭제
	public static final String DELETE_BOOK = "DELETE FROM BMS_BOOK WHERE BKNO = ?";
	
	public Object execQuery(Object... objs);
	public int execUpdate(Object... objs);
	
}
