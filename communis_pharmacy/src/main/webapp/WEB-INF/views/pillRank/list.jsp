<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<div
  style="width: 100%; height: 843px; position: relative; overflow: hidden; background: linear-gradient(to bottom right, rgba(255,242,198,0) 0%, #fff2c6 50.73%, rgba(255,242,198,0) 100%);"
>
  <div style="width: 1141px; height: 677px; position: absolute; left: 50%; top: 50%; transform: translate(-50%, -50%);">
    <div style="width: 297px; height: 297px; position: absolute; left: 0px; top: 0px;">
      <img
        style="width: 297px; height: 297px; position: absolute; left: -1px; top: -1px; border-radius: 30px;"
        src="/resources/image/pillRank/vitamin_img.png"
      />
      <div
        style="width: 297px; height: 297px; position: absolute; left: 0px; top: 0px; overflow: hidden; border-radius: 30px;"
      >
        <div
          style="width: 297px; height: 297px; position: absolute; left: -1px; top: -1px; background: rgba(0,0,0,0.2);"
        ></div>
      </div>
      
      <div style="width: 150px; height: 39px; position: absolute; left: 104px; top: 129px;">
        <p style="position: absolute; left: 0px; top: 0px; font-size: 32px; font-weight: 700; text-align: left; color: #fff;">
          <a href="/pillRank/digestionList" style="text-decoration: none; color: #fff;">소화기</a>
        </p>
      </div>
    </div>
    
    <div style="width: 297px; height: 297px; position: absolute; left: 0px; top: 380px;">
      <img
        style="width: 297px; height: 297px; position: absolute; left: -1px; top: -1px; border-radius: 30px;"
        src="/resources/image/pillRank/flu_img.png"/>
      <div style="width: 297px; height: 297px; position: absolute; left: 0px; top: 0px; overflow: hidden; border-radius: 30px;">
        <div style="width: 297px; height: 297px; position: absolute; left: -1px; top: -1px; background: rgba(0,0,0,0.2);"></div>
      </div>
      
      <div style="width: 150px; height: 39px; position: absolute; left: 104px; top: 129px;">
        <p style="position: absolute; left: 0px; top: 0px; font-size: 32px; font-weight: 700; text-align: left; color: #fff;">
          <a href="#" style="text-decoration: none; color: #fff;">호흡기</a>
        </p>
      </div>
    </div>
    
    <div style="width: 297px; height: 297px; position: absolute; left: 422px; top: 0px;">
      <img
        style="width: 297px; height: 297px; position: absolute; left: -1px; top: -1px; border-radius: 30px;"
        src="/resources/image/pillRank/skin_img.png"/>
      <div style="width: 297px; height: 297px; position: absolute; left: 0px; top: 0px;">
        <div style="width: 297px; height: 297px; position: absolute; left: -1px; top: -1px; border-radius: 30px; background: rgba(0,0,0,0.2);"></div>
      </div>
      
      <div style="width: 150px; height: 39px; position: absolute; left: 104px; top: 129px;">
        <p style="position: absolute; left: 15px; top: 0px; font-size: 32px; font-weight: 700; text-align: left; color: #fff;">
          <a href="#" style="text-decoration: none; color: #fff;">피부</a>
        </p>
      </div>
    </div>
    
    <div style="width: 297px; height: 297px; position: absolute; left: 422px; top: 380px;">
      <img
        style="width: 297px; height: 297px; position: absolute; left: -1px; top: -1px; border-radius: 30px;"
        src="/resources/image/pillRank/goods_img.png"/>
      
      <div style="width: 150px; height: 39px; position: absolute; left: 104px; top: 129px;"></div>
      
      <div
        style="width: 297px; height: 297px; position: absolute; left: 0px; top: 0px; overflow: hidden; border-radius: 30px;">
        <div style="width: 297px; height: 297px; position: absolute; left: -1px; top: -1px; background: rgba(0,0,0,0.2);"></div>
      </div>
      
      <div style="width: 150px; height: 39px; position: absolute; left: 104px; top: 129px;">
        <p style="position: absolute; left: -14px; top: 0px; font-size: 32px; font-weight: 700; text-align: left; color: #fff;">
          <a href="#" style="text-decoration: none; color: #fff;">의료용품</a>
        </p>
      </div>
    </div>
    
    <div style="width: 297px; height: 297px; position: absolute; left: 844px; top: 0px;">
      <img
        style="width: 297px; height: 297px; position: absolute; left: -1px; top: -1px; border-radius: 30px;"
        src="/resources/image/pillRank/pain_img.png"
      />
      <div style="width: 297px; height: 297px; position: absolute; left: 0px; top: 0px; border-radius: 30px;">
        <div style="width: 297px; height: 297px; position: absolute; left: -1px; top: -1px; border-radius: 30px; background: rgba(0,0,0,0.2);"></div>
      </div>
      
      <div style="width: 150px; height: 39px; position: absolute; left: 104px; top: 129px;">
        <p style="position: absolute; left: 15px; top: 0px; font-size: 32px; font-weight: 700; text-align: left; color: #fff;">
          <a href="#" style="text-decoration: none; color: #fff;">통증</a>
        </p>
      </div>
    </div>
    
	<div style="width: 297px; height: 297px; position: absolute; left: 844px; top: 380px;">
      <img
        style="width: 297px; height: 297px; position: absolute; left: -1px; top: -1px; border-radius: 30px;"
        src="/resources/image/pillRank/pet_img.png"/>
      <div style="width: 297px; height: 297px; position: absolute; left: 0px; top: 0px; overflow: hidden; border-radius: 30px;">
        <div style="width: 297px; height: 297px; position: absolute; left: -1px; top: -1px; background: rgba(0,0,0,0.2);"></div>
      </div>
      
      <div style="width: 150px; height: 39px; position: absolute; left: 104px; top: 129px;">
        <p style="position: absolute; left: -14px; top: 0px; font-size: 32px; font-weight: 700; text-align: left; color: #fff;">
          <a href="#" style="text-decoration: none; color: #fff;">반려동물</a>
        </p>
      </div>
	</div>
      
  </div>
</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>

