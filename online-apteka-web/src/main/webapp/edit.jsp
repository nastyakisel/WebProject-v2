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
		
			<input type="hidden" name="action" value="editGood" />
			<input type="hidden" name="previousURI" value="${previousURI}" />

			Id
			<br />
			<input type="text" id="id" name="id" value="<c:out value='${drug.id}' default=''  />" disabled/>
			<input type="hidden" name="good_id" value="${drug.id}" />
			<br />
			<br />
			Категория
			<br />
			<input type="text" id="cat_id" name="cat_id" value="<c:out value='${drug.categoryId}' default=''  />" />

			<br />
			<br />
			
			Наименование
			<br />
			<input type="text" id="good_name" name="good_name" value="<c:out value='${drug.drugName}' default=''  />" size="52" />

			<br />
			<br />

			Описание
			<br />
			<textarea name="good_descr" cols="40" rows="3"><c:out value='${drug.description}' default=''  /> 
			</textarea>
			<br />
			<br />
			
			Дозировка
			<br />
			<input type="text" id="dosage" name="dosage" value="<c:out value='${drug.dosage}' default=''  />" size="52"/>
				
			<br />
			<br />
			
			Инструкция
			<br />
			<textarea name="instruction" cols="60" rows="7"><c:out value='${drug.instruction}' default=''  /></textarea>
				
			<br />
			<br />
			
			Цена
			<br />
			<input type="text" id="price" name="price" value="<c:out value='${drug.price}' default=''  />"/>
				
			<br />
			<br />
			
			Количество 
			<br />
			<input type="text" id="quantity" name="quantity" value="<c:out value='${drug.quantity}' default=''  />"/>
				
			<br />
			<br />
			
			Рецепт 
			<br />
			<input type="text" id="recipe" name="recipe" value="<c:out value='${drug.needRecipe}' default=''  />"/>
				
			<br />
			<br />
			
			Картинка 
			<br />
			<input type="text" id="image" name="image" value="<c:out value='${drug.imagePath}' default=''  />"/>
				
			<br />
			<br />
			
			<input type="submit" name="add_but" value="Изменить" />
		</form>
		
	</div>
	
	
</body>
</html>