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
		<form method="post" action="controller.html" >
		
			<input type="hidden" name="action" value="addGood" />
			<input type="hidden" name="previousURI" value="${previousURI}" />

			Категория
			<br />
			<input type="text" id="cat_id" name="cat_id" value="<c:out value='${sessionScope.categotyId}' default=''  />" />

			<br />
			<br />
			
			Наименование
			<br />
			<input type="text" id="good_name" name="good_name" size="52" />

			<br />
			<br />

			Описание
			<br />
			<textarea name="good_descr" cols="40" rows="3"></textarea>
			<br />
			<br />
			
			Дозировка
			<br />
			<input type="text" id="dosage" name="dosage" size="52"/>
				
			<br />
			<br />
			
			Инструкция
			<br />
			<textarea name="instruction" cols="60" rows="7"></textarea>
				
			<br />
			<br />
			
			Цена
			<br />
			<input type="text" id="price" name="price" />
				
			<br />
			<br />
			
			Количество 
			<br />
			<input type="text" id="quantity" name="quantity" />
				
			<br />
			<br />
			
			Рецепт 
			<br />
			<input type="text" id="recipe" name="recipe" />
				
			<br />
			<br />
			
			Картинка 
			<br />
			<input type="text" id="image" name="image" />
				
			<br />
			<br />
			
			<input type="submit" name="add_but" value="Добавить" />
		</form>
		
	</div>
	
	
</body>
</html>