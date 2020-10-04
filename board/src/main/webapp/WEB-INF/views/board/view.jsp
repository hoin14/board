<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
<title>게시물 조회</title>
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
			<div class="form-group">
				<label for="title" class="col-sm-2 control-label">${view.title}</label>
			</div>
			<div class="form-group">
				<label for="writer" class="col-sm-2 control-label">${view.writer}</label>
			</div>
			<div class="form-group">
				<label for="content" class="col-sm-2 control-label">${view.content}</label>
			</div>

			<div class="form-group">

				<label for="regdate" class="col-sm-2 control-label"> <fmt:formatDate
						value="${view.regDate}" pattern="yyyy-MM-dd" />
				</label>
			</div>
			<div>
				<a href="/board/modify?bno=${view.bno}">게시물 수정</a>, <a
					href="/board/delete?bno=${view.bno}">게시물 삭제</a>
			</div>

			<!-- 댓글 시작 -->
			<hr />
			<c:forEach items="${reply}" var="reply">

				<div>
					<p>${reply.rno}/${reply.bno}/${reply.writer}/<fmt:formatDate
							value="${reply.regDate}" pattern="yyyy-MM-dd" />
						<a href="/board/replyModify?rno=${reply.rno}&bno=${reply.bno}">[수정</a>/<a
							href="/board/replyDelete?rno=${reply.rno}&bno=${reply.bno}">삭제]</a>
					</p>
					<p>${reply.content }</p>
				</div>

			</c:forEach>

			<form method="post" name="replyWrite" class="form-horizontal">
				<div class="form-group">
					<label for="writer" class="col-sm-2 control-label">댓글 작성자</label>
					<div class="col-sm-2">
						<input type="text" name="writer" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label for="content" class="col-sm-2 control-label">댓글 내용</label>
					<div class="col-sm-10">
						<input type="text" name="content" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="replyWriteBtn btn btn-success">
							작성</button>
					</div>
				</div>

			</form>
			<!-- 댓글 끝 -->
		</section>
	</div>
</body>
</html>