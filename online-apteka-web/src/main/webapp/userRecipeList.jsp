<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<%@ include file="header.jsp" %>
	<div id="wrapper">
	<div id="content">

	<table width="98%" border="0" cellspacing="1">
   	<h2><fmt:message key="doctor.Recipes" bundle="${msgs}" /></h2>
   	
  	<tr>
  	
 	</table>
 	
 	<br />
 	<br />
 	
    <table width="98%" border="0" cellspacing="1">
   	<c:forEach items="${sessionScope.userRecipeList}" var="recipe">
     	<tr>
     		<td align="left">
    			<h3 align="left"><a href ="controller.html?action=recipeDetails&recipeId=${recipe.id}"><span style="color:#669900;"><c:out value="${recipe.id}" /></span></a></h3><br> 
			</td>
			<td><c:out value = "${recipe.userId}"/></td>
			<td><c:out value = "${recipe.beginDate} - ${recipe.endDate}"/></td>
			<td>
   		</tr>
   		</c:forEach>
   	</table>
		
	</div>
	</div>
	
</body>
</html>