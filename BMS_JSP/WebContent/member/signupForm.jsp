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
				<li><a href="signinForm.do">로그인</a></li>
				<li class="active">회원가입</li>
			</ol>
			<!-- 경로 출력 종료 -->
			
			<div class="page-header">
				<h1>회원 가입<small> Member Registration</small></h1>
			</div>
			
			<div class="panel panel-info center-block hidden-xs" style="max-width: 50%;">
				<div class="panel-heading"><h4 class="panel-title">회원 가입</h4></div>
				<div class="panel-body">
					<form action="signupPro.do" method="post" enctype="multipart/form-data">
						<div class="form-group">
							<label for="mid">아이디</label>
							<div class="input-group">
								<input type="text" name="mid" id="mid" placeholder="아이디를 입력하세요" class="form-control" autofocus required pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{4,20}$">
								<span class="input-group-btn"><button class="btn btn-info" type="button">아이디 중복확인</button></span>
							</div>
							<small class="help-block">알파벳으로 시작해서 대소문자와 숫자 혼용 가능하며 최소 4자부터 20자까지</small>
						</div>
						<div class="form-group">
							<div class="row">
								<div class="col-md-6">
									<label for="mpw">비밀번호</label>
									<input type="password" name="mpw" id="mpw" placeholder="비밀번호를 입력하세요" class="form-control" required pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])|(?=.*[a-z]).*$">
								</div>
								<div class="col-md-6">
									<label for="repw">비밀번호 확인</label>
									<input type="password" name="repw" id="repw" placeholder="비밀번호를 다시 입력하세요" class="form-control" required pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])|(?=.*[a-z]).*$">
								</div>
							</div>
							<small class="help-block">알파벳 대소문자, 숫자, 특수문자 혼용 가능하며 최소 8자부터 20자까지</small>
						</div>
						<div class="row">
							<div class="form-group col-md-6">
								<label for="mname">이름</label>
								<input type="text" name="mname" id="mname" placeholder="이름을 입력하세요" class="form-control" required>
							</div>
							<div class="form-group col-md-6">
								<label for="mbirth">생일</label>
								<input type="date" name="mbirth" id="mbirth" placeholder="생일을 입력하세요" class="form-control" required pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))">
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-6">
								<label for="memail">이메일</label>
								<input type="email" name="memail" id="memail" placeholder="이메일을 입력하세요" class="form-control" required>
							</div>
							<div class="form-group col-md-6">
								<label for="mcp">휴대전화번호</label>
								<input type="tel" name="mcp" id="mcp" placeholder="휴대전화번호를 입력하세요" class="form-control" pattern="\d{3}[\-]\d{3,4}[\-]\d{4}">
							</div>
						</div>
						<div class="form-group">
							<label for="maddr">주소</label>
							<div class="input-group">
								<input type="text" name="maddr1" id="maddr1" placeholder="주소를 입력하세요" class="form-control">
								<span class="input-group-btn"><button class="btn btn-info" type="button" disabled>주소 검색</button></span>
							</div>
						</div>
						<div class="form-group">
							<input type="text" name="maddr2" id="maddr2" placeholder="상세 주소를 입력하세요" class="form-control">
						</div>
						<div class="form-group">
							<label for="mprofimg">프로필 이미지</label>
							<input type="file" name="mprofimg" id="mprofimg">
						</div>
						<div class="btn-group btn-group-justified" role="group">
							<div class="btn-group">
								<button class="btn btn-primary" type="submit">
									<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 확인
								</button>
							</div>
							<div class="btn-group">
								<button class="btn btn-danger" type="reset">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 전부 지우기
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			
			<div class="panel panel-info center-block visible-xs">
				<div class="panel-heading"><h4 class="panel-title">회원 가입</h4></div>
				<div class="panel-body">
					<form action="signupPro.do" method="post" enctype="multipart/form-data">
						<div class="form-group">
							<label for="mid">아이디</label>
							<div class="input-group">
								<input type="text" name="mid" id="mid" placeholder="아이디를 입력하세요" class="form-control" autofocus required pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{4,20}$">
								<span class="input-group-btn"><button class="btn btn-info" type="button">아이디 중복확인</button></span>
							</div>
							<small class="help-block">알파벳으로 시작해서 대소문자와 숫자 혼용 가능하며 최소 4자부터 20자까지</small>
						</div>
						<div class="form-group">
							<label for="mpw">비밀번호</label>
							<input type="password" name="mpw" id="mpw" placeholder="비밀번호를 입력하세요" class="form-control" required pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])|(?=.*[a-z]).*$">
							<small class="help-block">알파벳 대소문자, 숫자, 특수문자 혼용 가능하며 최소 8자부터 20자까지</small>
						</div>
						<div class="form-group">
							<label for="repw">비밀번호 확인</label>
							<input type="password" name="repw" id="repw" placeholder="비밀번호를 다시 입력하세요" class="form-control" required pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])|(?=.*[a-z]).*$">
						</div>
						<div class="form-group">
							<label for="mname">이름</label>
							<input type="text" name="mname" id="mname" placeholder="이름을 입력하세요" class="form-control" required>
						</div>
						<div class="form-group">
							<label for="mbirth">생일</label>
							<input type="date" name="mbirth" id="mbirth" placeholder="생일을 입력하세요" class="form-control" required pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))">
						</div>
						<div class="form-group">
							<label for="memail">이메일</label>
							<input type="email" name="memail" id="memail" placeholder="이메일을 입력하세요" class="form-control" required>
						</div>
						<div class="form-group">
							<label for="mcp">휴대전화번호</label>
							<input type="tel" name="mcp" id="mcp" placeholder="휴대전화번호를 입력하세요" class="form-control" pattern="\d{3}[\-]\d{3,4}[\-]\d{4}">
						</div>
						<div class="form-group">
							<label for="maddr">주소</label>
							<div class="input-group">
								<input type="text" name="maddr1" id="maddr1" placeholder="주소를 입력하세요" class="form-control">
								<span class="input-group-btn"><button class="btn btn-info" type="button" disabled>주소 검색</button></span>
							</div>
						</div>
						<div class="form-group">
							<input type="text" name="maddr2" id="maddr2" placeholder="상세 주소를 입력하세요" class="form-control">
						</div>
						<div class="form-group">
							<label for="mprofimg">프로필 이미지</label>
							<input type="file" name="mprofimg" id="mprofimg">
						</div>
						<div class="btn-group btn-group-justified" role="group">
							<div class="btn-group">
								<button class="btn btn-primary" type="submit">
									<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 확인
								</button>
							</div>
							<div class="btn-group">
								<button class="btn btn-danger" type="reset">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 전부 지우기
								</button>
							</div>
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