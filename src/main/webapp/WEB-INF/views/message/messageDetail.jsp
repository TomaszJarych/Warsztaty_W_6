<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Message</title>
</head>
<body>
	<div align="center">
		<h1 align="center">Message details:</h1>
		<h2>Author:</h2>
		<h3>${message.sender.username}</h3>
		<br>
		<h2>Text:</h2>
		<h3>${message.text}</h3>
		<br>
	</div>

</body>
</html>