<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<h1>약품 정보 검색</h1>

<form action="/menu/register" method="post">
    <label for="pillName">약품명:</label>
    <input type="text" id="pillName" name="pillName">
    <button type="submit">검색</button>
</form>

<br>
<h2>약품 정보</h2>
<c:choose>
    <c:when test="${not empty pillInfoList}">
        <c:forEach var="pillInfo" items="${pillInfoList}">
            <ul>
                <li>약품명: ${pillInfo.itemName}</li>
                <li>제조사: ${pillInfo.entpName}</li>
                <li>효능: ${pillInfo.efcyQesitm}</li>
                <li>이미지: <br>
                    <img alt="이미지가 없습니다." src="${pillInfo.thumbnail}">
                </li>
            </ul>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <p>검색된 약품 정보가 없습니다.</p>
    </c:otherwise>
</c:choose>


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