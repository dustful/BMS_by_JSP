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
	
	<section id="orderList">
		<article>
			<div class="row">
				<!-- Aside -->
				<%@ include file="../../common/aside.jsp" %>
				
				<!-- 본문 내용 시작 -->
				<div class="col-md-9">
					<!-- 경로 출력 시작 -->
					<ol class="breadcrumb">
						<li><a href="myPage.do"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> 마이페이지</a></li>
						<li class="active">주문관리</li>
					</ol>
					<!-- 경로 출력 종료 -->
		
					<div class="page-header">
						<h1>주문 목록 <small>Order List</small></h1>
					</div>
			
					<!-- 주문 목록 출력 시작 -->
					<c:if test="${tot > 0}">
						<table class="table table-hover">
							<colgroup>
								<col width="10%">
								<col width="20%">
								<col width="20%">
								<col width="20%">
								<col width="20%">
								<col width="10%">
							</colgroup>
							<thead>
								<tr class="active">
									<th class="text-center">순번</th>
									<th class="text-center">주문번호</th>
									<th class="text-center">주문금액</th>
									<th class="text-center">주문일</th>
									<th class="text-center">주문상태</th>
									<th class="text-center"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="order" items="${orders}">
									<tr>
										<td class="text-center">
											${num}
											<c:set var="num" value="${num - 1}" />
										</td>
										<td class="text-center">${order.odref}</td>
										<td class="text-center"><fmt:formatNumber pattern="#,###" value="${order.sumprice}"/></td>
										<td class="text-center"><fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${order.odregdate}"/></td>
										<td class="text-center">
											<c:choose>
												<c:when test="${order.odstat == 0}">주문 완료</c:when>
												<c:when test="${order.odstat == 1}">취소된 주문</c:when>
												<c:when test="${order.odstat == 2}">결제 확인</c:when>
												<c:when test="${order.odstat == 3}">발송 처리</c:when>
												<c:when test="${order.odstat == 4}">배송 완료</c:when>
												<c:when test="${order.odstat == 5}">환불 대기</c:when>
												<c:when test="${order.odstat == 6}">환불 완료</c:when>
											</c:choose>
										</td>
										<td class="text-center">
											<button class="btn btn-primary" type="button" data-toggle="tooltip" data-placement="top" title="상세보기" onClick="location.href = 'orderDetail.do?orgNum=${order.odref}'">
												<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
											</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					<!-- 주문 목록 출력 종료 -->
			
					<!-- 요청된 주문이 없을 때의 알림 출력 시작 -->
					<c:if test="${tot == 0}">
						<div class="panel panel-info">
							<div class="panel-heading">
								<h3 class="panel-title">알림</h3>
							</div>
							<div class="panel-body text-center">요청된 주문이 하나도 없습니다.</div>
						</div>
					</c:if>
					<!-- 요청된 주문이 없을 때의 알림 출력 종료 -->
					
					<!-- 페이지네이션 출력 시작 -->
					<nav class="text-center">
						<c:if test="${tot > 0}">
							<ul class="pagination">
								<!-- 처음/이전 블록 -->
								<c:if test="${beginPage > divPage}">
									<li><a href="orderList.do" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
									<li><a href="orderList.do?pageNum=${beginPage - divPage}" aria-label="Previous"><span aria-hidden="true">&lt;</span></a></li>
								</c:if>
				
								<c:forEach var="i" begin="${beginPage}" end="${endPage}">
									<c:if test="${i == currPage}">
										<li><span><b>${i}</b></span></li>
									</c:if>
									<c:if test="${i != currPage}">
										<li><a href="orderList.do?pageNum=${i}">${i}</a></li>
									</c:if>
								</c:forEach>
								
								<!-- 다음/이후 블록 -->
								<c:if test="${totPage > endPage}">
									<li><a href="orderList.do?pageNum=${beginPage + divPage}" aria-label="Next"><span aria-hidden="true">&gt;</span></a></li>
									<li><a href="orderList.do?pageNum=${totPage}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
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