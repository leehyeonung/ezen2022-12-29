<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="/index.jsp">
<h1>Board Register Page</h1>
</a>
	<form action="/brd/insert" method="post" enctype="multipart/form-data">
	title:<input type="text" name="title"><br>
	email:<input type="text" name="writer" value="${ses.email}" readonly><br>
	content:<textarea rows="3" cols="30" name="content"></textarea><br>
	image_file:<input type="file" id="file" name="image_file"
	accept="image/png, image/jpg, image/jpeg, image/gif, image/jfif">
		<button type="submit" class="btn btn-primary">등록</button>
	</form>		
</body>
</html>