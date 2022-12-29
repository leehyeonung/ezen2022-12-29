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
<h1>Member List Page</h1>
</a>
	<table border="1">
			<tr>
				<th>email</th>
				<th>password</th>
				<th>nick_name</th>
				<th>reg_at</th>
				<th>last_login</th>
			</tr>
			
			<c:forEach items="${list}" var="mvo">
				<tr>
					<td>${mvo.email}</td>					
					<td>${mvo.pwd}</td>				
					<td>${mvo.nick_name}</td>
					<td>${mvo.reg_at}</td>
					<td>${mvo.last_login}</td>
				</tr>
			</c:forEach>
		
		
	</table>

</body>
</html>