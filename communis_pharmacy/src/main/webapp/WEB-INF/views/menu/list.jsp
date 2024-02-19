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
					<option value ="item" ${typed eq 'item' ? 'selected' : '' }>제품명</option>
					<option value ="entp" ${typed eq 'entp' ? 'selected' : '' }>제조사</option>
					<option value ="all" ${typed eq 'itementp' ? 'selected' : '' }>전체</option>
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
				<th scope="col">찜</th>
				<th scope="col">판매수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="pvo">
				<tr>
					<th scope="row">${pvo.pillId}</th>
					<td><a href="#">${pvo.itemName}</a> <img alt="등록된 이미지가 없습니다." src="${pvo.thumbnail}"></td>
					<td>${pvo.entpName}</td>
					<td>${pvo.pillDibs}</td>
					<td>${pvo.pillSell}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		
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
				<a class="page-link" href="/menu/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
			</li>
		</ul>
	</nav>
</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>