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
	
	<section id="orderBookForm">
		<article>
			<!-- 본문 내용 시작 -->
			<!-- 경로 출력 시작 -->
			<ol class="breadcrumb">
				<li><a href="index.do"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> 메인페이지</a></li>
				<li><a href="bookList.do">상품목록</a></li>
				<%-- <li><a href="bookDetail.do?orgNum=${param.orgNum}">상품상세</a></li> --%>
				<li class="active">상품주문</li>
			</ol>
			<!-- 경로 출력 종료 -->
		
			<div class="page-header">
				<h1>상품 주문<small> Product Order</small></h1>
			</div>
			
			<!-- 상품 주문 양식 출력 시작 -->
			<div class="panel panel-default">
				<div class="panel-body">
					<form action="orderBookPro.do" method="post">
						<input type="hidden" name="nextv" value="${nextv}">
						<fieldset>
							<legend>주문 상품 정보</legend>
							<div class="table-responsive">
								<!-- <p class="help-block">주문 수량은 장바구니에서 조정이 가능합니다.</p> -->
								<table class="table table-hover">
									<colgroup>
										<col width="10%">
										<col width="15%">
										<col width="15%">
										<col width="15%">
										<col width="15%">
										<col width="15%">
										<col width="15%">
									</colgroup>
									<thead>
										<tr class="active">
											<th class="text-center"></th>
											<th class="text-center">도서명</th>
											<th class="text-center hidden-xs">저자</th>
											<th class="text-center hidden-xs">출판사</th>
											<th class="text-center">단가</th>
											<th class="text-center">주문수량</th>
											<th class="text-center">소계</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="book" items="${books}">
											<tr>
												<td class="text-center">
													<div class="thumbnail">
														<input type="hidden" name="bkno" value="${book.key}">
														<input type="hidden" name="odqty" value="${book.value.bkqty}">
														<img class="img-responsive" src="/BMS_JSP/uploadedFiles/books/${book.value.bkimg}" alt="${book.value.bkimg}" style="max-height: 120px;">
													</div>
												</td>
												<td class="text-center">${book.value.bkname}</td>
												<td class="text-center hidden-xs">${book.value.bkauthor}</td>
												<td class="text-center hidden-xs">${book.value.bkpublisher}</td>
												<td class="text-center"><fmt:formatNumber pattern="#,###" value="${book.value.bkprice}"/></td>
												<td class="text-center"><fmt:formatNumber pattern="#,###" value="${book.value.bkqty}"/></td>
												<td class="text-center"><fmt:formatNumber pattern="#,###" value="${book.value.bkprice * book.value.bkqty}"/></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</fieldset>
						<fieldset>
							<legend>총 결제 금액</legend>
							<p class="help-block">5만원 이상 결제시 배송비는 무료입니다.</p>
							<div class="panel panel-default">
								<div class="panel-body">
									<span class="price"><fmt:formatNumber pattern="#,###" value="${totPrice}"/></span> + <span class="price"><fmt:formatNumber pattern="#,###" value="${deliveryCharge}"/></span>(배송비) = <span class="price"><fmt:formatNumber pattern="#,###" value="${totPrice + deliveryCharge}"/></span>원
								</div>
							</div>
						</fieldset>
						<fieldset>
							<legend>주문하시는 분</legend>
							<div class="form-group">
								<label for="odname">이름</label>
								<input class="form-control" type="text"	 id="odname" name="odname" placeholder="이름을 입력하세요">
							</div>
							<div class="form-group">
								<label for="odcontact">연락처</label>
								<input class="form-control" type="text"	 id="odcontact" name="odcontact" placeholder="연락처를 입력하세요">
							</div>
						</fieldset>
						<fieldset>
							<legend>받으시는 분</legend>
							<div class="checkbox">
								<label>
									<input type="checkbox" id="sameOrderInfo"> 주문하시는 분과 받으시는 분이 동일할 경우 선택하세요
								</label>
							</div>
							<div class="form-group">
								<label for="rcname">이름</label>
								<input class="form-control" type="text"	 id="rcname" name="rcname" placeholder="이름을 입력하세요">
							</div>
							<div class="form-group">
								<label for="rccontact">연락처</label>
								<input class="form-control" type="text" id="rccontact" name="rccontact" placeholder="연락처를 입력하세요">
							</div>
							<div class="form-group">
								<label for="rcaddr">주소</label>
								<input class="form-control" type="text" id="rcaddr" name="rcaddr" placeholder="주소를 입력하세요">
							</div>
						</fieldset>
						<fieldset>
							<legend>결제 방식</legend>
							<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
								<div class="panel panel-info">
									<div class="panel-heading" role="tab" id="headingFirst">
										<h4 class="panel-title">
											<div class="radio" style="margin: 0;">
												<label>
													<input type="radio" disabled name="pymd" value="10" class="collapsed" data-toggle="collapse" data-parent="#accordion" data-target="#collapseFirst" aria-expanded="false" aria-controls="collapseFirst"> 신용카드
												</label>
											</div>
										</h4>
									</div>
									<div id="collapseFirst" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFirst">
										<div class="panel-body">준비중</div>
									</div>
								</div>
								<div class="panel panel-info">
									<div class="panel-heading" role="tab"	id="headingSecond">
										<h4 class="panel-title">
											<div class="radio" style="margin: 0;">
												<label>
													<input type="radio" disabled name="pymd" value="20" class="collapsed" data-toggle="collapse" data-parent="#accordion" data-target="#collapseSecond" aria-expanded="false" aria-controls="collapseSecond"> 실시간계좌이체
												</label>
											</div>
										</h4>
									</div>
									<div id="collapseSecond" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSecond">
										<div class="panel-body">준비중</div>
									</div>
								</div>
								<div class="panel panel-info">
									<div class="panel-heading" role="tab"	id="headingThird">
										<h4 class="panel-title">
											<div class="radio" style="margin: 0;">
												<label>
													<input type="radio" name="pymd" value="30" class="collapsed" data-toggle="collapse" data-parent="#accordion" data-target="#collapseThird" aria-expanded="false" aria-controls="collapseThird">무통장입금</a>
												</label>
											</div>
										</h4>
									</div>
									<div id="collapseThird" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThird">
										<div class="panel-body">
											<div class="form-group">
												<label for="pymd3info">은행 및 계좌번호</label>
												<p class="help-block">입금할 은행과 계좌번호 안내입니다.</p>
												<select class="form-control" id="pymddetail" name="pymddetail">
													<option>국민은행 123456-78-901234 (예금주 : 심야서점)</option>
													<option>농협 123456-78-901234 (예금주 : 심야서점)</option>
													<option>새마을금고 123456-78-901234 (예금주 : 심야서점)</option>
													<option>신한은행 123456-78-901234 (예금주 : 심야서점)</option>
													<option>우리은행 123456-78-901234 (예금주 : 심야서점)</option>
													<option>하나은행 123456-78-901234 (예금주 : 심야서점)</option>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
						</fieldset>
						<div class="btn-group btn-group-justified" role="group">
							<div class="btn-group">
								<button class="btn btn-success" type="submit">
									<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 주문하기
								</button>
							</div>
							<div class="btn-group">
								<button class="btn btn-danger" type="button" onClick="history.back();">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 취소
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- 상품 주문 양식 출력 종료 -->
			<!-- 본문 내용 종료 -->
		</article>
	</section>
	
	<!-- Footer -->
	<%@ include file="../../common/footer.jsp" %>
	
	</div>

</body>
</html>