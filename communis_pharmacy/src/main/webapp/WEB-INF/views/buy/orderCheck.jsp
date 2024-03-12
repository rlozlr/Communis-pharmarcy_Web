<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<div class="container">
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
        </div>
        <div class="col-md-6">
            <h3>구매자 정보 입력</h3>
            <form action="/orderController/confirmOrder" method="post">
                <div class="mb-3">
                    <label for="buyerName" class="form-label">구매자 이름</label>
                    <input type="text" class="form-control" id="buyerName" name="buyerName" required>
                </div>
                <div class="mb-3">
                    <label for="buyerEmail" class="form-label">구매자 이메일</label>
                    <input type="email" class="form-control" id="buyerEmail" name="buyerEmail" required>
                </div>
                <div class="mb-3">
                    <label for="buyerPhoneNumber" class="form-label">구매자 전화번호</label>
                    <input type="tel" class="form-control" id="buyerPhoneNumber" name="buyerPhoneNumber" required>
                </div><br><hr><br>
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
                    <input type="tel" class="form-control" id="recipientPhoneNumber" name="recipientPhoneNumber" required>
                </div>
                <div class="input-group mb-3">
                    <label for="recipientAddress" class="form-label">배송 주소</label>
                </div>
                <div class="mb-3">
                    <input type="text" id="sample6_postcode" placeholder="우편번호">
                    <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
                    <input type="text" id="sample6_address" placeholder="주소">
                    <input type="text" id="sample6_detailAddress" placeholder="상세주소">
                </div>
			    <h3>배송 정보</h3>
			    <table class="table table-striped">
			        <tbody>
			            <tr>
			                <th>결제 방법</th>
			                <td>${order.paymentMethod}</td>
			            </tr>
			        </tbody>
			    </table>
				<button type="submit" class="btn btn-primary">주문하기</button>
			</form>
		</div>
	</div>
</div>

<!-- 모달 창 -->
<!-- <div class="modal fade" id="addressModal" tabindex="-1" aria-labelledby="addressModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addressModalLabel">주소 검색</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="input-group mb-3">
                    <input type="text" class="form-control addressSearchBtn" id="roadName" placeholder="도로명 주소를 입력하세요" aria-label="Recipient's username" aria-describedby="addressSearchBtn">
                    <button class="btn btn-outline-secondary" type="button" id="addressSearchBtn">검색</button>
                </div>
            </div>
            <table id="SearchAddressList">
            	
            </table>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div> -->

<script src="/resources/js/buy/orderCheck.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<jsp:include page="../layout/footer.jsp"></jsp:include>