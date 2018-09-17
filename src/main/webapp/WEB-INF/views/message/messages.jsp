<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div align="center">
	<h1>My messages</h1>
	
	<table align="center">
		<tr>
			<th>Sender</th>
			<th>IsNew?</th>
			<th>Content</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${messages }" var="message">
			<tr>
				<td align="center">${message.sender.username}</td>
				<td align="center">${message.isNewString}</td>
				<td align="center">${message.text}</td>
				<td align="center"><a href="../message/delete/${message.id}">Delete</a><a
					href="../message/detail/${message.id}""> | Show detail</a></td>
			</tr>
		</c:forEach>
	</table>

</div>

<div align="center">
	<h1>Sended messages</h1>
	
	<table align="center">
		<tr>
			<th>Reciever</th>
			<th>Has the reciever read the message?</th>
			<th>Content</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${sendedMessages }" var="sendedMessages">
			<tr>
				<td align="center">${sendedMessages.receiver.username}</td>
				<td align="center">${sendedMessages.isNewString}</td>
				<td align="center">${sendedMessages.text}</td>
				<td align="center"><a href="../message/detail/${sendedMessages.id}""> | Show detail</a></td>
			</tr>
		</c:forEach>
	</table>

</div>

</body>
</html>