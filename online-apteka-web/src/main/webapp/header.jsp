<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.requestLocale}"/>
<fmt:setBundle basename="com.finalproject.onlineapteka.messages.msgs" var="msgs" />

<head>
	<meta charset="utf-8">

	<link rel="stylesheet" href="css/style2.css" type="text/css">
	<script type="text/javascript" src="js/registration.js"></script>
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
					<a href = "controller.html?action=getUserRecipes" type="submit"><fmt:message key="user.Recipes" bundle="${msgs}" /></a>
						</c:when>
				<c:when test="${sessionScope.userId == null}">	
              		<a href = "javascript:void(0)" onclick = "document.getElementById('envelope').style.display='block';document.getElementById('fade').style.display='block'"><fmt:message key="startPage.registration" bundle="${msgs}" /></a>
              		<a href = "javascript:void(0)" onclick = "document.getElementById('envelope2').style.display='block';document.getElementById('fade').style.display='block'"><fmt:message key="startPage.login" bundle="${msgs}" /></a>

              		</c:when>
              		</c:choose>
              	<a href = "controller.html?action=getFromCart"><fmt:message key="startPage.shoppingCart" bundle="${msgs}" /></a>
            </div>
  		</div>
  		
  		<div id="logoName"><img src="css/img/apteka.jpg" width="120" height="120"></img></div>

	<c:if test="${sessionScope.has_error != null}">
				<script type="text/javascript">
					var delay_popup = 20;
					setTimeout("document.getElementById('envelope2').style.display='block';document.getElementById('fade').style.display='block'", delay_popup);
					</script>
						
				</c:if>
	<c:if test="${sessionScope.reg_error != null}">
				<script type="text/javascript">
					var delay_popup = 20;
					setTimeout("document.getElementById('envelope').style.display='block';document.getElementById('fade').style.display='block'", delay_popup);
					</script>
						
				</c:if>
				
	<div id="fade" class="black-overlay"></div>
		
		<div id="envelope2" class="envelope2">
		
		
		<a class="close-btn" title="Закрыть" href="javascript:void(0)" onclick = "document.getElementById('envelope2').style.display='none';document.getElementById('fade').style.display='none'"></a>
			<br>
			<br>
			<form method='post' class='registration' action="controller.html" onsubmit="return loginvalidate(this);">
				<c:if test="${sessionScope.has_error != null}">
					<c:if test="${has_error.errorMessage == 'loginPage.errorUser'}">
						<span class="errorText">
							<fmt:message key="loginPage.errorUser" bundle="${msgs}" />
						</span>
						
					</c:if>
				
			</c:if>
				<input type="hidden" name="action" value="login" />
				<input type="hidden" name="locale" value="${sessionScope.requestLocale}" />
				<div class='input_form'>
					<label><fmt:message key="loginPage.userName.label" bundle="${msgs}" /></label>
    				<input type='text' id='user_login' name='user_login' value = "${sessionScope.current_user}">
  					</div>
  				<div class='input_form'>
    				<label><fmt:message key="loginPage.password.label" bundle="${msgs}" /></label>
    				<input type='text' id='user_password' name='user_password'>
					</div>
				
				<div class="knopka">
					<br>
					<input type="submit" value="<fmt:message key="loginPage.button" bundle="${msgs}" />">
					</div>
		</form>	
		</div>
		
		
		<div id="envelope" class="envelope">
		<a class="close-btn" title="Закрыть" href="javascript:void(0)" onclick = "document.getElementById('envelope').style.display='none';document.getElementById('fade').style.display='none'"></a>
			<br>
			<br>
			<form method='post' class='registration' action="controller.html" onsubmit="return validate(this);">
			<c:if test="${sessionScope.reg_error != null}">
					<c:if test="${reg_error.errorMessage == 'registrationPage.existUserError'}">
						<span class="errorText">
							<fmt:message key="registrationPage.existUserError" bundle="${msgs}" />
						</span>
						</c:if>
					</c:if>
				<input type="hidden" name="action" value="registration" />
				<input type="hidden" name="locale" value="${sessionScope.requestLocale}" />
				
				<div class='input_form'>
					<label><fmt:message key="registration.firstName" bundle="${msgs}" /></label>
    				<input type='text' id='first_name' name='first_name'>
  					</div>
				<div class='input_form'>
					<label><fmt:message key="registration.lastName" bundle="${msgs}" /></label>
    				<input type='text' id='second_name' name='second_name'>
  					</div>
  				<div class='input_form'>
    				<label><fmt:message key="registration.password" bundle="${msgs}" /></label>
    				<input type='text' id='password' name='password'>
					</div>
				<div class='input_form'>
    				<label><fmt:message key="registration.password.repeat" bundle="${msgs}" /></label>
    				<input type='text' id='repeat_password' name='repeat_password'>
					</div>
				<div class='input_form'>
					<label><fmt:message key="registration.email" bundle="${msgs}" /></label>
					<input type='text' id='email' name='email'>
					</div>
				<div class="knopka">
					<br>
					<input type="submit" value="<fmt:message key="registration.button" bundle="${msgs}" />">
					</div>
		</form>	
		
		<div id="fade" class="black-overlay"></div>
		
		</div>
		
		
  </div> <!-- конец header  -->
  </div>
  