<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>

</head>
<body>
<a href="/index.jsp">
<h1>Member Detail Page</h1>
</a>
<table border="1">
	<tr>
		<th>email</th>
		<td>${mvo.email}</td>
	</tr>
	<tr>
		<th>pwd</th>
		<td>${mvo.pwd}</td>
	</tr>
	<tr>
		<th>nick_name</th>
		<td>${mvo.nick_name}</td>
	</tr>
	<tr>
		<th>reg_at</th>
		<td>${mvo.reg_at}</td>
	</tr>
	<tr>
		<th>last_login</th>
		<td>${mvo.last_login}</td>
	</tr>
</table>
<a href="/mem/modify?email=${mvo.email}">수정</a><br>
<a href="/mem/remove?email=${mvo.email}">삭제</a><br>
<a href="/mem/list">리스트</a><br>
</body>
</html>