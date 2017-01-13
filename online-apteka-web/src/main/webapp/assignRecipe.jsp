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
		<h2>Создать новый рецепт</h2>
		Имя доктора:
   		<h2><c:out value="${doctorUser.userName}" /></h2>
   		Id рецепта:
		<h2><c:out value="${recipeId}" /></h2>
		<form method="post" action="controller.html" >
		
			<input type="hidden" name="action" value="createRecipe" />
			<input type="hidden" name="previousURI" value="${previousURI}" />
			Клиент
			<br />
			<c:if test="${selectedUser != null}">
 				<c:out value="${selectedUser.userName}" />
 			</c:if>
 			
 			<c:if test="${selectedUser == null}">
			<select name = "selectedUser">
 				<option disabled>Выберите клиента</option>
 				
 				<c:forEach items="${sessionScope.clientList}" var="user">
 				
				<option value="${user.id}"><c:out value="${user.userName}, ${user.id}" /></option>
				
			</c:forEach>
			</select>
			</c:if>
			<br />
			<br />
			Препарат
			<br />
			<select name = "selectedDrug">
 				<option disabled>Выберите препарат</option>
 	<c:forEach items="${sessionScope.drugListWhithRecipe}" var="drug">
 				
				<option value="${drug.id}"><c:out value="${drug.drugName}, ${drug.dosage}" /></option>
				
	</c:forEach>
	</select>
			<br />
			<br />
			Дозировка
			<br />
			<input type="text" id="dosage" name="dosage" size="52"/>
				
			<br />
			<br />
			Количество 
			<br />
			<input type="text" id="quantity" name="quantity" />
				
			<br />
			<br />
			
			<c:if test="${selectedUser == null}">
			<td>Действие рецепта с</td>
            <br />       
            <input type="date" id="begin_date" name="begin_date" min="2017-01-01" max="2018-01-01"/>
                            
            <br />
			<br />              
			
			<td>По</td>
            <br />       
            <input type="date" id="end_date" name="end_date" min="2017-01-01" max="2018-01-01"/>
                            
            <br />
			<br /> 
			</c:if>
			<input type="submit" name="add_but" value="Создать" />
			
		</form>
		
	</div>
	
	
</body>
</html>