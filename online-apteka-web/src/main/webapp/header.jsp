<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.requestLocale}"/>
<fmt:setBundle basename="com.finalproject.onlineapteka.messages.msgs" var="msgs" />

<head>
	<meta charset="utf-8">

	<link rel="stylesheet" href="css/style2.css" type="text/css">

	<title>
		<fmt:message key="application.title" bundle="${msgs}" />
	</title>
</head>

<body>
 <div id="wrapper">
   <div id="header">
   	<div class="name">
   		<a href = "controller.html?action=ru"><fmt:message key="startPage.ruLocale" bundle="${msgs}" /></a>
		<a href = "controller.html?action=en"><fmt:message key="startPage.enLocale" bundle="${msgs}" /></a>
		
		<fmt:message key="search" bundle="${msgs}" />
		<form method="post" action="controller.html" >
				<input type="hidden" name="action" value="searchDrugs" />
				<input type="text" id="search" name="search" size="52"/>
				<input type="submit" name="search_but" value="<fmt:message key="search" bundle="${msgs}" />" />
			</form>
		</div>
   	<div id="menuWrapper">
   	
           <div id="menuHeader">
              <a href = "index.jsp"><fmt:message key="startPage.main" bundle="${msgs}" /></a>
              <c:choose>
              	<c:when test="${sessionScope.userId != null}">
					<a href = "controller.html?action=logout" type="submit"><fmt:message key="loginOut.Out" bundle="${msgs}" /></a>	
					<a href = "controller.html?action=getOrders" type="submit"><fmt:message key="orders" bundle="${msgs}" /></a>
						</c:when>
				<c:when test="${sessionScope.userId == null}">	
              		<a href = "javascript:void(0)" onclick = "document.getElementById('envelope').style.display='block';document.getElementById('fade').style.display='block'"><fmt:message key="startPage.registration" bundle="${msgs}" /></a>
              		<a href = "login.jsp"><fmt:message key="startPage.login" bundle="${msgs}" /></a>
              		</c:when>
              		</c:choose>
              	<a href = "controller.html?action=getFromCart"><fmt:message key="startPage.shoppingCart" bundle="${msgs}" /></a>
            </div>
  		</div>
  		
  		<div id="logoName"><img src="css/img/apteka.jpg" width="120" height="120"></img></div>

		
  </div> <!-- конец header  -->
  </div>
  