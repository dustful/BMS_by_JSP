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
	
	<section id="signin">
		<article>
			<!-- 본문 내용 시작 -->
			<!-- 경로 출력 시작 -->
			<ol class="breadcrumb">
				<li><a href="index.do"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> 메인페이지</a></li>
				<li class="active">로그인</li>
			</ol>
			<!-- 경로 출력 종료 -->
			
			<div class="page-header">
				<h1>로그인<small> Login</small></h1>
			</div>
			
			<div class="panel panel-info center-block hidden-xs" style="max-width: 50%;">
				<div class="panel-heading"><h4 class="panel-title">로그인</h4></div>
				<div class="panel-body">
					<form action="signinPro.do" method="post">
						<div class="form-group">
							<label for="mid">아이디</label>
							<input type="text" name="mid" id="mid" placeholder="아이디를 입력하세요" class="form-control" autofocus required>
						</div>
						<div class="form-group">
							<label for="mpw">비밀번호</label>
							<input type="password" name="mpw" id="mpw" placeholder="비밀번호를 입력하세요" class="form-control" required>
						</div>
						<div class="btn-group btn-group-justified" role="group">
							<div class="btn-group">
								<button class="btn btn-primary" type="submit">
									<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 확인
								</button>
							</div>
							<div class="btn-group">
								<button class="btn btn-default" type="button" onClick="location.href = 'signupForm.do'">
									<span class="glyphicon glyphicon-user" aria-hidden="true"></span> 회원가입
								</button>
							</div>
							<div class="btn-group">
								<button class="btn btn-default" type="button">
									<span class="glyphicon glyphicon-search" aria-hidden="true"></span> 아이디/비밀번호 찾기
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			
			<div class="panel panel-info center-block visible-xs">
				<div class="panel-heading"><h4 class="panel-title">로그인</h4></div>
				<div class="panel-body">
					<form action="signinPro.do" method="post">
						<div class="form-group">
							<label for="mid-xs">아이디</label>
							<input type="text" name="mid" id="mid-xs" placeholder="아이디를 입력하세요" class="form-control">
						</div>
						<div class="form-group">
							<label for="mpw-xs">비밀번호</label>
							<input type="password" name="mpw" id="mpw-xs" placeholder="비밀번호를 입력하세요" class="form-control">
						</div>
						<div class="form-group">
							<button class="btn btn-primary form-control" type="submit">
								<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 확인
							</button>
						</div>
						<div class="form-group">
							<button class="btn btn-default form-control" type="button" onClick="location.href = 'signupForm.do'">
								<span class="glyphicon glyphicon-user" aria-hidden="true"></span> 회원가입
							</button>
						</div>
						<div class="form-group">
							<button class="btn btn-default form-control" type="button">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span> 아이디/비밀번호 찾기
							</button>
						</div>
					</form>
				</div>
			</div>
			<!-- 본문 내용 종료 -->
		</article>
	</section>
	
	<!-- Footer -->
	<%@ include file="../common/footer.jsp" %>
	
	</div>

</body>
</html>