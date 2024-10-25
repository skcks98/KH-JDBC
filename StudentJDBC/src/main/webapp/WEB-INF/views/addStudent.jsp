<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 추가</title>

<link rel="stylesheet" href="/resources/css/addStudent.css">
<!-- 추가할 CSS 파일 -->
</head>

<body>

	<div class="container">
		<h2>학생 추가</h2>

		<c:if test="${not empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/std/add"
			method="post">
			<label for="name">이름:</label> <input type="text" id="name"
				name="stdName" required><br> <label for="age">나이:</label>
			<input type="number" id="age" name="stdAge" required> <br>

			<label for="gender">성별:</label> <select id="gender" name="stdGender"
				required>
				<option value="M">남성</option>
				<option value="F">여성</option>
			</select> <br> <label for="score">성적:</label> <select id="score"
				name="stdScore" required>
				<option value="A">A</option>
				<option value="B">B</option>
				<option value="C">C</option>
				<option value="D">D</option>
				<option value="F">F</option>
			</select> <br>

			<button type="submit">추가</button>
			<button onclick="location.href='${pageContext.request.contextPath}/'">메인으로
				돌아가기</button>
		</form>


	</div>

</body>
</html>