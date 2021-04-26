<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addTodo</title>
</head>
<body>

	<h1>todo 입력</h1>
	
	<form method = "post" action = "${pageContext.request.contextPath}/auth/addTodo">
	
		<div>todoDate : </div>
		<div><input type="text" name="todoDate" value="${todoDate.toString()}" readonly="readonly"></div>
		<div>todoTitle : </div>
		<div><input type = "text" name = "todoTitle"></div>
		<div>todoContent : </div>
		<div><textarea name = "todoContent" cols = "80" rows = "5"></textarea></div>
		<div>todoFontColor</div>
		<div><input type = "color" name = "todoFontColor"></div>
		<div><button type = "submit">addTodo</button></div>
	
	</form>

</body>
</html>