<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
   
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>student List</title>

  <%-- css 파일 연결 (webapp 폴더 기준으로 경로 작성)--%>
  <link rel="stylesheet" href="/resources/css/main.css">
</head>
<body>
  <h1>학생관리 프로그램</h1>


  

  <hr>

  <%-- 학생 목록 출력 --%>
  <table id="stdList" border="1">
    <thead>
      <tr>
        <th>학생 번호</th> <!-- 페이지에서 보이는 용도의 단순 출력 번호 -->
        <th>학생 이름</th> <!-- 실제 이 데이터의 todoNo 고유번호 -->
        <th>학생 나이</th>
        <th>학생 성별</th>
        <th>학생 성적</th>
      </tr>
    </thead>
  
    <tbody>
      <c:forEach items="${stdList}" varStatus="vs" var="std">
        <tr>
          <th>${std.stdNo}</th> <%-- 학생 번호 --%>

          <td>
          	<%-- 학생 클릭 시
          		인덱스 번호를 이용하여 stdList의 
          		인덱스 번째 요소 내용을 조회하기
          		
          	 --%>
            <a href="/std/detail?stdNo==${std.stdNo}">${std.stdName}</a>
          </td> <%-- 학생 이름 --%>
          
		   <td>${std.stdAge}</td> <%-- 나이 --%>
         
           <td>${std.stdGender}</td> <%-- 성별 --%>

          <td>${std.stdScore}</td><%-- 성적 --%>
        </tr>
       </c:forEach>
    </tbody>
  </table>
  
  <%-- session 범위에 message가 있을 경우 --%>
  <c:if test="${not empty sessionScope.message}">
  	<script>
  		alert("${message}");
  		// JSP 해석 우선순위
  		// 1 순위 : Java(EL/JSTL)
  		// 2 순위 : Front(HTML,CSS,JS)
  	</script>
  	
  	<%-- message를 한 번만 출력하고 제거 --%>
  	<c:remove var="message" scope="session"/>
  </c:if>


  <%-- JS 연결 --%>
  <script src="/resources/js/main.js"></script>
</body>
</html>