<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tweet form</title>
</head>
<body>
	<h1 align="center">FORM</h1>

	<form:form method="post" modelAttribute="tweet" action="add">
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
			<form:hidden path="id" />
			<input type="submit" value="Send">
		</div>



	</form:form>



</body>
</html>