<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>${todoOne}</div>
	
	<h1>todoOne</h1>
	
	<table border = "1">
		<tr>
			<td>todoDate</td>
			<td>${todoOne.todoDate}</td>
		</tr>
		<tr>
			<td> todoTitle</td>
			<td>${todoOne.todoTitle}</td>
		</tr>
		<tr>
			<td>todoContent</td>
			<td>${todoOne.todoContent}</td>
		</tr>
		<tr>
			<td>todoFontColor</td>
			<td>${todoOne.todoFontColor}</td>
		</tr>
		<tr>
			<td>todoAddDate</td>
			<td>${todoOne.todoAddDate}</td>
		</tr>
	</table>
	
	<a href="${pageContext.request.contextPath}/auth/modifyTodoOne?todoNo=${todoOne.todoNo}"><button>todo수정</button></a>
	<a href="${pageContext.request.contextPath}/auth/removeTodoOne?todoNo=${todoOne.todoNo}"><button>todo삭제</button></a>
	
</body>
</html>
