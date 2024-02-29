<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<div class="container-md">
	<!-- search line -->
	<br>
	<div class="col-sm-12 col-md-6">
		<form action="/menu/list" method="get">
			<div class="input-group mb-3">
				<c:set value="${ph.pgvo.type }" var="typed"></c:set>
				<select class="form-select" name="type" id="inputGroupSelect01">
					<option ${typed eq null ? 'selected' : '' }>선택</option>
					<option value ="p" ${typed eq 'p' ? 'selected' : '' }>제품명</option>
					<option value ="e" ${typed eq 'e' ? 'selected' : '' }>제조사</option>
					<option value ="pe" ${typed eq 'pe' ? 'selected' : '' }>전체</option>
				</select>
				<input type="hidden" name="pageNo" value="1">
				<input type="hidden" name="qty" value="${ph.pgvo.qty }">
				<input type="search" name="keyword" value="${ph.pgvo.keyword }"
					class="form-control me-2"  placeholder="검색">
				<button type="submit" class="btn btn-outline-success">검색
					<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
						${ph.totalCount }
						<span class="visually-hidden">unread messages</span>
					</span>
				</button> 
			</div>
		</form>
	</div>

	<table class="table">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">제품명</th>
				<th scope="col">제조사</th>
				<th scope="col">가격</th>
				<th scope="col">찜</th>
				<th scope="col">판매수</th>
				<th scope="col">재고</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="pvo">
				<tr data-pillid="${pvo.pillId}" data-itemname="${pvo.itemName}" data-entpname="${pvo.entpName}" 
					data-pillprice="${pvo.pillPrice}" data-pillstock="${pvo.pillStock}" data-thumbnail="${pvo.thumbnail}">
					<th scope="row">${pvo.pillId}</th>
					
					<td>
						<c:choose>
							<c:when test="${not empty mdto.pillImgList}">
                    			<c:forEach items="${mdto.pillImgList}" var="pivo">
                    				<c:if test="${pvo.pillId == pivo.pillId}">
								 		<img src="${pivo.pillImgName}" alt="">
										<a href="/menu/detail?pillId=${pvo.pillId}">${pvo.itemName}</a>
									</c:if>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<a href="/menu/detail?pillId=${pvo.pillId}">${pvo.itemName}</a>
							</c:otherwise>
						</c:choose>
					</td>
					<td>${pvo.entpName}</td>
					<td>${pvo.pillPrice}</td>
					<td>${pvo.pillDibs}</td>
					<td>${pvo.pillSell}</td>
					<td>${pvo.pillStock}</td>
					<td>
						<button type="button" id="showUpdateModalBtn" class="btn btn-outline-dark btn-sm showUpdateModalBtn"
							data-bs-toggle="modal" data-bs-target="#updateModal">간편관리</button>
						<a href="/menu/remove?pillId=${pvo.pillId}">
							<button type="button" id="invenDelBtn" class="btn btn-outline-danger btn-sm">제품삭제</button>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 모달 -->
	<div class="modal fade" id="updateModal" tabindex="-1" aria-labelledby="updateModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="updateModalLabel">제품 관리</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	      	
	      	<input type="hidden" id="pillId" name="pillId">
	        <div class="mb-3">
	          <label for="itemName" class="form-label">제품명</label>
	          <input type="text" id="itemName" class="form-control">
	        </div>
	        <div class="mb-3">
	          <label for="entpName" class="form-label">제조사</label>
	          <input type="text" id="entpName" class="form-control">
	        </div>
	        <div class="mb-3">
	          <label for="pillPrice" class="form-label">가격</label>
	          <input type="number" id="pillPrice" class="form-control" placeholder="숫자만 입력">
	        </div>
	        <div class="mb-3">
	          <label for="pillStock" class="form-label">재고수량</label>
	          <input type="number" id="pillStock" class="form-control" placeholder="숫자만 입력">
	        </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-outline-primary" id="updateBtn">저장</button>
	        <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">닫기</button>
	      </div>
	    </div>
	  </div>
	</div>
	
		
	<!-- 페이징 라인  -->
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item ${(ph.prev eq false) ? 'disabled' : '' }">
				<a class="page-link"
				href="/menu/list?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
				aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
			</li>
			<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
				<li class="page-item">
					<a class="page-link" 
						href="/menu/list?pageNo=${i }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i}</a>
				</li>
			</c:forEach>
			<li class="page-item ${(ph.next eq false) ? 'disabled' : '' }">
				<a class="page-link" 
					href="/menu/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" 
					aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
			</li>
		</ul>
	</nav>
</div>

<script src="/resources/js/menu/list.js"></script>
<script src="/resources/js/menu/menuImage.js"></script>
<jsp:include page="../layout/footer.jsp"></jsp:include>