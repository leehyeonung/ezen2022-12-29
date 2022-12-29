<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="/index.jsp">
<h1>Join Page</h1>
</a>
<form action="/mem/register" method="post">
	Email : <input type="text" name="email"><br>
	PassWord : <input type="password" name="pwd"><br>
	Nick_Name : <input type="text" name="nick_name"><br>
	<button type="submit" class="btn btn-primary">join</button>
</form>
</body>
</html>