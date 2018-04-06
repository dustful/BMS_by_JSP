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
	
	<section id="bookList">
		<article>
			<!-- 본문 내용 시작 -->
			<!-- 경로 출력 시작 -->
			<ol class="breadcrumb">
				<li><a href="index.do"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> 메인페이지</a></li>
				<li class="active">상품목록</li>
			</ol>
			<!-- 경로 출력 종료 -->
		
			<div class="page-header">
				<h1>상품 목록 <small>Product List</small></h1>
			</div>
			
			<!-- 상품 목록 출력 시작 -->
			<c:if test="${tot > 0}">
				<div class="row">
					<c:forEach var="book" items="${books}">
						<div class="col-xs-6 col-md-3">
							<div class="panel panel-default">
								<%-- <div class="panel-heading">
									<h3 class="panel-title">${book.bkname}</h3>
								</div> --%>
								<div class="panel-body">
									<div class="thumbnail" style="border: 0;">
										<a href="bookDetail.do?orgNum=${book.bkno}">
											<img class="img-responsive" src="/BMS_JSP/uploadedFiles/books/${book.bkimg}" alt="${book.bkimg}" style="height: 160px;">
										</a>
									</div>
									<div class="caption">
										<%-- <fmt:formatNumber pattern="#,###" value="${book.bkprice}"/>원 --%>
										<a href="bookDetail.do?orgNum=${book.bkno}">${book.bkname}</a>
										<c:if test="${book.bkqty == 0}">
											<span class="label label-danger">품절</span>
										</c:if>
										<p><span class="price"><fmt:formatNumber pattern="#,###" value="${book.bkprice}"/></span>원</p>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:if>
			<!-- 상품 목록 출력 종료 -->
			
			<!-- 등록된 상품이 없을 때의 알림 출력 시작 -->
			<c:if test="${tot == 0}">
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