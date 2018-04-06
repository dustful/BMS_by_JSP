<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<c:if test="${sessionScope.mno != null}">
	<!-- aside 시작 -->
	<div class="col-md-3">
		<c:choose>
			<c:when test="${sessionScope.mgrd == 0}">
			<!-- 판매자 메뉴 시작 -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title"><span class="glyphicon glyphicon-user"></span> ${sessionScope.mname}</h4>
				</div>
				<div class="panel-body">
					<p>회원 등급 : ${sessionScope.mgrd == 0? "판매자":"구매자"}</p>
					<p>최근 접속일 : ${sessionScope.mrecdate}</p>
				</div>
				<ul class="list-group">
					<li class="list-group-item"><a href="memberList.do">회원관리</a></li>
					<li class="list-group-item"><a href="sellerBookList.do">재고관리</a></li>
					<li class="list-group-item"><a href="sellerOrderList.do">주문관리</a></li>
					<li class="list-group-item"><a href="sellerBoardList.do">게시물관리</a></li>
				</ul>
			</div>
			<!-- 판매자 메뉴 종료 -->
			</c:when>
		
			<c:otherwise>
			<!-- 구매자 메뉴 시작 -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title"><span class="glyphicon glyphicon-user"></span> ${sessionScope.mname}</h4>
				</div>
				<div class="panel-body">
					<p>회원 등급 : ${sessionScope.mgrd == 0? "판매자":"구매자"}</p>
					<p>최근 접속일 : ${sessionScope.mrecdate}</p>
				</div>
				<ul class="list-group">
					<li class="list-group-item"><a href="memberDetail.do">개인정보관리</a></li>
					<li class="list-group-item"><a href="orderList.do">주문관리</a></li>
				</ul>
			</div>
			<!-- 구매자 메뉴 종료 -->
			</c:otherwise>
		</c:choose>
	</div>
	<!-- aside 종료 -->
	</c:if>