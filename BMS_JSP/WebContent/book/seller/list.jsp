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
	
	<section id="sellerbookList">
		<article>
			<div class="row">
				<!-- Aside -->
				<%@ include file="../../common/aside.jsp" %>
				
				<!-- 본문 내용 시작 -->
				<div class="col-md-9">
					<!-- 경로 출력 시작 -->
					<ol class="breadcrumb">
						<li><a href="myPage.do"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> 마이페이지</a></li>
						<li class="active">재고관리</li>
					</ol>
					<!-- 경로 출력 종료 -->
				
					<div class="page-header">
						<h1>재고 목록 <small>Product List</small></h1>
					</div>
					
					<div class="form-group pull-right" role="group">
						<a href="bookRegisterForm.do" class="btn btn-primary"><span class="glyphicon glyphicon-pencil"></span> 상품입고</a>
					</div>
			
					<!-- 상품 목록 출력 시작 -->
					<c:if test="${tot > 0}">
						<table class="table table-hover">
							<thead>
								<tr class="active">
									<th class="text-center"><input type="checkbox"></th>
									<th class="text-center"></th>
									<th class="text-center">도서명</th>
									<th class="text-center">저자</th>
									<th class="text-center">출판사</th>
									<th class="text-center">가격</th>
									<th class="text-center">재고수량</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="book" items="${books}">
								<tr>
									<td class="text-center"><input type="checkbox"></td>
									<td class="text-center"><img class="thumbnail img-responsive" src="/BMS_JSP/uploadedFiles/books/${book.bkimg}" alt="${book.bkimg}" style="height: 80px;"></td>
									<td><a href="sellerBookDetail.do?orgNum=${book.bkno}">${book.bkname}</a></td>
									<td class="text-center">${book.bkauthor}</td>
									<td class="text-center">${book.bkpublisher}</td>
									<td class="text-center"><fmt:formatNumber pattern="#,###" value="${book.bkprice}"/></td>
									<td class="text-center"><span class="emphasis"><fmt:formatNumber pattern="#,###" value="${book.bkqty}"/></span></td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</c:if>
					<!-- 상품 목록 출력 종료 -->
			
					<!-- 등록된 상품이 없을 때의 알림 출력 시작 -->
					<c:if test="${tot == 0}">
						<div class="panel panel-info">
							<div class="panel-heading">
								<h3 class="panel-title">알림</h3>
							</div>
							<div class="panel-body text-center">
								등록된 상품이 하나도 없습니다.
							</div>
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
				</div>
				<!-- 본문 내용 종료 -->
			</div>
		</article>	
	</section>
	
	<!-- Footer -->
	<%@ include file="../../common/footer.jsp" %>
	
	</div>

</body>
</html>