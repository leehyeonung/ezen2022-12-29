<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="index.jsp">
<h1>My First Project Board & Member !!</h1>
</a>

<c:if test="${ses.email ne null&&ses.email ne ''}">
${ses.email } login하였습니다.<br>
계정생성일 : ${ses.reg_at}<br>
마지막 접속 : ${ses.last_login}<br>

<a href="/mem/modify?email=${ses.email}">
<button type="button" class="btn btn-primary">회원정보수정</button>
</a>

<a href="/mem/logout?email=${ses.email}"><button type="button" class="btn btn-primary">로그아웃</button></a>
</c:if>



<c:if test="${ses.email ne null&&ses.email ne ''}">
	<a href="/brd/register">Board Register</a>
</c:if>



<a href="/brd/pageList" class="link-danger">Board List</a>
<a href="/mem/login" class="link-warning">Log In</a>
<a href="/mem/join" class="link-success">Sign Up</a>
<a href="/mem/list" class="link-primary">Member List</a>
<a href="/brd/list2?email=${ses.email}"  class="link-secondary" >내가만든리스트보기</a>



<c:if test="${ses.email ne null&&ses.email ne ''}">
	<a href="/mem/remove?email=${ses.email}">회원탈퇴</a>
</c:if>

</body>
</html>