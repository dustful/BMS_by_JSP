/*
 * 상품 상세
 * - 버튼별 행동
 */
$(function() {
	$("#buyNowBtn").click(function() {
		$("#bookDetailForm").attr("action", "orderBookForm.do");
		$("#bookDetailForm").submit();
	});

	$("#addCartBtn").click(function() {
		$("#bookDetailForm").attr("action", "cartAddPro.do");
		$("#bookDetailForm").submit();
	});
});

/*
 * 체크 박스 전체 선택
 */
$(function() {
	$("#check_all").change(function() {
		// this = 전체 선택용 체크 박스
		var is_check = $(this).is(":checked");

		// 개별 체크 박스
		$(".check_each").prop("checked", is_check);
	});
});

/*
 * 장바구니
 * - 버튼별 행동
 */
$(function() {
	$("#orderAllOfThem").click(function() {
		$(".check_each").prop("checked", true);
		$("#cartAction").val("orderSelected");
		$("#cartForm").submit();
	});
	
	$("#orderSelected").click(function() {
		$("#cartAction").val("orderSelected");
		$("#cartForm").submit();
	});

	$("#removeToCart").click(function() {
		$("#cartAction").val("removeToCart");
		$("#cartForm").submit();
	});
});

/*
 * 주문 양식에서
 * 주문자와 수신자가 동일할 경우
 * 주문자 정보를 수신자 정보에 반영
 */
$(function() {
	$("#sameOrderInfo").change(function() {
		if($("#sameOrderInfo").is(":checked")) {
			$("#rcname").val($("#odname").val());
			$("#rccontact").val($("#odcontact").val());
			$("#rcname").attr("readonly", true);
			$("#rccontact").attr("readonly", true);
		} else {
			$("#rcname").val("");
			$("#rccontact").val("");
			$("#rcname").attr("readonly", false);
			$("#rccontact").attr("readonly", false);
		}
	});
});
