<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>회원가입</h1>
	
	<form action = "${pageContext.request.contextPath }/signUp" method = "post">
	
		<div>
		
			<div>ID : </div>
			<div><input type = "text" name = "memberId"></div>
			<div>PW : </div>
			<div><input type = "password" name = "memberPw"></div>
			<div><button type = "submit">회원가입</button></div>
			
		</div>
		
	</form>
</body>
</html> 