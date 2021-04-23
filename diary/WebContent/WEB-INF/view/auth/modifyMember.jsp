<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyMember</title>
</head>
<body>
	<h1>회원정보 수정</h1>
	<form action = "${pageContext.request.contextPath }/auth/modifyMember" method = "post">
		<div>
		
			<div>ID : </div>
			<div><input type = "text" name = "memberId" value="${sessionMember.memberId}" readonly="readonly"></div>
			<div>새로운비밀번호 : </div>
			<div><input type = "password" name = "newMemberPw"></div>
			<div><button type = "submit">수정완료</button></div>
			
		</div>
	</form>
</body>
</html> 