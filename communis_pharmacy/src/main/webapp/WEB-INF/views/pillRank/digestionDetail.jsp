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

<div class="container-md">
<!-- 인기 약제품 10개씩 뿌리기 -->
<div>
	<div class="container text-center">
	  <div class="row row-cols-5" id="pillRankItemList">
	    <div class="col" id="pillRankItem">
	    	<div><img src="#" alt="이미지가 없습니다."></div>
	    	<div id="pillName">약품명</div>
	    	<div id="coName">제조사</div>
	    	<div>
	    		<button>장바구니</button>
	    		<button>바로구매</button>
	    	</div>
	    </div>
	  </div>
	</div>
</div>
<br>

<!-- 페이징라인 -->
<nav aria-label="Page navigation example">
	<ul class="pagination">
		<li class="page-item ${(ph.prev eq false) ? 'disabled' : '' }">
			<a class="page-link"
				href="/board/list?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
				aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
		</li>
		<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
			<li class="page-item">
				<a class="page-link" 
						href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i}</a>
			</li>
		</c:forEach>
		<li class="page-item ${(ph.next eq false) ? 'disabled' : '' }">
			<a class="page-link" href="/board/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
		</li>
	</ul>
</nav>
</div>

</div>
<script src="/resources/js/pillRank/pillRankDetail.js"></script>
<jsp:include page="../layout/footer.jsp"></jsp:include>