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
   
   	<div id="menuWrapper">
   	
           <div id="menuHeader">
              <a href = "controller.html?action=logout"><fmt:message key="loginOut.Out" bundle="${msgs}" /></a>
              <a href = "controller.html?action=doctorCommand"><fmt:message key="startPage.main" bundle="${msgs}" /></a>
            </div>
  		</div>
  </div> <!-- конец header -->