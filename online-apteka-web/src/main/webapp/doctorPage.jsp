<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<%@ include file="headerPharm.jsp" %>
	<div id="wrapper">
	<div id="content">

	<table width="98%" border="0" cellspacing="1">
   	<h2><fmt:message key="doctor.Recipes" bundle="${msgs}" /></h2>
   	
   	<fmt:message key="doctor.Name" bundle="${msgs}" />
   	<h2><c:out value="${doctorUser.userName}" /></h2>
  	<tr>
  	<form method="post" action="controller.html">
 				<input type="hidden" name="action" value="assignRecipe" />
 				<td><input type="submit" name="addRecipe_but" value="<fmt:message key="doctor.assignRecipe" bundle="${msgs}" />" onclick="this.value='Скрипт сработал'"  /></td>
 				<!-- <input type="button" value="Перейти в корзину" onClick='location.href="cart.jsp"'>-->
 				
 			</form>
 	</table>
 	
 	<br />
 	<br />
 	
    <table width="98%" border="0" cellspacing="1">
   	<c:forEach items="${sessionScope.recipeList}" var="recipe">
     	<tr>
     		<td align="left">
    			<h3 align="left"><a href ="controller.html?action=recipeDetails&recipeId=${recipe.id}"><span style="color:#669900;"><c:out value="${recipe.id}" /></span></a></h3><br> 
			</td>
			<td><c:out value = "${recipe.userId}"/></td>
			<td><c:out value = "${recipe.beginDate} - ${recipe.endDate}"/></td>
			<td>
			<form method="post" action="controller.html">
 				<input type="hidden" name="action" value="recipeDetails" />
 				<input type="hidden" name="recipeId" value="${recipe.id}" />
 				<input type="submit" name="addRecipe_but" value="<fmt:message key="doctor.Prolong" bundle="${msgs}" />" onclick="this.value='Скрипт сработал'"  />
 				<!-- <input type="button" value="Перейти в корзину" onClick='location.href="cart.jsp"'>-->
 			</form> </td>	
   		</tr>
   		</c:forEach>
   	</table>
		
	</div>
	</div>
	
</body>
</html>