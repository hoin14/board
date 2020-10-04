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
<title>Web Board</title>
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
			<table class="table taable-hover">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성일</th>
						<th>작성자</th>
						<th>조회수</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${list}" var="list">
						<tr>
							<td>${list.bno}</td>
							<td><a href="/board/view?bno=${list.bno}">${list.title}</a></td>
							<td>${list.regDate}</td>
							<td>${list.writer}</td>
							<td>${list.viewCnt}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<div class="search row">
				<div class="col-xs-2 col-sm-2">
					<select name="searchType" class="form-control">
						<option value="title"
							<c:if test="${page.searchType eq 'title'}">selected</c:if>>제목</option>
						<option value="content"
							<c:if test="${page.searchType eq 'content'}">selected</c:if>>내용</option>
						<option value="title_content"
							<c:if test="${page.searchType eq 'title_content'}">selected</c:if>>제목+내용</option>
						<option value="writer"
							<c:if test="${page.searchType eq 'writer'}">selected</c:if>>작성자</option>
					</select>
				</div>
				<div class="col-sx-10 col-sm-10">
					<div class="input-group">
						<input type="text" name="keyword" value="${page.keyword}"
							class="form-control" /> <span class="input-group-btn">
							<button type="button" id="searchBtn" class="btn btn-default">검색</button>
						</span>
					</div>
				</div>
			</div>

			<script>
				document.getElementById("searchBtn").onclick = function() {

					let searchType = document.getElementsByName("searchType")[0].value;
					let keyword = document.getElementsByName("keyword")[0].value;
					location.href = "/board/listPageSearch?num=1"
							+ "&searchType=" + searchType + "&keyword="
							+ keyword;
				};
			</script>

			<div class="col-md-offset-3">
				<ul class="pagination">
					<c:if test="${page.prev}">
						<span>[ <a
							href="/board/listPage?num=${page.startPageNum - 1}">이전</a> ]
						</span>
					</c:if>

					<c:forEach begin="${page.startPageNum}" end="${page.endPageNum}"
						var="num">
						<span> <c:if test="${select != num}">
								<a href="/board/listPage?num=${num}">${num}</a>
							</c:if> <c:if test="${select == num}">
								<b>${num}</b>
							</c:if>
						</span>
					</c:forEach>

					<c:if test="${page.next}">
						<span>[ <a
							href="/board/listPage?num=${page.endPageNum + 1}">다음</a> ]
						</span>
					</c:if>
				</ul>
			</div>
			<script>
		document.getElementById("searchBtn").onclick = function() {

			let searchType = document.getElementsByName("searchType")[0].value;
			let keyword = document.getElementsByName("keyword")[0].value;
			location.href = "/board/listPageSearch?num=1" + "&searchType="
					+ searchType + "&keyword=" + keyword;
		};
	</script>
		</section>
	</div>
</body>
</html>