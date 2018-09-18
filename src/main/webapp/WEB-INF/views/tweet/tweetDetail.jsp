<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tweet</title>
</head>
<body>

	<div>
		<h3>Menu</h3>
		<h4>
			<a href="${pageContext.request.contextPath}/user/logout">Logout</a>
			<a href="${pageContext.request.contextPath}/">Index</a>
			<a href="${pageContext.request.contextPath}/message/showMyMessages">My messages</a>
			<a href="${pageContext.request.contextPath}/message/add">Send new message</a>
			<a href="${pageContext.request.contextPath}/user/editMyProfile">Edit profile</a>
		</h4>
	</div>

	<div align="center">
		<h1 align="center">Tweet details:</h1>
		<h2>Author:</h2>
		<h3>${tweetDetail.user.username}</h3>
		<h2>Created:</h2>
		<h3>${tweetDetail.fullDate}</h3>
		<h2>Text:</h2>
		<h3>${tweetDetail.text}</h3>
		<br>
		<h2>No. of comments:</h2>
		<h3>${count}</h3>
		<br>
	</div>


	<div align="center">
	<h1 align="center">Comments</h1>
		<table align="center">
			<tr>
				<th>Author</th>
				<th>Created</th>
				<th>Content</th>
			</tr>
			<c:forEach items="${comments }" var="comment">
				<tr>
					<td align="center">${comment.user.username}</td>
					<td align="center">${comment.fullDate}</td>
					<td align="center">${comment.text}</td>
				</tr>
			</c:forEach>
		</table>

	</div>
	
	<div align="center">
	<h1 align="center">Add new Comment</h1>
	<h1 align="center">FORM</h1>

	<form:form method="post" modelAttribute="comment" action="../../comment/add">
		<div>
			<label>Text</label>
			<form:textarea path="text" />
			<form:errors path="text" cssStyle="color:red;" />
		</div>
		<div>
			<label>User</label>
			<form:select path="user.id"  >
				<form:option value="${tweetDetail.user.id}" label="${tweetDetail.user.username}" />
			</form:select>
			<form:errors path="user" cssStyle="color:red;" />
		</div>
		<div>
			<label>Tweet</label>
			<form:select  path="tweet.id"  >
				<form:option  value="${tweetDetail.id}" label="${tweetDetail.id}" />
			</form:select>
			<form:errors path="tweet" cssStyle="color:red;" />
		</div>
		<div>
			<form:hidden path="id"  />
			<input type="submit" value="Send">
		</div>
	</form:form>
	
	
	</div>

</body>
</html>