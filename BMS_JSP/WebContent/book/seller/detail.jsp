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
	
	<section id="bookDetail">
		<article>
			<div class="row">
				<!-- Aside -->
				<%@ include file="../../common/aside.jsp" %>
		
				<!-- 본문 내용 시작 -->
				<div class="col-md-9">
					<!-- 경로 출력 시작 -->
					<ol class="breadcrumb">
						<li><a href="myPage.do"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> 마이페이지</a></li>
						<li><a href="sellerBookList.do">재고관리</a></li>
						<li class="active">재고상세</li>
					</ol>
					<!-- 경로 출력 종료 -->
				
					<div class="page-header">
						<h1>재고 상세 <small>Product Detail</small></h1>
					</div>
					
					<!-- 상품 상세 출력 시작 -->
					<div class="row">
						<div class="col-xs-12 col-md-6">
							<div class="thumbnail">
								<img class="img-responsive" src="/BMS_JSP/uploadedFiles/books/${book.bkimg}" alt="${book.bkimg}" style="max-height: 360px;">
							</div>
						</div>
						<div class="col-xs-12 col-md-6">
							<div class="panel panel-default">
								<div class="panel-body">
									<h4>${book.bkname}</h4>
									<form id="bookDetailForm" method="post">
										<input type="hidden" name="orgNum" value="${book.bkno}">
										<div class="table-responsive">
											<table class="table">
												<tr>
													<td>저자</td>
													<td>${book.bkauthor}</td>
												</tr>
												<tr>
													<td>출판사</td>
													<td>${book.bkpublisher}</td>
												</tr>
												<tr>
													<td>가격</td>
													<td><span class="price"><fmt:formatNumber pattern="#,###" value="${book.bkprice}"/></span>원</td>
												</tr>
											</table>
										</div>
										<div class="btn-group btn-group-justified">
											<div class="btn-group">
												<button class="btn btn-primary" type="button" id="bookModifyBtn" onClick="location.href = 'bookModifyForm.do?orgNum=${book.bkno}'">
													<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 재고정보수정
												</button>
											</div>
											<div class="btn-group">
												<button class="btn btn-danger" type="button" id="bookDeleteBtn">
													<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 재고삭제
												</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
					<ul class="nav nav-tabs">
						<li role="presentation" class="active"><a href="#">도서소개</a></li>
						<li role="presentation"><a href="#">상품후기관리</a></li>
					</ul>
					<div class="container" style="padding: 20px;">${book.bkcontent}</div>
					<!-- 상품 상세 출력 종료 -->
					
					<!-- 등록된 상품 후기가 없을 때의 알림 출력 시작 -->
					<c:if test="${tot == 0}">
						<div class="panel panel-info">
							<div class="panel-heading">
								<h3 class="panel-title">알림</h3>
							</div>
							<div class="panel-body text-center">등록된 상품 후기가 하나도 없습니다.</div>
						</div>
					</c:if>
					<!-- 등록된 상품 후기가 없을 때의 알림 출력 종료 -->
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