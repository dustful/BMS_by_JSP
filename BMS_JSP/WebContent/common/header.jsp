<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<header>
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#headerNavbar">
						<span class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></span>
					</button>
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#searchEntire">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					</button>
					<a class="navbar-brand" href="index.do">심야서점</a>
				</div>
				
				<div class="collapse navbar-collapse navbar-left" id="headerNavbar">
					<ul class="nav navbar-nav">
						<c:if test="${sessionScope.mno == null}">
						<li><a href="bookList.do">상품목록</a></li>
						<li><a href="boardList.do">고객게시판</a></li>
						<li><a href="signinForm.do">로그인</a></li>
						</c:if>
						<c:if test="${sessionScope.mno != null}">
						<li><a href="bookList.do">상품목록</a></li>
						<li><a href="boardList.do">고객게시판</a></li>
						<li><a href="cartList.do"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> 장바구니</a></li>
						<li><a href="myPage.do"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${sessionScope.mname}</a></li>
						<li><a href="signout.do">로그아웃</a></li>
						</c:if>
					</ul>
				</div>
				
				<div class="collapse navbar-collapse navbar-right" id="searchEntire">
					<form class="navbar-form" role="search">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="검색어를 입력하세요" aria-describedby="addon-search">
							<span class="input-group-btn">
								<button type="submit" class="btn btn-default" id="addon-search">&nbsp;<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;</button>
							</span>
						</div>
					</form>
				</div>
			</div>
		</nav>
	</header>
