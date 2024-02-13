<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<h1>약품 정보 검색</h1>

<form action="/menu/search" method="get">
    <label for="pillName">약품명:</label>
    <input type="text" id="pillName" name="pillName">
    <button type="submit">검색</button>
<c:choose>
    <c:when test="${not empty pillInfo}">
        <h2>약품 정보</h2>
        <ul>
            <li>약품명: ${pillInfo.itemName}</li>
            <li>제조사: ${pillInfo.entpName}</li>
            <li>효능: ${pillInfo.efcyQesitm}</li>
            <!-- 여기에 이미지를 표시하는 부분을 추가할 수 있습니다 -->
        </ul>
    </c:when>
    <c:otherwise>
        <p>검색된 약품 정보가 없습니다.</p>
    </c:otherwise>
</c:choose>
</form>


<%-- <form action="/menu/register" method="post">
    <label for="manufacturer">제조사 선택:</label>
    <select id="manufacturer" name="manufacturer">
        <c:forEach items="${manufacturerList}" var="manufacturer">
            <option value="${manufacturer}">${manufacturer}</option>
        </c:forEach>
    </select>
    <button type="submit">등록</button>
</form> --%>

<jsp:include page="../layout/footer.jsp"></jsp:include>