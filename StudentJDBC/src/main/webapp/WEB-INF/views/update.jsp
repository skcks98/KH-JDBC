<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${std.stdName} 정보 수정</title>
</head>
<body>

	<h4>학생 정보 수정</h4>

	<form action="${pageContext.request.contextPath}/std/update" method="post">
        <input type="hidden" name="stdNo" value="${std.stdNo}" />
        
        <label for="name">이름:</label>
        <input type="text" id="name" name="stdName" value="${std.stdName}" required> <br>
        
        <label for="age">나이:</label>
        <input type="number" id="age" name="stdAge" value="${std.stdAge}" required> <br>
        
        <label for="gender">성별:</label>
        <select id="gender" name="stdGender" required> 
            <option value="M" <c:if test="${std.stdGender == 'M'}">selected</c:if>>남성</option>
            <option value="F" <c:if test="${std.stdGender == 'F'}">selected</c:if>>여성</option>
        </select> <br>
        
        <label for="score">성적:</label>
        <input type="text" id="score" name="stdScore" value="${std.stdScore}" required> 

        <button type="submit">수정</button>
    </form>
    
    <%-- session 스코프에 message 있으면 alert 출력하기 --%>
	<c:if test="${not empty sessionScope.message}">
		<script>
			alert("${message}");
		</script>

		<c:remove var="message" scope="session" />
	</c:if>



</body>
</html>