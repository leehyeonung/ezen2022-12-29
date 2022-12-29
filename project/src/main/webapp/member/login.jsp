<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Login Page</h1>
<form action="/mem/login_auth" method="post">
	Email : <input type="text" name="email"><br>
	PassWord : <input type="password" name="pwd"><br>
	<button type="submit" class="btn btn-primary">login</button>
</form>
</body>
</html>