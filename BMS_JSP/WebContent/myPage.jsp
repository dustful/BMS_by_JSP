<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="./common/setting.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${titleBar}</title>
</head>
<body>

	<div class="container">
	
	<!-- Header -->
	<%@ include file="./common/header.jsp" %>
	
	<section id="myPage">
		<article>
			<div class="row">
				<!-- Aside -->
				<%@ include file="./common/aside.jsp" %>
				
				<!-- 본문 내용 시작 -->
				<div class="col-md-9">
					<!-- 경로 출력 시작 -->
					<ol class="breadcrumb">
						<li class="active"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> 마이페이지</li>
					</ol>
					<!-- 경로 출력 종료 -->
					
					<div class="page-header">
						<h1>마이 페이지 <small>My Page</small></h1>
					</div>
					
					<div class="jumbotron">
						<h2>환영합니다!</h2>
						<p>언제나 즐거운 심야서점의 마이 페이지입니다.</p>
					</div>
				</div>
				<!-- 본문 내용 종료 -->
			</div>
		</article>
	</section>
	
	<!-- Footer -->
	<%@ include file="./common/footer.jsp" %>
	
	</div>

</body>
</html>