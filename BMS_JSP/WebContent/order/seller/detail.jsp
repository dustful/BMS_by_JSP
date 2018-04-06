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
	
	<section id="bookDetail">
		<article>
			<div class="row">
				<!-- Aside -->
				<%@ include file="../../common/aside.jsp" %>
				
				<!-- 본문 내용 시작 -->
				<div class="col-md-9">
					<!-- 경로 출력 시작 -->
					<ol class="breadcrumb">
						<li><a href="myPage.do"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> 마이페이지</a></li>
						<li><a href="orderList.do">주문관리</a></li>
						<li class="active">주문상세</li>
					</ol>
					<!-- 경로 출력 종료 -->
		
					<div class="page-header">
						<h1>주문 상세 <small>Order Detail</small></h1>
					</div>
					
					<div class="form-group pull-right" role="group">
						<button class="btn btn-success" type="button" id="confirmOrder">
							<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 결제 확인
						</button>
						<button class="btn btn-success" type="button" id="sendOrderedBook">
							<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 발송 완료
						</button>
						<button class="btn btn-success" type="button" id="allowRefundation">
							<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 환불 승인
						</button>
					</div>
					
					<div class="clearfix"></div>
					
					<fieldset>
						<legend>주문 상품 정보</legend>
						<div class="table-responsive">
							<!-- 주문 상세 출력 시작 -->
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
									<c:forEach var="order" items="${orders}">
										<tr>
											<td class="text-center">
												<c:set var="totPrice" value="${totPrice = totPrice + order.bookDTO.bkprice * order.odqty}" />
												<div class="thumbnail">
													<a href="bookDetail.do?orgNum=${order.bookDTO.bkno}">
														<img class="img-responsive" src="/BMS_JSP/uploadedFiles/books/${order.bookDTO.bkimg}" alt="${order.bookDTO.bkimg}" style="max-height: 120px;">
													</a>
												</div>
											</td>
											<td class="text-center">${order.bookDTO.bkname}</td>
											<td class="text-center hidden-xs">${order.bookDTO.bkauthor}</td>
											<td class="text-center hidden-xs">${order.bookDTO.bkpublisher}</td>
											<td class="text-center"><fmt:formatNumber pattern="#,###" value="${order.bookDTO.bkprice}"/></td>
											<td class="text-center"><fmt:formatNumber pattern="#,###" value="${order.odqty}"/></td>
											<td class="text-center"><fmt:formatNumber pattern="#,###" value="${order.bookDTO.bkprice * order.odqty}"/></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<!-- 주문 상세 출력 종료 -->
						</div>
					</fieldset>
							
					<fieldset>
						<legend>총 주문 금액</legend>
						<p class="help-block">5만원 이상 주문시 배송비는 무료입니다.</p>
						<div class="panel panel-default">
							<div class="panel-body">
								<c:set var="deliveryCharge" value="${totPrice > includedDeliveryCharge? 0:deliveryCharge}"/>
								<span class="price"><fmt:formatNumber pattern="#,###" value="${totPrice}"/></span> + <span class="price"><fmt:formatNumber pattern="#,###" value="${deliveryCharge}"/></span>(배송비) = <span class="price"><fmt:formatNumber pattern="#,###" value="${totPrice + deliveryCharge}"/></span>원
							</div>
						</div>
					</fieldset>
					<fieldset>
						<legend>주문하시는 분</legend>
						<table class="table">
							<colgroup>
								<col width="20%">
								<col width="80%">
							</colgroup>
							<tr>
								<th class="active text-right">이름</th>
								<td>${orders[0].odname}</td>
							</tr>
							<tr>
								<th class="active text-right">연락처</th>
								<td>${orders[0].odcontact}</td>
							</tr>
						</table>
					</fieldset>
					<fieldset>
						<legend>받으시는 분</legend>
						<table class="table">
							<colgroup>
								<col width="20%">
								<col width="80%">
							</colgroup>
							<tr>
								<th class="active text-right">이름</th>
								<td>${orders[0].rcname}</td>
							</tr>
							<tr>
								<th class="active text-right">연락처</th>
								<td>${orders[0].rccontact}</td>
							</tr>
							<tr>
								<th class="active text-right">주소</th>
								<td>${orders[0].rcaddr}
						</table>
					</fieldset>
					<fieldset>
						<legend>결제 방식</legend>
						<c:if test="${orders[0].pymd == 10}">
							<div class="panel panel-info">
								<div class="panel-heading"><h4 class="panel-title">신용카드</h4></div>
								<div class="panel-body">준비중</div>
							</div>
						</c:if>
						<c:if test="${orders[0].pymd == 20}">
							<div class="panel panel-info">
								<div class="panel-heading"><h4 class="panel-title">실시간계좌이체</h4></div>
								<div class="panel-body">준비중</div>
							</div>
						</c:if>
						<c:if test="${orders[0].pymd == 30}">
							<div class="panel panel-info">
								<div class="panel-heading"><h4 class="panel-title">무통장입금</h4></div>
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
						</c:if>
					</fieldset>
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