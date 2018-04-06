<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../../common/setting.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${titleBar}</title>
</head>
<body>

	<div class="container">
	
	<!-- Header -->
	<%@ include file="../../common/header.jsp" %>
	
	<section id="cartList">
		<article>
			<!-- 본문 내용 시작 -->
			<!-- 경로 출력 시작 -->
			<ol class="breadcrumb">
				<li><a href="index.do"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> 메인페이지</a></li>
				<li class="active">장바구니</li>
			</ol>
			<!-- 경로 출력 종료 -->
		
			<div class="page-header">
				<h1>장바구니 <small>Cart List</small></h1>
			</div>
			
			<div class="alert alert-warning" role="alert">
				<span class="glyphicon glyphicon-alert" aria-hidden="true"></span> 장바구니는 30일간 보관됩니다.
			</div>
			
			<!-- 장바구니 목록 출력 시작 -->
			<c:if test="${fn:length(sessionScope.cart2) > 0}">
				<form action="cartAction.do" method="post" id="cartForm">
					<input type="hidden" name="cartAction" id="cartAction">
					<div class="hidden-xs pull-right" style="margin-bottom: 20px;">
						<button class="btn btn-primary" type="button" id="orderAllOfThem">
							<span class="glyphicon glyphicon-credit-card" aria-hidden="true"></span> 전체 주문
						</button>
						<button class="btn btn-primary" type="button" id="orderSelected">
							<span class="glyphicon glyphicon-credit-card" aria-hidden="true"></span> 선택한 상품을 주문
						</button>
						<button class="btn btn-danger" type="button" id="removeToCart">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 선택한 상품을 장바구니에서 제거
						</button>
					</div>
					<table class="table table-hover">
						<colgroup>
							<col width="5%">
							<!-- <col width="10%"> -->
							<col width="15%">
							<col width="20%">
							<col width="15%">
							<col width="15%">
							<col width="15%">
							<col width="15%">
						</colgroup>
						<thead>
							<tr class="active">
								<th class="text-center"><input type="checkbox" id="check_all"></th>
								<!-- <th class="text-center"></th> -->
								<th class="text-center"></th>
								<th class="text-center">도서명</th>
								<th class="text-center hidden-xs">저자</th>
								<th class="text-center hidden-xs">출판사</th>
								<th class="text-center">가격</th>
								<th class="text-center">수량</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="book" items="${carts}">
								<tr>
									<td class="text-center"><input type="checkbox" class="check_each" name="check_each" value="${book.key}"></td>
									<%-- <td class="text-center">${num} <c:set var="num" value="${num - 1}" /></td> --%>
									<td class="text-center">
										<div class="thumbnail">
											<a href="bookDetail.do?orgNum=${book.value.bkno}">
												<img src="/BMS_JSP/uploadedFiles/books/${book.value.bkimg}" alt="${book.value.bkimg}" class="img-responsive" style="height: 120px;">
											</a>
										</div>
									</td>
									<td class="text-center">${book.value.bkname}</td>
									<td class="text-center hidden-xs">${book.value.bkauthor}</td>
									<td class="text-center hidden-xs">${book.value.bkpublisher}</td>
									<td class="text-center"><fmt:formatNumber pattern="#,###" value="${book.value.bkprice}"/></td>
									<td class="text-center"><input type="number" min="1" max="${book.value.bkqty}" step="1" name="odqty" value="1"></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="hidden-sm hidden-md hidden-lg" style="margin-bottom: 20px;">
						<button class="btn btn-primary btn-lg btn-block" type="button" id="orderAllOfThem">
							<span class="glyphicon glyphicon-credit-card" aria-hidden="true"></span> 전체 주문
						</button>
						<button class="btn btn-success btn-block" type="button" id="orderSelected">
							<span class="glyphicon glyphicon-credit-card" aria-hidden="true"></span> 선택한 상품을 주문
						</button>
						<button class="btn btn-danger btn-block" type="button" id="removeToCart">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 선택한 상품을 장바구니에서 제거
						</button>
					</div>
				</form>
			</c:if>
			<!-- 장바구니 목록 출력 종료 -->
			
			<!-- 등록된 상품이 없을 때의 알림 출력 시작 -->
			<c:if test="${fn:length(sessionScope.cart2) == 0}">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">알림</h3>
					</div>
					<div class="panel-body text-center">등록된 상품이 하나도 없습니다.</div>
				</div>
			</c:if>
			<!-- 등록된 상품이 없을 때의 알림 출력 종료 -->
			
			<!-- 페이지네이션 출력 시작 -->
			<nav class="text-center">
				<c:if test="${tot > 0}">
					<ul class="pagination">
						<!-- 처음/이전 블록 -->
						<c:if test="${beginPage > divPage}">
							<li><a href="bookList.do" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
							<li><a href="bookList.do?pageNum=${beginPage - divPage}" aria-label="Previous"><span aria-hidden="true">&lt;</span></a></li>
						</c:if>
		
						<c:forEach var="i" begin="${beginPage}" end="${endPage}">
							<c:if test="${i == currPage}">
								<li><span><b>${i}</b></span></li>
							</c:if>
							<c:if test="${i != currPage}">
								<li><a href="bookList.do?pageNum=${i}">${i}</a></li>
							</c:if>
						</c:forEach>
						
						<!-- 다음/이후 블록 -->
						<c:if test="${totPage > endPage}">
							<li><a href="bookList.do?pageNum=${beginPage + divPage}" aria-label="Next"><span aria-hidden="true">&gt;</span></a></li>
							<li><a href="bookList.do?pageNum=${totPage}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
						</c:if>
					</ul>
				</c:if>
			</nav>
			<!-- 페이지네이션 출력 종료 -->
			<!-- 본문 내용 종료 -->
		</article>	
	</section>
	
	<!-- Footer -->
	<%@ include file="../../common/footer.jsp" %>
	
	</div>

</body>
</html>