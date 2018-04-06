package bms.jsp.dto;

import java.sql.Date;

public class CartDTO {
	
	private int ctno; // ��ٱ��� ��ȣ
	private int mbno; // ȸ�� ��ȣ
	private int bkno; // ���� ��ȣ
	private BookDTO bkdto; // ���� ����
	private Date ctregdate; // �����
	
	public int getCtno() { return ctno; }
	public int getMbno() { return mbno; }
	public int getBkno() { return bkno; }
	public BookDTO getBkdto() { return bkdto; }
	public Date getCtregdate() { return ctregdate; }
	
	public void setCtno(int ctno) { this.ctno = ctno; }
	public void setMbno(int mbno) { this.mbno = mbno; }
	public void setBkno(int bkno) { this.bkno = bkno; }
	public void setBkdto(BookDTO bkdto) { this.bkdto = bkdto; }
	public void setCtregdate(Date ctregdate) { this.ctregdate = ctregdate; }
	
}
