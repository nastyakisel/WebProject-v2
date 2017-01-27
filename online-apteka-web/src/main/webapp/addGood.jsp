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
			<c:forEach items="${sessionScope.has_errors}" var="error">
			<c:if test="${error.errorMessage == 'emptyField'}">
						<c:set var="errorEmpty" value="addGood.emptyField" />
						
					</c:if>
					</c:forEach>
			</c:if>
			<c:if test="${errorEmpty == 'emptyField'}">
			<div class="errorText">
							<fmt:message key="addGood.errorMessage" bundle="${msgs}" />
						</div>
						</c:if>
			<br />
			
			<input type="hidden" name="action" value="addGood" />
			<input type="hidden" name="previousURI" value="${previousURI}" />

			<fmt:message key="admin.Category" bundle="${msgs}" />
			<br />
			<input type="text" id="cat_id" name="cat_id" value="<c:out value='${sessionScope.categotyId}' default=''  />" />

			<br />
			<br />
			
			<c:if test="${addById == 'yes'}">
				<fmt:message key="admin.drug.id" bundle="${msgs}" />
				<br />
				<input type="text" id="drug_id" name="drug_id" value="<c:out value='${sessionScope.drug_id}' default=''  />" />

				<br />
				<br />
			</c:if>
			
			<fmt:message key="admin.Name" bundle="${msgs}" />
			<br />
			<input type="text" id="good_name" name="good_name" value="<c:out value='${sessionScope.goodName}' default=''  />" size="52" />

			<br />
			<br />

			<fmt:message key="admin.Description" bundle="${msgs}" />
			<br />
			<textarea name="good_descr" cols="40" rows="3"><c:out value='${sessionScope.description}' default=''  /></textarea>
			<br />
			<br />
			
			
			<fmt:message key="admin.Dosage" bundle="${msgs}" />
			<br />
			<input type="text" id="dosage" name="dosage" value="<c:out value='${goodDosage}' default=''  />" size="52"/>
				
			<br />
			<br />
			
			<fmt:message key="admin.Instruction" bundle="${msgs}" />
			<br />
			<textarea name="instruction" cols="60" rows="7"><c:out value='${sessionScope.instruction}' default=''  /></textarea>
				
			<br />
			<br />
			
			<c:if test="${has_errors != null}">
				<c:forEach items="${sessionScope.has_errors}" var="error">
				<c:if test="${error.errorMessage == 'goodPrice.not.number'}">
						<div class="errorText">
							<fmt:message key="goodPrice.not.number" bundle="${msgs}" />
						</div>
					</c:if>
					</c:forEach>
			</c:if>
			<fmt:message key="admin.Price" bundle="${msgs}" />
			<br />
			<input type="text" id="price" name="price" value="<c:out value='${sessionScope.goodPrice}' default=''  />" />
				
			<br />
			<br />
			
			<c:if test="${has_errors != null}">
				<c:forEach items="${sessionScope.has_errors}" var="error">
				<c:if test="${error.errorMessage == 'goodQuantity.not.number'}">
						<div class="errorText">
							<fmt:message key="goodQuantity.not.number" bundle="${msgs}" />
						</div>
					</c:if>
					</c:forEach>
			</c:if>
			<fmt:message key="admin.Quantity" bundle="${msgs}" /> 
			<br />
			<input type="text" id="quantity" name="quantity" value="<c:out value='${sessionScope.goodQuantity}' default=''  />" />
				
			<br />
			<br />
			
			<fmt:message key="admin.Recipe" bundle="${msgs}" /> 
			<br />
			
			<select name="recipe">
  				<option>0</option>
  				<option>1</option>
				</select>
				
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