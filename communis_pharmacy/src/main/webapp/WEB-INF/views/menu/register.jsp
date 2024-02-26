<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<div class="container-md">
<h1>약품 정보 검색</h1>
<div class="mb-3">
    <label id="pillSearch" for="pillName">약품명:</label>
    <input type="text" id="pillName" name="pillName">
    <button type="button" id="pillSearchBtn" class="btn btn-outline-dark btn-sm pillSearchBtn"
    data-bs-toggle="modal" data-bs-target="#pillSearchModal">검색</button>
</div>

<div class="container-md">
<br>
<h2>약품 정보</h2>
	<div class="mb-3">
		<label for="itemName" class="form-label">제품명</label>
		<input type="text" name="itemName" class="form-control" 
			id="itemName">
	</div>
	<div class="mb-3">
		<label for="entpName" class="form-label">제조사</label>
		<input type="text" name="entpName" class="form-control" 
			id="entpName">
	</div>
	<div class="mb-3">
		<label for="efcyQesitm" class="form-label">효과·효능</label>
		<input type="text" name="efcyQesitm" class="form-control" 
			id="efcyQesitm">
	</div>
	<div class="mb-3">
		<label for="pillPrice" class="form-label">가격</label>
		<input type="number" name="pillPrice" class="form-control" 
			id="pillPrice">
	</div>
	<div class="mb-3">
		<label for="pillStock" class="form-label">재고</label>
		<input type="number" name="pillStock" class="form-control" 
			id="pillStock">
	</div>
	<div class="mb-3">
		<label for="thumbnail" class="form-label">제품사진</label>
		<span><button type="button" id="trigger">사진등록</button></span>
		<input type="file" name="pillImgName" class="form-control" id="files" multiple="multiple" style="display: none"><br>
	</div>
		<!-- 파일 목록 표시라인 -->
 	<div class="mb-3" id="fileZone"></div>
		
	<button type="submit" id="regBtn">등록</button>
</div>
</form>
</div>

	
<%-- <c:choose>
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
</c:choose> --%>

<!-- 약품 검색 결과를 표시할 모달 -->
<div class="modal fade" id="pillSearchModal" tabindex="-1" aria-labelledby="pillSearchModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
                <h5 class="modal-title" id="pillSearchModalLabel">약품 검색 결과</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- 약품 검색 결과를 테이블 형식으로 표시 -->
                <table class="table">
                    <thead>
                        <tr>
                            <th>약품명</th>
                            <th>제조사</th>
                            <th>효능</th>
                            <th>선택</th>
                        </tr>
                    </thead>
                    <tbody id="pillSearchResults">
                        <!-- 약품 검색 결과가 있는 경우 -->
                        <c:choose>
                            <c:when test="${not empty pillInfoList}">
                                <c:forEach var="pillInfo" items="${pillInfoList}">
                                    <tr>
                                        <td>${pillInfo.itemName}</td>
                                        <td>${pillInfo.entpName}</td>
                                        <td>${pillInfo.efcyQesitm}</td>
                                        <!-- 약품 선택 버튼 -->
                                        <td>
                                            <button type="button" class="btn btn-primary selectPillBtn"
                                                data-item-name="${pillInfo.itemName}"
                                                data-entp-name="${pillInfo.entpName}"
                                                data-efcy-qesitm="${pillInfo.efcyQesitm}">
                                                선택
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="4">검색된 약품 정보가 없습니다.</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


<script src="/resources/js/menu/register.js"></script>
<script src="/resources/js/menu/menuImage.js"></script>
<jsp:include page="../layout/footer.jsp"></jsp:include>