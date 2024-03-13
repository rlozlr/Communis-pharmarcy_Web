<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<div class="container">
<sec:authentication property="principal.mvo" var="mvo"/>
    <h2>주문 확인</h2><hr><br>
    <div class="row">
        <div class="col-md-6">
            <h3>주문 정보</h3>
            <table class="table table-striped">
                <tbody>
                    <tr>
                        <th>상품명</th>
                        <td>${pvo.itemName}</td>
                    </tr>
                    <tr>
                        <th>제조사</th>
                        <td>${pvo.entpName}</td>
                    </tr>
                    <tr>
                        <th>가격</th>
                        <td>${pvo.pillPrice}원</td>
                    </tr>
                </tbody>
            </table>
            <hr>
            <div class="mb-6">
            	<label for="totalPrice" class="form-label">총 금액</label>
			    <input type="text" class="form-control" id="totalPrice" name="totalPrice" readonly 
			    	value="${pvo.pillPrice}원" style="width: 70%;">
            </div>
        </div>
        <div class="col-md-6">
            <h3>구매자 정보 입력</h3>
                <div class="mb-3">
                    <label for="buyerName" class="form-label">구매자 이름</label>
                    <input type="text" class="form-control" id="buyerName" name="buyerName" readonly value="${mvo.nickName }">
                </div>
                <div class="mb-3">
                    <label for="buyerEmail" class="form-label">구매자 이메일</label>
                    <input type="email" class="form-control" id="buyerEmail" name="buyerEmail" required value="${mvo.email }">
                </div>
                <div class="mb-3">
                    <label for="buyerPhoneNumber" class="form-label">구매자 전화번호</label>
                    <input type="tel" class="form-control" id="buyerPhoneNumber" name="buyerPhoneNumber" required placeholder="'-'는 빼고 입력해주세요.">
                </div><br><hr><br>
                
	            <form action="/orderController/confirmOrder" method="post">
	            <input type="hidden" value="${mvo.email}">
	            <input type="hidden" value="${pvo.pillId}">
	                <h3>받는이 정보 입력</h3>
	                <div class="form-check mb-3">
	                    <input type="checkbox" class="form-check-input" id="sameAsBuyer">
	                    <label class="form-check-label" for="sameAsBuyer">구매자와 동일</label>
	                </div>
	                <div class="mb-3">
	                    <label for="recipientName" class="form-label">받는이 이름</label>
	                    <input type="text" class="form-control" id="recipientName" name="recipientName" required>
	                </div>
	                <div class="mb-3">
	                    <label for="recipientPhoneNumber" class="form-label">받는이 전화번호</label>
	                    <input type="tel" class="form-control" id="recipientPhoneNumber" name="recipientPhoneNumber" required placeholder="'-'는 빼고 입력해주세요.">
	                </div>
	                <div class="input-group mb-3">
	                    <label for="recipientAddress" class="form-label">배송 주소</label>
	                </div>
	                <div class="mb-3">
	                    <input type="text" id="sample6_postcode" placeholder="우편번호">
	                    <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
	                    <input type="text" id="sample6_address" placeholder="주소">
	                    <input type="text" id="sample6_detailAddress" placeholder="상세주소">
	                </div><br><hr><br>
				    <h3>결제 방법 선택</h3>
				    <div class="payment-buttons">
					    <button type="button" class="btn btn-payment btn-outline-dark" id="btnBankTransfer" value="bank">무통장입금</button>
					    <button type="button" class="btn btn-payment btn-outline-dark" id="btnCardPayment" value="card">카드결제</button>
					    <button type="button" class="btn btn-payment btn-outline-dark" id="btnKakaoPay" value="kakao">KakaoPay</button>
					    <button type="button" class="btn btn-payment btn-outline-dark" id="btnNaverPay" value="naver">NaverPay</button>
					</div><br><hr><br>
				<button type="submit" class="btn btn-primary">주문하기</button>
			</form>
		</div>
	</div>
</div>

<script src="/resources/js/buy/orderCheck.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<jsp:include page="../layout/footer.jsp"></jsp:include>