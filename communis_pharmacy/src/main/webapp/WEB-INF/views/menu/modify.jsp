<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<div class="container-md">
<c:set value="${mdto.pvo }" var="pvo"></c:set>
<input type="hidden" name="pillId" class="form-control" id="pillId" readonly value="${pvo.pillId }">
<br><hr><h2>${pvo.itemName }</h2><hr><br>
<form action="/menu/modify" method="post"  enctype="multipart/form-data">
	
	<div class="mb-3">
		<label for="itemName" class="form-label">제품명</label>
		<input type="text" name="itemName" class="form-control" 
			id="itemName" readonly value="${pvo.itemName }">
	</div>
	<div class="mb-3">
		<label for="entpName" class="form-label">제조사</label>
		<input type="text" name="entpName" class="form-control" 
			id="entpName" readonly value="${pvo.entpName }">
	</div>
	<div class="mb-3">
		<label for="pillReg" class="form-label">등록일</label> 
		<input type="text" name="pillReg" class="form-control" 
			id="pillReg" readonly value="${pvo.pillReg }">
	</div>
	<div class="mb-3">
		<label for="pillMod" class="form-label">업데이트 날짜</label> 
		<input type="text" name="pillMod" class="form-control" 
			id="pillMod" readonly value="${pvo.pillMod }">
	</div>
	<div class="mb-3">
		<label for="efcyQesitm" class="form-label">효과·효능</label>
		<input type="text" name="efcyQesitm" class="form-control" 
			id="efcyQesitm" readonly value="${pvo.efcyQesitm }">
	</div>
	<div class="mb-3">
		<label for="thumbnail" class="form-label">제품사진</label>
		<img src="${pvo.thumbnail }" name="thumbnail" class="form-control" 
			id="thumbnail" style="max-width: 10%;" readonly>
	</div>
	<div class="mb-3">
		<label for="pillDibs" class="form-label">찜</label>
		<input type="text" name="pillDibs" class="form-control" 
			id="pillDibs" readonly value="${pvo.pillDibs }">
	</div>
	<div class="mb-3">
		<label for="pillSell" class="form-label">판매량</label>
		<input type="text" name="pillSell" class="form-control" 
			id="pillSell" readonly value="${pvo.pillSell }">
	</div>
	<div class="mb-3">
		<label for="pillStock" class="form-label">재고</label>
		<input type="text" name="pillStock" class="form-control" 
			id="pillStock" readonly value="${pvo.pillStock }">
	</div>
	
 	<!-- 파일 line -->
	<c:set value="${mdto.pillImgList}" var="pillImgList"></c:set>
		<div class="col-12">
			<label for="f" class="form-label"></label>
			<ul class="list-group list-group-flush">
				<c:forEach items="${pillImgList}" var="pivo">
					<li class="list-group-item">
						<c:choose>
							<c:when test="${pivo.pillImgType == 1 }">
								<div>
									<img alt="" src="/upload/${pivo.pillImgSaveDir }/${pivo.pillImgId}_th_${pivo.pillImgName}">
								</div>
							</c:when>
							<c:otherwise>
								<div>
									<!-- 일반 파일을 표시할 아이콘 -->
									<a href="/upload/${pivo.pillImgSaveDir }/${pivo.pillImgId}_${pivo.pillImgName}" download="${pivo.pillImgName }">
									<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-file-earmark-arrow-down" viewBox="0 0 16 16">
  <path d="M8.5 6.5a.5.5 0 0 0-1 0v3.793L6.354 9.146a.5.5 0 1 0-.708.708l2 2a.5.5 0 0 0 .708 0l2-2a.5.5 0 0 0-.708-.708L8.5 10.293V6.5z"/>
  <path d="M14 14V4.5L9.5 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2zM9.5 3A1.5 1.5 0 0 0 11 4.5h2V14a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h5.5v2z"/>
</svg>
									</a>
								</div>
							</c:otherwise>
						</c:choose>
						<div class="ms-2 me-auto">
							<div class="fw-bold">
								${pivo.fileName}<br>
								<span class="badge rounded-pill text-bg-secondary">${pivo.pillImgSize }Byte</span>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div> 
		
		<!-- Image File 입력 라인 추가 -->
 		<div class="mb-3">
			<input type="file" name="files" class="form-control" id="files" multiple="multiple" style="display: none"><br>
			<!-- 파일 버튼 트리거 사용하기 위해서 주는 버튼 -->
			<button type="button" class="btn btn-primary" id="trigger">사진 업데이트</button>
		</div>

		<!-- 파일 목록 표시라인 -->
 		<div class="mb-3" id="fileZone">
 			
		</div>
		
	<a href="/menu/modify?pillId=${pvo.pillId }"><button type="submit" class="btn btn-success">수정</button></a> 
	<a href="/menu/remove?pillId=${pvo.pillId }"><button type="button" class="btn btn-danger">삭제</button></a>
	<a href="/menu/list"><button type="submit" class="btn btn-primary">목록</button></a>
	</form>
</div>

<script src="/resources/js/menu/menuImage.js"></script>
<jsp:include page="../layout/footer.jsp"></jsp:include>