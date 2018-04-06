package bms.jsp.dao;

public interface OrderDAO {
	
	// 전체 주문건수 조회
	public static final String SELECT_ORDER_TOTAL = "SELECT COUNT(*) FROM BMS_ORDER WHERE ROWID IN (SELECT MAX(ROWID) FROM BMS_ORDER GROUP BY ODREF)";
	
	// 주문 목록
	public static final String SELECT_ORDER_LIST = "SELECT * FROM (SELECT BO.ODREF, BO.MBNO, BO.ODSTAT, COUNT(BO.ODREF) CNTREF, SUM(BB.BKPRICE * BO.ODQTY) SUMPRICE, BO.ODREGDATE, ROW_NUMBER() OVER(ORDER BY BO.ODREGDATE DESC) AS RN FROM BMS_ORDER BO JOIN BMS_BOOK BB ON BO.BKNO = BB.BKNO GROUP BY BO.ODREF, BO.ODREGDATE, BO.ODSTAT, BO.MBNO HAVING COUNT(BO.ODREF) > 0) ODRES JOIN BMS_MEMBER BM ON ODRES.MBNO = BM.MNO WHERE RN BETWEEN ? AND ?";
	
	// 주문 상세
	public static final String SELECT_ORDER_DETAIL = "SELECT * FROM BMS_ORDER BO JOIN BMS_BOOK BB ON BO.BKNO = BB.BKNO WHERE BO.ODREF = ?";
	
	// 주문 번호 생성
	public static final String SELECT_ORDER_NEXTVAL = "SELECT ORDER_SEQ.NEXTVAL FROM DUAL";
	
	// 주문 등록
	public static final String INSERT_ORDER = "INSERT INTO BMS_ORDER(ODNO, ODREF, ODNAME, ODCONTACT, RCNAME, RCCONTACT, RCADDR, PYMD, BKNO, ODQTY, MBNO) VALUES(ORDER_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	// 주문 상태 수정
	public static final String UPDATE_ORDER = "UPDATE BMS_ORDER SET ODSTAT = ?";
	
	// 주문 삭제
	public static final String DELETE_ORDER = "DELETE FROM BMS_ORDER WHERE ODNO = ?";
	
	public Object execQuery(Object... objs);
	public int execUpdate(Object... objs);
	
}
