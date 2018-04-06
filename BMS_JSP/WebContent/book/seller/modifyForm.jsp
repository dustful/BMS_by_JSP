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
	
	<section id="bookModifyForm">
		<article>
			<div class="row">
				<!-- Aside -->
				<%@ include file="../../common/aside.jsp" %>
				
				<!-- 본문 내용 시작 -->
				<div class="col-md-9">
					<!-- 경로 출력 시작 -->
					<ol class="breadcrumb">
						<li><a href="myPage.do"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> 마이페이지</a></li>
						<li><a href="sellerBookList.do">재고관리</a></li>
						<li><a href="sellerBookDetail.do?orgNum=${book.bkno}">재고상세</a></li>
						<li class="active">재고정보수정</li>
					</ol>
					<!-- 경로 출력 종료 -->
				
					<div class="page-header">
						<h1>재고 정보 수정<small> Product Modification</small></h1>
					</div>
					
					<!-- 상품 수정 양식 출력 시작 -->
					<div class="panel panel-default">
						<div class="panel-body">
							<form action="bookRegisterPro.do" method="post" enctype="multipart/form-data">
								<div class="form-group">
									<label for="bkname">도서명</label>
									<input class="form-control" type="text"	 id="bkname" name="bkname" value="${book.bkname}" placeholder="도서명을 입력하세요">
								</div>
								<div class="form-group">
									<label for="bkimg">도서표지이미지</label>
									<input class="form-control" type="file"	id="bkimg" name="bkimg">
								</div>
								<div class="form-group">
									<label for="bkauthor">저자</label>
									<input class="form-control" type="text"	 id="bkauthor" name="bkauthor" value="${book.bkauthor}" placeholder="저자를 입력하세요">
								</div>
								<div class="form-group">
									<label for="bkpublisher">출판사</label>
									<input class="form-control" type="text"	 id="bkpublisher" name="bkpublisher" value="${book.bkpublisher}" placeholder="출판사를 입력하세요">
								</div>
								<div class="form-group">
									<label for="bkprice">가격</label>
									<input class="form-control" type="number" min="0" step="10" id="bkprice" name="bkprice" value="${book.bkprice}" placeholder="가격을 입력하세요">
								</div>
								<div class="form-group">
									<label for="bkqty">재고수량</label>
									<input class="form-control" type="number" min="0" step="1" id="bkqty" name="bkqty" value="${book.bkqty}" placeholder="재고수량을 입력하세요">
								</div>
								<div class="form-group">
									<label for="bkcontent">도서소개</label>
									<textarea class="form-control" id="bkcontent" name="bkcontent" rows="5" placeholder="도서소개를 입력하세요">${book.bkcontent}</textarea>
								</div>
								<div class="btn-group btn-group-justified" role="group">
									<div class="btn-group">
										<button class="btn btn-success" type="submit">
											<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 확인
										</button>
									</div>
									<div class="btn-group">
										<button class="btn btn-danger" type="reset">
											<span class="glyphicon glyphicon-erase" aria-hidden="true"></span> 전부 지우기
										</button>
									</div>
								</div>
							</form>
						</div>
					</div>
					<!-- 상품 수정 양식 출력 종료 -->
					
					<!-- 결과 반환 변수에 의한 알림 출력 시작 -->
					<c:if test="${result == 1}">
						<script type="text/javascript">
							$(function() {
								$("#resultModal .modal-body").text("상품이 정상적으로 수정되었습니다.");
								$("#resultModal").modal("show");
							});
						</script>
					</c:if>
					
					<div class="modal fade" id="resultModal" tabindex="-1" role="dialog" aria-labelledby="resultModalTitle" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h4 class="modal-title" id="resultModalTitle">알림</h4>
								</div>
								<div class="modal-body"></div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">계속 입력</button>
									<button type="button" class="btn btn-primary" onClick="location.href = 'sellerBookList.do'">상품목록으로 이동</button>
								</div>
							</div>
						</div>
					</div>
					<!-- 결과 반환 변수에 의한 알림 출력 종료 -->
				</div>
				<!-- 본문 내용 종료 -->
			</div>
		</article>
	</section>
	
	<!-- Footer -->
	<%@ include file="../../common/footer.jsp" %>
	
	</div>

</body>
</html>