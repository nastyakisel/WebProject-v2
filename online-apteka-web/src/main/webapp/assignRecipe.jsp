<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<%@ include file="headerPharm.jsp" %>
	<div id="wrapper">

<body>
	<div class="registration">
		<h2><fmt:message key="doctor.NewRecipe" bundle="${msgs}" /></h2>
		<br />
		<fmt:message key="doctor.Name" bundle="${msgs}" />
   		<h2><c:out value="${doctorUser.userName}" /></h2>
   		<br />
   		<fmt:message key="doctor.Recipe.Id" bundle="${msgs}" />
		<h2><c:out value="${recipeId}" /></h2>
		<br />
		<form method="post" action="controller.html" >
		
			<input type="hidden" name="action" value="createRecipe" />
			<input type="hidden" name="previousURI" value="${previousURI}" />
			<fmt:message key="doctor.Client" bundle="${msgs}" />
			<br />
			<c:if test="${selectedUser != null}">
 				<c:out value="${selectedUser.userName}" />
 			</c:if>
 			
 			<c:if test="${selectedUser == null}">
			<select name = "selectedUser">
 				<option disabled><fmt:message key="doctor.Choose.Client" bundle="${msgs}" /></option>
 				
 				<c:forEach items="${sessionScope.clientList}" var="user">
 				
				<option value="${user.id}"><c:out value="${user.userName}, ${user.id}" /></option>
				
			</c:forEach>
			</select>
			</c:if>
			<br />
			<br />
			<fmt:message key="doctor.Drug" bundle="${msgs}" />
			<br />
			<select name = "selectedDrug">
 				<option disabled><fmt:message key="doctor.Choose.Drug" bundle="${msgs}" /></option>
 	<c:forEach items="${sessionScope.drugListWhithRecipe}" var="drug">
 				
				<option value="${drug.id}"><c:out value="${drug.drugName}, ${drug.dosage}" /></option>
				
	</c:forEach>
	</select>
			<br />
			<br />
			<fmt:message key="doctor.Dosage" bundle="${msgs}" />
			<br />
			<input type="text" id="dosage" name="dosage" size="52"/>
				
			<br />
			<br />
			<fmt:message key="doctor.Quantity" bundle="${msgs}" /> 
			<br />
			<input type="text" id="quantity" name="quantity" />
				
			<br />
			<br />
			
			<c:if test="${selectedUser == null}">
			<td><fmt:message key="doctor.Recipe.From" bundle="${msgs}" /> </td>
            <br />       
            <input type="date" id="begin_date" name="begin_date" min="2017-01-01" max="2018-01-01"/>
                            
            <br />
			<br />              
			
			<td><fmt:message key="doctor.Till" bundle="${msgs}" /></td>
            <br />       
            <input type="date" id="end_date" name="end_date" min="2017-01-01" max="2018-01-01"/>
                            
            <br />
			<br /> 
			</c:if>
			<input type="submit" name="add_but" value="<fmt:message key="doctor.Create" bundle="${msgs}" />" />
			
		</form>
		
	</div>
	
</body>
</html>