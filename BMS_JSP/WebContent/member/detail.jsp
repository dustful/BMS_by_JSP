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
						<li><a href="memberList.do">회원관리</a></li>
						<li class="active">회원상세</li>
					</ol>
					<!-- 경로 출력 종료 -->
				
					<div class="page-header">
						<h1>회원 상세 <small>Member Detail</small></h1>
					</div>
					
					<!-- 회원 상세 출력 시작 -->
					<div class="row">
						<div class="col-xs-12 col-md-6">
							<div class="thumbnail">
								<img class="img-responsive" src="/BMS_JSP/uploadedFiles/member/${member.mprofimg}" alt="${member.mprofimg}" style="max-height: 360px;">
							</div>
						</div>
						<div class="col-xs-12 col-md-6">
							<div class="panel panel-default">
								<div class="panel-body">
									<h4>${member.mid}</h4>
									<form id="bookDetailForm" method="post">
										<input type="hidden" name="orgNum" value="${member.mno}">
										<div class="table-responsive">
											<table class="table">
												<tr>
													<td>상태</td>
													<td>${member.mstat == 1? "정상":"탈퇴"}</td>
												</tr>
												<tr>
													<td>이름</td>
													<td>${member.mname}</td>
												</tr>
												<tr>
													<td>회원등급</td>
													<td>${member.mgrd == 0? "판매자":"구매자"}</td>
												</tr>
												<tr>
													<td>가입일</td>
													<td>${member.mregdate}</td>
												</tr>
												<tr>
													<td>최근접속일</td>
													<td>${member.mrecdate}</td>
												</tr>
												<tr>
													<td>생일</td>
													<td>${member.mbirth}</td>
												</tr>
												<tr>
													<td>이메일</td>
													<td>${member.memail}</td>
												</tr>
												<tr>
													<td>휴대전화번호</td>
													<td>${member.mcp}</td>
												</tr>
												<tr>
													<td>주소</td>
													<td>${member.maddr}</td>
												</tr>
												<tr>
													<td>적립금</td>
													<td><span class="price"><fmt:formatNumber pattern="#,###" value="${member.mmileage}"/></span>원</td>
												</tr>
												<tr>
													<td>예치금</td>
													<td><span class="price"><fmt:formatNumber pattern="#,###" value="${member.mbalance}"/></span>원</td>
												</tr>
											</table>
										</div>
										<div class="btn-group btn-group-justified">
											<div class="btn-group">
												<button class="btn btn-primary" type="button" id="bookModifyBtn" onClick="location.href = 'memberModifyForm.do?orgNum=${member.mno}'">
													<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 회원 정보 수정
												</button>
											</div>
											<div class="btn-group">
												<button class="btn btn-danger" type="button" id="bookDeleteBtn">
													<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 회원 삭제
												</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
					<!-- 회원 상세 출력 종료 -->
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