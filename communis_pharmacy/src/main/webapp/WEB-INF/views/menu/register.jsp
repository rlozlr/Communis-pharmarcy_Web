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
</div>

<div class="container-md" id="pillRegForm">
<!-- 약품정보 등록 form  -->
</div>

<!-- 약품 검색 결과를 표시할 모달 -->
<div class="modal fade" id="pillSearchModal" tabindex="-1" aria-labelledby="pillSearchModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
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
                            <th></th>
                        </tr>
                    </thead>
                    <tbody id="pillSearchResults">
                        <!-- 약품 검색 결과가 동적으로 추가될 자리 -->
                        <td colspan="4"><h1>검색중</h1></td>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script src="/resources/js/menu/register.js"></script>
<jsp:include page="../layout/footer.jsp"></jsp:include>