<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page isErrorPage="true" %>
<html>
<head>
<%@ include file="./common/setting.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${titleBar}</title>
</head>
<body>

	<div class="container">
		<div class="alert alert-danger">
			<h1 style="margin-top: 0;">에러 코드 : 404</h1>
			<p>서버가 요청한 페이지를 찾을 수 없습니다.</p>
		</div>
	</div>

</body>
</html>