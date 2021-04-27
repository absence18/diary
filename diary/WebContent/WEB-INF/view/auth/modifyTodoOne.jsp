<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyTodoOne</title>
</head>
<body>
	<h1>modifyTodoOne</h1>
	<form action = "${pageContext.request.contextPath }/auth/modifyTodoOne" method = "post">
		<input type = "hidden" value = "${todoOne.todoNo}" name = "todoNo">
		<table border = "1">
			<tr>
				<td>todoDate</td>
				<td><input type = text value = "${todoOne.todoDate}" readonly="readonly"></td>
			</tr>
			<tr>
				<td> todoTitle</td>
				<td><input type = text value = "${todoOne.todoTitle}" name ="todoTitle"></td>
			</tr>
			<tr>
				<td>todoContent</td>
				<td><textarea cols="80" rows="5" name = "todoContent">${todoOne.todoContent}</textarea></td>
			</tr>
			<tr>
				<td>todoFontColor</td>
				<td><input type="color" value ="${todoOne.todoFontColor}" name="todoFontColor"></td>
			</tr>
		</table>
		<button type = "submit">수정완료</button>
	</form>
</body>
</html>
