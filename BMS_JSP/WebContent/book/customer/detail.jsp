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
			<!-- 본문 내용 시작 -->
			<!-- 경로 출력 시작 -->
			<ol class="breadcrumb">
				<li><a href="index.do"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> 메인페이지</a></li>
				<li><a href="bookList.do">상품목록</a></li>
				<li class="active">상품상세</li>
			</ol>
			<!-- 경로 출력 종료 -->
		
			<div class="page-header">
				<h1>상품 상세 <small>Product Detail</small></h1>
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
							<h4>
								${book.bkname}
								<c:if test="${book.bkqty == 0}"><span class="label label-danger">품절</span></c:if>
							</h4>
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
								<c:if test="${sessionScope.mno == null}">
								<div class="btn-group btn-group-justified">
									<div class="btn-group">
										<button class="btn btn-primary" type="button" onClick="location.href = 'signinForm.do'">구입을 원하시면 로그인 해주세요</button>
									</div>
								</div>
								</c:if>
								<c:if test="${sessionScope.mno != null}">
								<div class="btn-group btn-group-justified">
									<c:if test="${book.bkqty > 0}">
										<div class="btn-group">
											<button class="btn btn-primary" type="button" id="buyNowBtn">
												<span class="glyphicon glyphicon-credit-card" aria-hidden="true"></span> 즉시구입
											</button>
										</div>
										<div class="btn-group">
											<button class="btn btn-primary" type="button" id="addCartBtn">
												<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> 장바구니에 추가
											</button>
										</div>
									</c:if>
									<c:if test="${book.bkqty == 0}">
										<div class="btn-group">
											<button class="btn btn-danger btn-lg active" type="button" disabled>품절</button>
										</div>
									</c:if>
								</div>
								</c:if>
							</form>
						</div>
					</div>
				</div>
			</div>
			<ul class="nav nav-tabs">
				<li role="presentation" class="active"><a href="#">도서소개</a></li>
				<li role="presentation"><a href="#">상품후기</a></li>
				<li role="presentation"><a href="#">배송안내</a></li>
			</ul>
			<div class="container" style="padding: 20px;">${book.bkcontent}</div>
			<!-- 상품 상세 출력 종료 -->
			
			<!-- 결과 반환 변수에 의한 알림 출력 시작 -->
			<c:if test="${result == 1}">
				<script type="text/javascript">
					$(function() {
						$("#resultModal .modal-body").text("상품이 장바구니에 추가되었습니다.");
						$("#resultModal").modal("show");
					});
				</script>
			</c:if>
			<c:if test="${result == 2}">
				<script>
					$(function() {
						$("#resultModal .modal-body").text("이미 장바구니에 추가된 상품입니다.");
						$("#resultModal").modal("show");
					});
				</script>
			</c:if>
			
			<div class="modal fade" id="resultModal" tabindex="-1" role="dialog" aria-labelledby="resultModalTitle" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="resultModalTitle">알림</h4>
						</div>
						<div class="modal-body"></div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">계속 쇼핑하기</button>
							<button type="button" class="btn btn-primary" onClick="location.href = 'cartList.do'">장바구니로 이동</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 결과 반환 변수에 의한 알림 출력 종료 -->
			
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