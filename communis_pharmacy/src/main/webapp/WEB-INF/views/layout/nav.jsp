<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Bootstrap 내비게이션 바 -->
<nav class="navbar navbar-expand-lg">
  <div class="container-fluid">
    <a class="navbar-brand" href="/">꼼뮤니스</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" 
    data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" 
    aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">약사 TALK</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/pillRank/list">인기 약품</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/menu/register">약품등록</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">가까운 약국</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">로그인</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">회원가입</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
