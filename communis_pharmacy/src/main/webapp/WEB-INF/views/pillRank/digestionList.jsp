<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<div>
<div style="width: 100%; height: 290px; position: relative; overflow: hidden;">
  <div
    style="width: 100%; height: 290px; position: absolute; left: 0px; top: 0px; overflow: hidden; background: url(/resources/image/pillRank/ad_img.png); background-size: cover; background-repeat: no-repeat; background-position: center;"
  >
  </div>
</div>
	<!-- search line -->
	<br>
	<div class="col-sm-12 col-md-6">
		<form action="/pillRank/digestionList" method="get">
			<div class="input-group mb-3">
				<c:set value="${ph.pgvo.type }" var="typed"></c:set>
				<select class="form-select" name="type" id="inputGroupSelect01">
					<option ${typed eq null ? 'selected' : '' }>선택</option>
					<option value ="p" ${typed eq 'p' ? 'selected' : '' }>제품명</option>
					<option value ="e" ${typed eq 'e' ? 'selected' : '' }>제조사</option>
					<option value ="pe" ${typed eq 'pe' ? 'selected' : '' }>전체</option>
				</select>
				<input type="hidden" name="pageNo" value="1">
				<input type="hidden" name="qty" value="${ph.pgvo.qty}">
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
	
	<div class="container-md">
		<div class="row row-cols-3">
			<c:forEach items="${mdtoList}" var="mdtoList">
				<div class="col">
					<input type="hidden" name="pillId" value="${mdtoList.pvo.pillId}">
					<div data-pillid="${mdtoList.pvo.pillId}">
						<div class="container text-center">
							<div class="row" id="pillRankItemList">
								<div class="col" id="pillRankItem">
									<!-- 파일 line -->
									<c:choose>
										<c:when test="${empty mdtoList.pillImgList}">
											<!-- 이미지가 없는 경우 대체 이미지 표시 -->
											<div class="col-12">
												<label for="f" class="form-label"></label>
												<ul class="list-group list-group-flush">
													<li class="list-group-item">
														<div>
															<img alt=""
																src="/resources/image/pillRank/pill_img_empty.png"
																style="width: 200px; height: 200px;">
														</div>
													</li>
												</ul>
											</div>
										</c:when>
										<c:otherwise>
											<!-- 이미지가 있는 경우 해당 이미지 표시 -->
											<div class="col-12">
												<label for="f" class="form-label"></label>
												<ul class="list-group list-group-flush">
													<c:forEach items="${mdtoList.pillImgList}" var="pivo">
														<li class="list-group-item">
															<div>
																<img alt=""
																	src="/upload/${pivo.pillImgSaveDir}/${pivo.pillImgId}_${pivo.pillImgName}"
																	style="width: 200px; height: 200px;">
															</div>
														</li>
													</c:forEach>
												</ul>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
								<div id="pillName">
									<a href="#">${mdtoList.pvo.itemName}</a>
								</div>
								<div id="coName">${mdtoList.pvo.entpName}</div>
								<div>${mdtoList.pvo.pillPrice}원</div>
								<div>
									<button type="button">장바구니</button>
									<a href="/orderController/orderCheck?pillId=${mdtoList.pvo.pillId}"><button type="button">바로구매</button></a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
			
			<!-- 페이징라인 -->
			<nav aria-label="Page navigation example">
			<br>
				<ul class="pagination">
					<li class="page-item ${(ph.prev eq false) ? 'disabled' : '' }">
						<a class="page-link"
						href="/pillRank/digestionList?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
						aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
					</li>
					<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
						<li class="page-item"><a class="page-link"
							href="/pillRank/digestionList?pageNo=${i }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i}</a>
						</li>
					</c:forEach>
					<li class="page-item ${(ph.next eq false) ? 'disabled' : '' }">
						<a class="page-link"
						href="/pillRank/digestionList?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
						aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
					</li>
				</ul>
			</nav>
		</div>
	</div>
</div>
	<!-- <script src="/resources/js/pillRank/pillRankDetail.js"></script>
<script type="text/javascript">
	spreadPillNameFromAPI();
</script> -->
<jsp:include page="../layout/footer.jsp"></jsp:include>