package bms.jsp.service;

public interface Code {
	
	/*
	 * 판매자 관련 코드
	 */
	public static final int SELLER_BOOK_LIST = 10; // 상품 목록
	public static final int SELLER_BOOK_DETAIL = 11; // 상품 상세
	public static final int SELLER_BOOK_MODIFY_FORM = 12; // 상품 수정 양식
	public static final int SELLER_ORDER_LIST = 13; // 주문 목록
	public static final int SELLER_ORDER_DETAIL = 14; // 주문 상세
	
	/*
	 * 구매자 관련 코드
	 */
	public static final int CUSTOMER_BOOK_LIST = 20; // 상품 목록
	public static final int CUSTOMER_BOOK_DETAIL = 21; // 상품 상세
	public static final int CUSTOMER_ORDER_FORM = 22; // 주문 양식
	public static final int CUSTOMER_ORDER_LIST = 23; // 주문 목록
	public static final int CUSTOMER_ORDER_DETAIL = 24; // 주문 상세
	
	/*
	 * 도서 관련 코드
	 */
	public static final int SELECT_BOOK_TOTAL = 100; // 전체 도서수 조회
	public static final int SELECT_BOOK_LIST = 101; // 도서 목록 조회
	public static final int SELECT_BOOK_DETAIL = 102; // 도서 상세 조회
	public static final int INSERT_BOOK = 103; // 도서 등록
	public static final int UPDATE_BOOK = 104; // 도서 수정
	public static final int UPDATE_BOOK_QTY = 105; // 도서 수량 수정
	public static final int DELETE_BOOK = 106; // 도서 삭제
	
	/*
	 * 주문 관련 코드
	 */
	public static final int SELECT_ORDER_TOTAL = 200; // 전체 주문 건수 조회
	public static final int SELECT_ORDER_LIST = 201; // 주문 목록 조회
	public static final int SELECT_ORDER_DETAIL = 202; // 주문 상세 조회
	public static final int SELECT_ORDER_NEXTVAL = 203; // 주문 번호 생성
	public static final int INSERT_ORDER = 204; // 주문 등록
	public static final int UPDATE_ORDER = 205; // 주문 수정
	public static final int DELETE_ORDER = 206; // 주문 삭제
	
	/*
	 * 회원 관련 코드
	 */
	public static final int SELECT_MEMBER_TOTAL = 300; // 전체 회원수 조회
	public static final int SELECT_MEMBER_LIST = 301; // 회원 목록 조회
	public static final int SELECT_MEMBER = 302; // 회원 조회
	public static final int SELECT_MEMBER_DETAIL = 303; // 회원 상세 조회
	public static final int INSERT_MEMBER = 304; // 회원 등록
	public static final int UPDATE_MEMBER = 305; // 회원 수정
	public static final int UPDATE_MEMBER_STAT = 306; // 회원 상태 수정
	public static final int DELETE_MEMBER = 307; // 회원 삭제
	
	/*
	 * 게시판 관련 코드
	 */
	public static final int SELECT_BOARD_TOTAL = 400; // 전체 게시물수 조회
	public static final int SELECT_BOARD_LIST = 401; // 게시물 목록 조회
	public static final int SELECT_BOARD_DETAIL = 402; // 게시물 상세 조회
	public static final int INSERT_BOARD = 403; // 게시물 등록
	public static final int UPDATE_BOARD = 404; // 게시물 수정
	public static final int DELETE_BOARD = 405; // 게시물 삭제

}
