<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<%@ include file="header.jsp" %>
	<div id="wrapper">
	
<body>
	<div class="registration">
		<h2><fmt:message key="doctor.Recipe" bundle="${msgs}" /></h2>
		
			<br />
			<br />
		<fmt:message key="doctor.Name" bundle="${msgs}" />
   		<h2><c:out value="${recipe.doctorName}" /></h2>
   		<fmt:message key="doctor.Recipe.Id" bundle="${msgs}" />
		<h2><c:out value="${recipe.id}" /></h2>
		
			<fmt:message key="doctor.Client" bundle="${msgs}" />
			<br />
 			<c:out value="${userInRecipe.firstName}, ${userInRecipe.secondName}, ${userInRecipe.id}" />
 			
			<br />
			<br />
			
			<fmt:message key="doctor.Recipe.Validity" bundle="${msgs}" />
			<br />
			<c:out value = "${recipe.beginDate} - ${recipe.endDate}"/>
			
			<br />
			<br />
			<table>
			<tr><td><fmt:message key="doctor.Drug" bundle="${msgs}" /></td>
			<td></td>
			<td><fmt:message key="doctor.Quantity" bundle="${msgs}" /></td>
			
			<c:forEach items="${sessionScope.drugListInRecipe}" var="drug">
				<tr>
				<td><c:out value="${drug.drugName}, ${drug.dosage}" /></td>
				<td></td>
				<td>
				<c:out value="${drug.quantity}" /></td>
			</c:forEach>
			</tr>
			</table>
	
			<br />
			<br />
			<form method="post" action="controller.html">
 				<input type="hidden" name="action" value="prolongRecipeRequest" />
 				<input type="hidden" name="recipeId" value="${recipe.id}" />
 				<input type="submit" name="addRecipe_but" value="<fmt:message key="doctor.prolong.user" bundle="${msgs}" />" onclick="this.value='Скрипт сработал'"  />
 				<!-- <input type="button" value="Перейти в корзину" onClick='location.href="cart.jsp"'>-->
 			</form>
			
	</div>
	<br />
	<br />
	<br />
	<br />
	<%@ include file="footer.jsp" %>
</body>

</html>