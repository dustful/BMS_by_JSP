package bms.jsp.dto;

import java.sql.Date;

public class BookDTO {
	
	private int bkno; // 도서 번호
	private String bkname; // 도서명
	private String bkimg; // 도서 이미지
	private String bkauthor; // 저자
	private String bkpublisher; // 출판사
	private int bkprice; // 가격
	private int bkqty; // 재고 수량
	private String bkcontent; // 도서 소개
	private Date bkregdate; // 입고일
	
	public int getBkno() { return bkno; }
	public String getBkname() { return bkname; }
	public String getBkimg() { return bkimg; }
	public String getBkauthor() { return bkauthor; }
	public String getBkpublisher() { return bkpublisher; }
	public int getBkprice() { return bkprice; }
	public int getBkqty() { return bkqty; }
	public String getBkcontent() { return bkcontent; }
	public Date getBkregdate() { return bkregdate; }
	
	public void setBkno(int bkno) { this.bkno = bkno; }
	public void setBkname(String bkname) { this.bkname = bkname; }
	public void setBkimg(String bkimg) { this.bkimg = bkimg; }
	public void setBkauthor(String bkauthor) { this.bkauthor = bkauthor; }
	public void setBkpublisher(String bkpublisher) { this.bkpublisher = bkpublisher; }
	public void setBkprice(int bkprice) { this.bkprice = bkprice; }
	public void setBkqty(int bkqty) { this.bkqty = bkqty; }
	public void setBkcontent(String bkcontent) { this.bkcontent = bkcontent; }
	public void setBkregdate(Date bkregdate) { this.bkregdate = bkregdate; }
	
}
