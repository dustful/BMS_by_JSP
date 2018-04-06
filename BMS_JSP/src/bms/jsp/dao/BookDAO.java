package bms.jsp.dao;

public interface BookDAO {
	
	// ��ü ������ ��ȸ
	public static final String SELECT_BOOK_TOTAL = "SELECT COUNT(*) FROM BMS_BOOK";
	
	// ���� ��� ��ȸ
	public static final String SELECT_BOOK_LIST = "SELECT * FROM (SELECT BB.*, ROW_NUMBER() OVER(ORDER BY BKNO DESC) AS RN FROM BMS_BOOK BB) WHERE RN BETWEEN ? AND ?";
	
	// ���� �� ��ȸ
	public static final String SELECT_BOOK_DETAIL = "SELECT * FROM BMS_BOOK WHERE BKNO = ?";
	
	// ���� ���
	public static final String INSERT_BOOK = "INSERT INTO BMS_BOOK(BKNO, BKNAME, BKIMG, BKAUTHOR, BKPUBLISHER, BKPRICE, BKQTY, BKCONTENT) VALUES(BOOK_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
	
	// ���� ����
	public static final String UPDATE_BOOK = "UPDATE BMS_BOOK SET";
	
	// ���� ���� ����
	public static final String UPDATE_BOOK_QTY = "UPDATE BMS_BOOK SET BKQTY = ? WHERE BKNO = ?";
	
	// ���� ����
	public static final String DELETE_BOOK = "DELETE FROM BMS_BOOK WHERE BKNO = ?";
	
	public Object execQuery(Object... objs);
	public int execUpdate(Object... objs);
	
}
