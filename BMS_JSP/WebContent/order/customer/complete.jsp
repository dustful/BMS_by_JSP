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
	
	<section id="orderComplete">
		<article>
			<div class="jumbotron">
				<h2>주문이 완료되었습니다.</h2>
				<p>자세한 사항은 주문내역에서 확인하세요.</p>
				<button class="btn btn-primary" type="button" onClick="location.href = 'orderList.do'">주문내역확인</button>
				<button class="btn btn-default" type="button" onClick="location.href = 'bookList.do'">쇼핑 계속하기</button>
			</div>
		</article>
	</section>
	
	<!-- Footer -->
	<%@ include file="../../common/footer.jsp" %>
	
	</div>

</body>
</html>