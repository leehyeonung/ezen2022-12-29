<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Register Page</h1>

<form action="/pro/register" method="post">
	id: <input type="text" name="id"><br>
	password:<input type="text" name="password"><br>
	email:<input type="text" name="email"><br>
	age:<input type="text" name="age"><br>
	
	<button type="submit">등록하기</button>
</form>
</body>
</html>