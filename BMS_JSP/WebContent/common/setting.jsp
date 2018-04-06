<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Viewport -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- JQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- JQuery Validation -->
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- Bootstrap Theme -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"> -->
<!-- Bootstrap Javascript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<!-- Bootstrap Tooltip -->
<script>
$(function(){
	$("[data-toggle='tooltip']").tooltip();
})
</script>

<!-- AngularJS -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script> -->

<!-- User Defined JS -->
<script src="common/script.js"></script>
<!-- User Defined CSS -->
<!-- <link rel="stylesheet" type="text/css" href="/BMS_JSP/style/common.css"> -->

<!-- Shortcut Icon -->
<!-- <link rel="shortcut icon" href="/BMS_JSP/images/favicon2.ico"/> -->

<c:set var="titleBar" value="심야서점 - 우주 관련 서적 전문 온라인 쇼핑몰" />

<!-- LotteMartHappy Web Font -->
<style type="text/css">
body {
	padding-top: 72px;
}

@font-face {
	font-family: 'LotteMartHappy';
	font-style: normal;
	font-weight: 400;
	src: url('//cdn.jsdelivr.net/korean-webfonts/1/corps/lottemart/LotteMartHappy/LotteMartHappyMedium.woff2') format('woff2'),
		url('//cdn.jsdelivr.net/korean-webfonts/1/corps/lottemart/LotteMartHappy/LotteMartHappyMedium.woff') format('woff');
}

@font-face {
	font-family: 'LotteMartHappy';
	font-style: normal;
	font-weight: 700;
	src: url('//cdn.jsdelivr.net/korean-webfonts/1/corps/lottemart/LotteMartHappy/LotteMartHappyBold.woff2') format('woff2'),
		url('//cdn.jsdelivr.net/korean-webfonts/1/corps/lottemart/LotteMartHappy/LotteMartHappyBold.woff') format('woff');
}

* { font-family: 'LotteMartHappy', sans-serif; }
.lottemarthappy * { font-family: 'LotteMartHappy', sans-serif; }
.daehan * { font-family: 'Daehan', serif; }
.normal { font-style: normal; }
.w250 { font-weight: 250; }
.w300 { font-weight: 300; }
.w350 { font-weight: 350; }
.w400 { font-weight: 400; }
.w500 { font-weight: 500; }
.w700 { font-weight: 700; }
.w800 { font-weight: 800; }
.w900 { font-weight: 900; }

/* li span {
	line-height: 1.8;
	font-size: 1.3em;
} */

.price,
.emphasis {
	color: red;
	font-weight: 900;
	font-size: large;
}
</style>