<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<div class="container">
    <h2>장바구니</h2>
    
    <c:if test="${empty cartItems}">
        <p>장바구니가 비어 있습니다.</p>
    </c:if>
    
    <c:if test="${not empty cartItems}">
        <table class="table">
            <thead>
                <tr>
                    <th>상품명</th>
                    <th>가격</th>
                    <th>수량</th>
                    <th>합계</th>
                    <th>삭제</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${cartItems}" var="item">
                    <tr>
                        <td>${item.product.name}</td>
                        <td>${item.product.price}</td>
                        <td>${item.quantity}</td>
                        <td>${item.product.price * item.quantity}</td>
                        <td>
                            <form action="/cart/remove" method="post">
                                <input type="hidden" name="productId" value="${item.product.id}">
                                <button type="submit" class="btn btn-danger">삭제</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <div>
            <h4>총 금액: ${totalPrice}원</h4>
            <a href="/checkout" class="btn btn-primary">주문하기</a>
        </div>
    </c:if>
</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>