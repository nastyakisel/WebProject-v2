<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.requestLocale}"/>
<fmt:setBundle basename="com.finalproject.onlineapteka.messages.msgs" var="msgs" />

<head>
	<link rel="stylesheet" href="css/style.css" type="text/css">

	<title>
		<fmt:message key="application.title" bundle="${msgs}" />
	</title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<body>
	<div class="registration">
		<h2>Рецепт успешно создан!</h2>
		<br />
		Id рецепта
		<h2><c:out value="${recipeId}" /></h2>
		<table>
		<tr>
		<td>
		<form method="post" action="${previousURI}">
				<!--  <input type="hidden" name="action" value="addDrugToRecipe" />-->
 				<input type="submit" name="addRecipe_but" value="Добавить еще препарат" onclick="this.value='Скрипт сработал'"  />
 				<!-- <input type="button" value="Перейти в корзину" onClick='location.href="cart.jsp"'>-->
 				
 			</form> </td>
		<td>
		<form method="post" action="controller.html">
 				<input type="hidden" name="action" value="assignRecipe" />
 				<input type="submit" name="addRecipe_but" value="Назначить новый рецепт" onclick="this.value='Скрипт сработал'"  />
 				<!-- <input type="button" value="Перейти в корзину" onClick='location.href="cart.jsp"'>-->
 				
 			</form></td>
		
	</div>
	
	
</body>
</html>