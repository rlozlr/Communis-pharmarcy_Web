<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<div class="container">
    <h2>주문 확인</h2>
    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">상품명</th>
                <th scope="col">제조사</th>
                <th scope="col">가격</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${orderRe}" var="orderRe">
                <tr>
                    <td>${orderRe.itemName}</td>
                    <td>${orderRe.entpName}</td>
                    <td>${orderRe.pillPrice}원</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <form action="/pillRank/orderConfirmation" method="post">
        <!-- 여기서는 주문자 정보를 입력할 수 있는 폼을 추가할 수 있습니다 -->
        <button type="submit" class="btn btn-primary">주문 완료</button>
    </form>
</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>