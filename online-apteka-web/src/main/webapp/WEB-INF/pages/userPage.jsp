<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<head>
<meta charset="utf-8">
<fmt:setLocale value="${sessionScope.requestLocale}"/>
<fmt:setBundle basename="com.finalproject.onlineapteka.messages.msgs" var="msgs" />



	<link rel="stylesheet" href="css/style.css" type="text/css">

	<title>
		<fmt:message key="application.title" bundle="${msgs}" />
	</title>
</head>


<body>


	<div class="registration">
		
		<a href = "registration.jsp"><fmt:message key="startPage.registration" bundle="${msgs}" /></a>
	</div>
	
	<div class="login">
		
		<a href = "login.jsp"><fmt:message key="startPage.login" bundle="${msgs}" /></a>
		
	</div>
	<div class="locale">
		
		<a href = "controller.html?locale=ru"><fmt:message key="startPage.ruLocale" bundle="${msgs}" /></a>
		<a href = "controller.html?locale=en"><fmt:message key="startPage.enLocale" bundle="${msgs}" /></a>
		
	</div>
</body>
</html>