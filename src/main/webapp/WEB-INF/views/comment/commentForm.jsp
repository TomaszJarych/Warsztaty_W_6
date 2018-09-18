<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Comment form</title>
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

	<h1 align="center">FORM</h1>

	<form:form method="post" modelAttribute="comment" action="add">
		<div>
			<label>Text</label>
			<form:textarea path="text" />
			<form:errors path="text" cssStyle="color:red;" />
		</div>
		<div>
			<label>User</label>
			<form:select path="user.id"  >
				<form:option value="${userDto.id}" label="${userDto.username}" />
			</form:select>
			<form:errors path="user" cssStyle="color:red;" />
		</div>
		<div>
			<label>User</label>
			<form:select path="tweet.id"  >
				<form:option value="${tweet.id}" label="${tweet.id}" />
			</form:select>
			<form:errors path="tweet" cssStyle="color:red;" />
		</div>
		<div>
			<form:hidden path="id"  />
			<input type="submit" value="Send">
		</div>



	</form:form>



</body>
</html>