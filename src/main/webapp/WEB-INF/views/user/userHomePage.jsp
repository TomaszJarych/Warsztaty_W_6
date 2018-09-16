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
				<td align="center">${tweet.created}</td>
				<td align="center">${tweet.text}</td>
				<td align="center"><a href="../tweet/update/${tweet.id}">Edit</a> | <a
					href="../tweet/delete/${tweet.id}"> Delete</a></td>
			</tr>


		</c:forEach>
	</table>

</body>
</html>