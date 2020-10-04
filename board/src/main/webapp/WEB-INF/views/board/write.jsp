<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<title>게시물 작성</title>
</head>
<body>
	<div class="container">
		<header>
			<h1>게시판</h1>
		</header>
		<div id="nav">
			<%@ include file="../include/nav.jsp"%>
		</div>

		<section id="container">
			<form method="post">
				<div class="form-group">
					<label class="col-sm-2 control-label">제목</label> <input type="text"
						name="title" class="form-control" /><br /> <label
						class="col-sm-2 control-label">작성자</label> <input type="text"
						name="writer" class="form-control" /><br /> <label
						class="col-sm-2 control-label">내용</label>
					<textarea cols="50" rows="5" name="content" class="form-control"></textarea>
					<br />
				<div class="col-sm-1 col-sm-10">
						<button type="submit" class="replyWriteBtn btn btn-success">
							작성</button>
					</div>
				</div>
			</form>
		</section>
	</div>
</body>
</html>