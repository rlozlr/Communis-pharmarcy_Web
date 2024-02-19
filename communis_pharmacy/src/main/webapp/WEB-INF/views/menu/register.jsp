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
			<form action="/menu/insert" method="post">
				<ul>
					<li>약품명: ${pillInfo.itemName}</li>
					<li>제조사: ${pillInfo.entpName}</li>
					<li>효능: ${pillInfo.efcyQesitm}</li>
					<li>이미지: <br> <img alt="이미지가 없습니다."
						src="${pillInfo.thumbnail}">
					</li>
				</ul>
				<!-- hidden input 필드 대신 input 요소의 name 속성을 PillVO 객체의 필드와 일치시킴 -->
				<input type="hidden" name="itemName" value="${pillInfo.itemName}">
				<input type="hidden" name="entpName" value="${pillInfo.entpName}">
				<input type="hidden" name="efcyQesitm" value="${pillInfo.efcyQesitm}"> 
				<input type="hidden" name="thumbnail" value="${pillInfo.thumbnail}"> 
				<input type="checkbox" name="selectedItems" value="on">
				<!-- <input type="hidden" name="지점" value="인천약국"> -->
				<button type="button" id="regBtn">등록</button>
				<br>
			</form>
		</c:forEach>
		<button type="button" id="selectedBtn">선택 등록</button>
	</c:when>
	<c:otherwise>
		<p>검색된 약품 정보가 없습니다.</p>
	</c:otherwise>
</c:choose>

<script src="/resources/js/menu/register.js"></script>
<jsp:include page="../layout/footer.jsp"></jsp:include>