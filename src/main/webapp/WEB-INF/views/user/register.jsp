<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User form</title>
</head>
<body>

	<div>
		<h3>Menu</h3>
		<h4>
			<a href="${pageContext.request.contextPath}/user/logout">Logout</a>
		</h4>
	</div>

	<h1 align="center">FORM</h1>

	<form:form method="post" modelAttribute="userDto" action="register">
		<div>
			<label>User name</label>
			<form:input path="username" />
			<form:errors path="username" cssStyle="color:red;" />
		</div>
		<div>
			<label>Email</label>
			<form:input path="email" />
			<form:errors path="email" cssStyle="color:red;" />
		</div>
		<div>
			<label>Password</label>
			<form:input path="password" />
			<form:errors path="password" cssStyle="color:red;" />
		</div>
		<div>
			<label>Is active?</label>
			<form:checkbox path="enabled"/>
		</div>
		<div>
			<form:hidden path="id" />
			<input type="submit" value="Send">
		</div>



	</form:form>



</body>
</html>