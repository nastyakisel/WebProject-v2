<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<body>
<%@ include file="headerAdm.jsp" %>
<div id="wrapper">
	<div class="registration">
		<form method="post" action="controller.html" >
		<c:if test="${has_errors != null}">
						<span class="errorText">
							<fmt:message key="addGood.errorMessage" bundle="${msgs}" />
						</span>
			</c:if>
			<br />
			
			<input type="hidden" name="action" value="addGood" />
			<input type="hidden" name="previousURI" value="${previousURI}" />

			<fmt:message key="admin.Category" bundle="${msgs}" />
			<br />
			<input type="text" id="cat_id" name="cat_id" value="<c:out value='${sessionScope.categotyId}' default=''  />" />

			<br />
			<br />
			
			<fmt:message key="admin.Name" bundle="${msgs}" />
			<br />
			<input type="text" id="good_name" name="good_name" value="<c:out value='${sessionScope.goodName}' default=''  />" size="52" />

			<br />
			<br />

			<fmt:message key="admin.Description" bundle="${msgs}" />
			<br />
			<textarea name="good_descr" cols="40" value="<c:out value='${sessionScope.description}' default=''  />" rows="3"></textarea>
			<br />
			<br />
			
			<fmt:message key="admin.Dosage" bundle="${msgs}" />
			<br />
			<input type="text" id="dosage" name="dosage" value="<c:out value='${goodDosage}' default=''  />" size="52"/>
				
			<br />
			<br />
			
			<fmt:message key="admin.Instruction" bundle="${msgs}" />
			<br />
			<textarea name="instruction" value="<c:out value='${sessionScope.instruction}' default=''  />" cols="60" rows="7"></textarea>
				
			<br />
			<br />
			
			<fmt:message key="admin.Price" bundle="${msgs}" />
			<br />
			<input type="text" id="price" name="price" value="<c:out value='${sessionScope.goodPrice}' default=''  />" />
				
			<br />
			<br />
			
			<fmt:message key="admin.Quantity" bundle="${msgs}" /> 
			<br />
			<input type="text" id="quantity" name="quantity" value="<c:out value='${sessionScope.goodQuantity}' default=''  />" />
				
			<br />
			<br />
			
			<fmt:message key="admin.Recipe" bundle="${msgs}" /> 
			<br />
			<input type="text" id="recipe" name="recipe" value="<c:out value='${sessionScope.goodRecipe}' default=''  />" />
				
			<br />
			<br />
			
			<fmt:message key="admin.Image" bundle="${msgs}" /> 
			<br />
			<input type="text" id="image" name="image" value="<c:out value='${sessionScope.imagePath}' default=''  />" />
				
			<br />
			<br />
			
			<input type="submit" name="add_but" value="<fmt:message key="admin.Add" bundle="${msgs}" />" /> 
		</form>
		
	</div>
	
</body>
</html>