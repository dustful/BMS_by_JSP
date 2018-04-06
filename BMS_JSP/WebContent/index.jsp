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
	
	<section id="main">
		<article>
			<!-- 본문 내용 시작 -->
			<div class="jumbotron">
				<h1>심야서점</h1>
				<p>심야서점에서 즐거운 시간과 함께 하세요.</p>
				<button class="btn btn-primary" type="button" onClick="location.href = 'bookList.do'">신상품 보러가기</button>
			</div>
			<!-- 본문 내용 종료 -->
		</article>
	</section>
	
	<!-- Footer -->
	<%@ include file="./common/footer.jsp" %>
	
	</div>

</body>
</html>