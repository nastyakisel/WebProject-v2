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
		<h2>Рецепт</h2>
		Имя доктора:
   		<h2><c:out value="${doctorUser.userName}" /></h2>
   		Id рецепта:
		<h2><c:out value="${recipe.id}" /></h2>
		
			Клиент:
			<br />
 			<c:out value="${userInRecipe.userName}, ${userInRecipe.id}" />
 			
			<br />
			<br />
			
			Срок действия:
			<br />
			<c:out value = "${recipe.beginDate} - ${recipe.endDate}"/>
			
			<br />
			<br />
			<table>
			<tr><td>Препарат:</td>
			<td>Количество:</td>
			
			<c:forEach items="${sessionScope.drugListInRecipe}" var="drug">
				<tr>
				<td><c:out value="${drug.drugName}, ${drug.dosage}" /></td>
				<td>
				<c:out value="${drug.quantity}" /></td>
			</c:forEach>
			</tr>
			</table>
	
			<br />
			<br />
			
			<form method="post" action="recipeDetails_2.jsp">
 				<!--  <input type="hidden" name="action" value="prolongRecipe" />
 				<input type="hidden" name="recipeId" value="${recipe.id}" /> -->
 				<input type="submit" name="addRecipe_but" value="Продлить" onclick="this.value='Скрипт сработал'"  />
 				<!-- <input type="button" value="Перейти в корзину" onClick='location.href="cart.jsp"'>-->
 			</form>
			
		
		
	</div>
	
	
</body>
</html>