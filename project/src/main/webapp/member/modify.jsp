<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Member Modify Page</h1>
<form action="/mem/update" method="post">
email:<input type="text" name="email" value="${mvo.email}" readonly ><br>
pwd:<input type="text" name="pwd" value="${mvo.pwd }"><br>
nick_name:<input type="text" name="nick_name" value="${mvo.nick_name}" ><br>
reg_at:<input type="text" name="reg_at" value="${mvo.reg_at}" readonly ><br>
last_login:<input type="text" name="reg_at" value="${mvo.last_login}" readonly ><br>
<button type="submit">수정</button>
</form>
</body>
</html>