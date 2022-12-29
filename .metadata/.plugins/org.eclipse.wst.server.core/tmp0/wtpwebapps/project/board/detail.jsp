<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</head>
<body onload="printCommentList(${bvo.bno})">
<a href="/index.jsp">
<h1>Board Detail Page</h1>
</a>
<div>
<img alt="없음." src="/_fileUpload/${bvo.image_file}">
</div>
<table border="1">
	<tr>
		<th>BNO</th>
		<td>${bvo.bno}</td>
	</tr>
	<tr>
		<th>TITLE</th>
		<td>${bvo.title}</td>
	</tr>
	<tr>
		<th>WRITER</th>
		<td>${bvo.writer}</td>
	</tr>
	<tr>
		<th>REG_dATE</th>
		<td>${bvo.reg_Date}</td>
	</tr>
	<tr>
		<th>CONTENT</th>
		<td>${bvo.content}</td>
	</tr>
	<tr>
		<th>read_count</th>
		<td>${bvo.read_count}</td>
	</tr>
</table>

<c:if test="${bvo.writer eq ses.email}">
<a href="/brd/modify?bno=${bvo.bno}">수정</a><br>
<a href="/brd/remove?bno=${bvo.bno}">삭제</a><br>
</c:if>

<a href="/brd/pageList">리스트</a><br>
<div>
	comment line<br>
	<input type="text" id="cmtWriter" value="${ses.email}" readonly="readonly"><br>
	<input type="text" id="cmtText" placeholder="Add Comment">
	<button type="button" id="cmtAddBtn" class="btn btn-primary">댓글등록</button>
</div>

<!-- 댓글 표시 영역 -->
<div class="accordion" id="accordionExample">
<!--댓글 아이템 표시 영역-->
  <div class="accordion-item">
    <h2 class="accordion-header" id="headingOne">
      <button class="accordion-button" type="button" data-bs-toggle="collapse"
      data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
      cno,bno,writer
  
      </button>
    </h2>
    <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
      <div class="accordion-body">
	content,reg_at
  
      </div>
    </div>
  </div>



<script type="text/javascript">
const bnoVal='<c:out value="${bvo.bno}"/>';
</script>
<script src="/resources/board_detail.js"></script>
<script type="text/javascript">
printCommentList(bnoVal);
</script>
</body>
</html>