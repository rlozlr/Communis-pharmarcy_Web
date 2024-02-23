<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<div class="container-md">
<c:set value="${mdto.pvo }" var="pvo"></c:set>
<br><hr><h2>${pvo.itemName }</h2><hr><br>
<form action="/menu/modify" method="post"  enctype="multipart/form-data">
<input type="hidden" name="pillId" id="pillId" value="${pvo.pillId }">	
	<div class="mb-3">
		<label for="itemName" class="form-label">제품명</label>
		<input type="text" name="itemName" class="form-control" 
			id="itemName" value="${pvo.itemName }">
	</div>
	<div class="mb-3">
		<label for="entpName" class="form-label">제조사</label>
		<input type="text" name="entpName" class="form-control" 
			id="entpName" value="${pvo.entpName }">
	</div>
	<div class="mb-3">
		<label for="efcyQesitm" class="form-label">효과·효능</label>
		<input type="text" name="efcyQesitm" class="form-control" 
			id="efcyQesitm" value="${pvo.efcyQesitm }">
	</div>
	<div class="mb-3">
		<label for="pillPrice" class="form-label">가격</label>
		<input type="text" name="pillPrice" class="form-control" 
			id="pillPrice" value="${pvo.pillPrice }">
	</div>
	<div class="mb-3">
		<label for="pillStock" class="form-label">재고</label>
		<input type="text" name="pillStock" class="form-control" 
			id="pillStock" value="${pvo.pillStock }">
	</div>
	<div class="mb-3">
		<label for="thumbnail" class="form-label">제품사진</label>
		<span><button type="button" id="trigger">사진변경</button></span>
		<img src="${pvo.thumbnail }" name="thumbnail" class="form-control" 
			id="thumbnail" style="max-width: 10%;">
		<input type="file" name="pillImgName" class="form-control" id="files" multiple="multiple" style="display: none"><br>
	</div>
	
	<!-- 파일 목록 표시라인 -->
 	<div class="mb-3" id="fileZone"></div>
		
	<button type="submit" id="regBtn">수정완료</button>
	<a href="/menu/detail?pillId=${pvo.pillId }"><button type="button">취소</button></a>
	</form>
</div>

<script src="/resources/js/menu/menuImage.js"></script>
<jsp:include page="../layout/footer.jsp"></jsp:include>