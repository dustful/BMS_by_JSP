package bms.jsp.dto;

import java.sql.Date;

public class MemberDTO {
	
	private int mno; // 회원 번호
	private String mid; // 아이디
	private String mpw; // 비밀 번호
	private int mgrd; // 회원 등급
	private String mname; // 이름
	private String mprofimg; // 프로필 이미지
	private Date mbirth; // 생일
	private String memail; // 이메일
	private String mcp; // 휴대 전화 번호
	private String maddr; // 주소
	private int mmileage; // 적립금
	private int mbalance; // 예치금
	private Date mregdate; // 가입일
	private Date mrecdate; // 최근 접속일
	private int mstat; // 상태
	/*
	 * ===== 회원 등급 정보 =====
	 * 0 : 판매자
	 * 1 : 구매자
	 * 
	 * ===== 회원 상태 정보 =====
	 * 0 : 미가입(실재하지 않음)
	 * 1 : 정상(가입)
	 * 2 : 탈퇴
	 */
	
	public int getMno() { return mno; }
	public String getMid() { return mid; }
	public String getMpw() { return mpw; }
	public int getMgrd() { return mgrd; }
	public String getMname() { return mname; }
	public String getMprofimg() { return mprofimg; }
	public Date getMbirth() { return mbirth; }
	public String getMemail() { return memail; }
	public String getMcp() { return mcp; }
	public String getMaddr() { return maddr; }
	public int getMmileage() { return mmileage; }
	public int getMbalance() { return mbalance; }
	public Date getMregdate() { return mregdate; }
	public Date getMrecdate() { return mrecdate; }
	public int getMstat() { return mstat; }
	
	public void setMid(String mid) { this.mid = mid; }
	public void setMno(int mno) { this.mno = mno; }
	public void setMpw(String mpw) { this.mpw = mpw; }
	public void setMgrd(int mgrd) { this.mgrd = mgrd; }
	public void setMname(String mname) { this.mname = mname; }
	public void setMprofimg(String mprofimg) { this.mprofimg = mprofimg; }
	public void setMbirth(Date mbirth) { this.mbirth = mbirth; }
	public void setMemail(String memail) { this.memail = memail; }
	public void setMcp(String mcp) { this.mcp = mcp; }
	public void setMaddr(String maddr) { this.maddr = maddr; }
	public void setMmileage(int mmileage) { this.mmileage = mmileage; }
	public void setMbalance(int mbalance) { this.mbalance = mbalance; }
	public void setMregdate(Date mregdate) { this.mregdate = mregdate; }
	public void setMrecdate(Date mrecdate) { this.mrecdate = mrecdate; }
	public void setMstat(int mstat) { this.mstat = mstat; }
	
}
