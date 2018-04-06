package bms.jsp.dao;

public interface MemberDAO {
	
	// ��ü ȸ���� ��ȸ
	public static final String SELECT_MEMBER_TOTAL = "SELECT COUNT(*) FROM BMS_MEMBER";
	
	// ȸ�� ��� ��ȸ
	public static final String SELECT_MEMBER_LIST = "SELECT * FROM (SELECT BM.*, ROW_NUMBER() OVER(ORDER BY MNO DESC) AS RN FROM BMS_MEMBER BM) WHERE RN BETWEEN ? AND ?";
	
	// ȸ�� ��ȸ
	public static final String SELECT_MEMBER = "SELECT * FROM BMS_MEMBER WHERE MID = ? AND MPW = ?";
	
	// ȸ�� �� ��ȸ
	public static final String SELECT_MEMBER_DETAIL = "SELECT * FROM BMS_MEMBER WHERE MNO = ?";
	
	// ȸ�� ���
	public static final String INSERT_MEMBER = "INSERT INTO BMS_MEMBER(MNO, MID, MPW, MNAME, MPROFIMG, MBIRTH, MEMAIL, MCP, MADDR) VALUES(MEMBER_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	// ȸ�� ����
	public static final String UPDATE_MEMBER = "UPDATE BMS_MEMBER SET MPW = ?, MNAME = ?, MPROFIMG = ?, MEMAIL = ?, MCP = ?, MADDR = ? WHERE MNO = ?";
	
	// ȸ�� ���� ����
	public static final String UPDATE_MEMBER_STAT = "UPDATE BMS_MEMBER SET MSTAT = ? WHERE MNO = ?";
	
	// ȸ�� ����
	public static final String DELETE_MEMBER = "DELETE FROM BMS_MEMBER WHERE MNO = ?";
	
	public Object execQuery(Object... objs);
	public int execUpdate(Object... objs);
	
}
