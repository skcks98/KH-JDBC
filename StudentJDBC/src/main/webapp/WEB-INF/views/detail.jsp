<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${std.stdName}상세조회</title>
<link rel="stylesheet" href="/resources/css/detail.css">
</head>
<body>

	<%-- 학생 이름 --%>
	<h1>${std.stdName}님의 상세 정보</h1>

	<div class="detail-container">
		<table class="detail-table">
			<tr>
				<th>학번</th>
				<td>${std.stdNo}</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${std.stdName}</td>
			</tr>
			<tr>
				<th>나이</th>
				<td>${std.stdAge}</td>
			</tr>
			<tr>
				<th>성별</th>
				<td>${std.stdGender}</td>
				
			</tr>
			<tr>
				<th>성적</th>
				<td>${std.stdScore}학점</td>
			</tr>
		</table>

		<div class="btn-area">
			<button type="button" id="updateBtn" data-std-no="${std.stdNo}">수정하기</button>
			<button type="button" id="deleteBtn" data-std-no="${std.stdNo}">삭제하기</button>
			<button type="button" id="goToListBtn">목록으로</button>
		</div>
	</div>

	<%-- session에 message가 있다면 --%>
	<c:if test="${not empty sessionScope.message}">
		<script>
			alert("${message}");
		</script>
		<c:remove var="message" scope="session" />
	</c:if>
	
	
	<script src="/resources/js/detail.js"></script>

</body>
</html>