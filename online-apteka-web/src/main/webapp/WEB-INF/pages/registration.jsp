<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<fmt:setBundle basename="com.finalproject.onlineapteka.messages.msgs" var="msgs" />

<head>
	<meta charset="utf-8">

	<link rel="stylesheet" href="css/style.css" type="text/css">

	<title>
		<fmt:message key="application.title" bundle="${msgs}" />
	</title>
</head>

<body>
	<div class="registration">
		<form method="post" action="registration.html" >
		
			<br />

			<input type="hidden" name="action" value="registration" />

			<fmt:message key="registration.userName.label" bundle="${msgs}" />
			<br />
			<input type="text" id="user_login" name="user_login" />

			<br />
			<br />

			<fmt:message key="registration.password.label" bundle="${msgs}" />
			<br />
			<input type="text" id="user_password" name="user_password" />

			
			<br />
			<br />
			<input type="submit" name="registration_but" value="<fmt:message key='registration.button' bundle='${msgs}' />" />
		</form>
		
	</div>
	
	
</body>
</html>