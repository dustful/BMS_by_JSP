<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/setting.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${titleBar}</title>
</head>
<body>

	<div class="container">
	
	<!-- Header -->
	<%@ include file="../common/header.jsp" %>
	
	<section id="memberList">
		<article>
			<div class="row">
				<!-- Aside -->
				<%@ include file="../../common/aside.jsp" %>
				
				<!-- 본문 내용 시작 -->
				<div class="col-md-9">
					<!-- 경로 출력 시작 -->
					<ol class="breadcrumb">
						<li><a href="myPage.do"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> 마이페이지</a></li>
						<li class="active">회원관리</li>
					</ol>
					<!-- 경로 출력 종료 -->
		
					<div class="page-header">
						<h1>회원 목록 <small>Member List</small></h1>
					</div>
			
					<!-- 회원 목록 출력 시작 -->
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
									<th class="text-center">회원아이디</th>
									<th class="text-center">회원명</th>
									<th class="text-center">가입일</th>
									<th class="text-center">상태</th>
									<th class="text-center"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="member" items="${members}">
									<tr>
										<td class="text-center">
											${num}
											<c:set var="num" value="${num - 1}" />
										</td>
										<td class="text-center">${member.mid}</td>
										<td class="text-center">${member.mname}</td>
										<td class="text-center"><fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${member.mregdate}"/></td>
										<td class="text-center">
											<c:choose>
												<c:when test="${member.mstat == 1}">정상</c:when>
												<c:when test="${member.mstat == 2}">탈퇴</c:when>
											</c:choose>
										</td>
										<td class="text-center">
											<button class="btn btn-primary" type="button" data-toggle="tooltip" data-placement="top" title="상세보기" onClick="location.href = 'memberDetail.do?orgNum=${member.mno}'">
												<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
											</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					<!-- 회원 목록 출력 종료 -->
			
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
	<%@ include file="../common/footer.jsp" %>
	
	</div>

</body>
</html>