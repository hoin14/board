<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<title>Home</title>
</head>

<body>
	<p>
		<a href="/board/listPageSearch?num=1">게시물 목록</a><br /> <a href="/board/write">게시물
			작성</a><br /> <a href="/member/register">회원가입</a>
	</p>
	<form name="homeForm" method="post" action="/member/login">
		<c:if test="${member == null }">
			<div>
				<label for="userId"></label> <input type="text" id="userId"
					name="userId">
			</div>
			<div>
				<label for="userPass"></label> <input type="password" id="userPass"
					name="userPass">
			</div>
			<div>
				<button type="submit">로그인</button>
			</div>
		</c:if>
		<c:if test="${member != null }">
			<div>
				<p>${member.userId }님환영합니다.</p>
					<button id="memberUpdateBtn" type="button">회원정보 수정</button>
					<button id="memberDeleteBtn" type="button">회원정보 탈퇴</button>
				<button id="logoutBtn" type="button">로그아웃</button>
			</div>
		</c:if>
		<c:if test="${msg == false && member == null}">
			<p style="color: red;">로그인 실패!</p>
		</c:if>
	</form>
	<script type="text/javascript">
		document.getElementById("logoutBtn").onclick = function() {
			location.href = "member/logout";
		};
		document.getElementById("memberUpdateBtn").onclick = function() {
			location.href = "member/update";
		};
		document.getElementById("memberDeleteBtn").onclick = function() {
			location.href = "member/delete";
		};
	</script>
</body>
</html>
