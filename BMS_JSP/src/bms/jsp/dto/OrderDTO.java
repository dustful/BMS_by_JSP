package bms.jsp.dto;

import java.sql.Date;

public class OrderDTO {
	
	private int odno; // 주문 번호
	private int mbno; // 회원 번호
	private String mid; // 회원 아이디
	private String odname; // 주문자명
	private String odcontact; // 주문자 연락처
	private String rcname; // 수신자명
	private String rccontact; // 수신자 연락처
	private String rcaddr; // 수신자 주소
	private int pymd; // 결제 방식
	private int bkno; // 도서 번호
	private int odqty; // 주문 수량
	private Date odregdate; // 주문일
	private int cntref; // 주문건수
	private int sumprice; // 주문 금액
	private int odref; // 참고 주문 번호
	private BookDTO bookDTO; // BookDTO 참조 변수
	private int odstat; // 주문 상태
	/*
	 * ===== 주문 상태 정보 =====
	 * 0 : 주문 완료
	 * 1 : 취소된 주문
	 * 2 : 결제 확인
	 * 3 : 발송 처리
	 * 4 : 배송 완료
	 * 5 : 환불 대기
	 * 6 : 환불 완료
	 */
	
	public int getOdno() { return odno; }
	public int getMbno() { return mbno; }
	public String getMid() { return mid; }
	public String getOdname() { return odname; }
	public String getOdcontact() { return odcontact; }
	public String getRcname() { return rcname; }
	public String getRccontact() { return rccontact; }
	public String getRcaddr() { return rcaddr; }
	public int getPymd() { return pymd; }
	public int getBkno() { return bkno; }
	public int getOdqty() { return odqty; }
	public Date getOdregdate() { return odregdate; }
	public int getCntref() { return cntref; }
	public int getSumprice() { return sumprice; }
	public int getOdref() { return odref; }
	public BookDTO getBookDTO() { return bookDTO; }
	public int getOdstat() { return odstat; }

	public void setOdno(int odno) { this.odno = odno; }
	public void setMbno(int mbno) { this.mbno = mbno; }
	public void setMid(String mid) { this.mid= mid; }
	public void setOdname(String odname) { this.odname = odname; }
	public void setOdcontact(String odcontact) { this.odcontact = odcontact; }
	public void setRcname(String rcname) { this.rcname = rcname; }
	public void setRccontact(String rccontact) { this.rccontact = rccontact; }
	public void setRcaddr(String rcaddr) { this.rcaddr = rcaddr; }
	public void setPymd(int pymd) { this.pymd = pymd; }
	public void setBkno(int bkno) { this.bkno = bkno; }
	public void setOdqty(int odqty) { this.odqty = odqty; }
	public void setOdregdate(Date odregdate) { this.odregdate = odregdate; }
	public void setCntref(int cntref) { this.cntref = cntref; }
	public void setSumprice(int sumprice) { this.sumprice = sumprice; }
	public void setOdref(int odref) { this.odref = odref; }
	public void setBookDTO(BookDTO bookDTO) { this.bookDTO = bookDTO; }
	public void setOdstat(int odstat) { this.odstat = odstat; }
	
}
