<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<%@ include file="headerPharm.jsp" %>
	<div id="wrapper">

<body>
	<div class="registration">
		<h2><fmt:message key="doctor.Created" bundle="${msgs}" /></h2>
		<br />
		<fmt:message key="doctor.Recipe.Id" bundle="${msgs}" />
		<h2><c:out value="${recipeId}" /></h2>
		<br />
		<table>
		<tr>
		<td>
		<form method="post" action="${previousURI}">
				<!--  <input type="hidden" name="action" value="addDrugToRecipe" />-->
 				<input type="submit" name="addRecipe_but" value="<fmt:message key="doctor.Add.Drug" bundle="${msgs}" />" />
 				<!-- <input type="button" value="Перейти в корзину" onClick='location.href="cart.jsp"'>-->
 				
 			</form> </td>
		<td>
		<form method="post" action="controller.html">
 				<input type="hidden" name="action" value="assignRecipe" />
 				<input type="submit" name="addRecipe_but" value="<fmt:message key="doctor.assignRecipe" bundle="${msgs}" />" onclick="this.value='Скрипт сработал'"  />
 			</form></td>
			
</body>
</html>