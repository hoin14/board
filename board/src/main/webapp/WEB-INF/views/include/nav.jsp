<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style type="text/css">
li {
	list-style: none;
	display: inline;
	padding: 6px;
}
</style>

<ul>
	<li><a href="/board/listPageSearch?num=1">글 목록</a></li>
	<!-- 	<li><a href="/board/listPage?num=1">글 목록(페이징)</a></li> -->
	<!-- 	<li><a href="/board/listPage?num=1">글 목록</a></li> -->
	<li><a href="/board/write">글 작성</a></li>
	<li><c:if test="${member != null }">
			<a href="/member/logout">로그아웃</a>
		</c:if> <c:if test="${member == null }">
			<a href="/">로그인</a>
		</c:if></li>
	<li><c:if test="${member != null }">
			<p>${member.userId }님안녕하세요.</p>
		</c:if></li>
</ul>