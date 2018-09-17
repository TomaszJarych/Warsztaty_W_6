<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User home page</title>
</head>
<body>
	<div>
		<h3>Menu</h3>
		<h4>
			<a href="../user/logout">Logout</a>
		</h4>

	</div>
	<h2>Greetings traveler!</h2>
	<br>
	<br>

	<h1 align="center">Tweet list:</h1>

	<table align="center">
		<tr>
			<th>Created</th>
			<th>Content</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${tweets }" var="tweet">
			<tr>
				<td align="center">${tweet.fullDate}</td>
				<td align="center">${tweet.text}</td>
				<td align="center"><a href="../tweet/update/${tweet.id}">Edit
				</a> | <a href="../tweet/delete/${tweet.id}"> | Delete</a><a
					href="../tweet/detail/${tweet.id}""> | Show detail</a></td>
			</tr>
		</c:forEach>
	</table>

	<div align="center">
		<h1 align="center">Add new Tweet</h1>

		<form:form method="post" modelAttribute="tweet" action="../tweet/add">
			<div>
				<label>Text</label>
				<form:textarea path="text" />
				<form:errors path="text" cssStyle="color:red;" />
			</div>
			<div>
				<label>User</label>
				<form:select path="user.id">
					<form:option value="${userDto.id}" label="${userDto.username}" />
				</form:select>
				<form:errors path="user" cssStyle="color:red;" />
			</div>
			<div>
				<br>
				<form:hidden path="id" />
				<br> <input type="submit" value="Send">
			</div>
		</form:form>
	</div>
</body>
</html>