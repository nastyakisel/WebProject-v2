<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
 <!DOCTYPE html>
 <fmt:setLocale value="${sessionScope.requestLocale}"/>
 <fmt:setBundle basename="com.finalproject.onlineapteka.messages.msgs" var="msgs" />
 
 <head>
 	<meta charset="utf-8">
 
 	<link rel="stylesheet" href="css/style2.css" type="text/css">
 
 	<title>
 		<fmt:message key="application.title" bundle="${msgs}" />
 	</title>
 </head>
 
 <body>
  <div id="wrapper">
    <div id="header">
    	<div class="name">
    		<a href = "controller.html?action=ru"><fmt:message key="startPage.ruLocale" bundle="${msgs}" /></a>
 		<a href = "controller.html?action=en"><fmt:message key="startPage.enLocale" bundle="${msgs}" /></a>
 		</div>
    	<div id="menuWrapper">
    	
            <div id="menuHeader">
               <a href = "index.jsp"><fmt:message key="startPage.main" bundle="${msgs}" /></a>
               <c:choose>
               	<c:when test="${sessionScope.userId != null}">
 					<a href = "controller.html?action=logout" type="submit"><fmt:message key="loginOut.Out" bundle="${msgs}" /></a>	
 					<a href = "controller.html?action=getOrders" type="submit">Заказы</a>
 						</c:when>
 				<c:when test="${sessionScope.userId == null}">	
               		<a href = "javascript:void(0)" onclick = "document.getElementById('envelope').style.display='block';document.getElementById('fade').style.display='block'"><fmt:message key="startPage.registration" bundle="${msgs}" /></a>
               		<a href = "login.jsp"><fmt:message key="startPage.login" bundle="${msgs}" /></a>
               		</c:when>
               		</c:choose>
               	<a href = "cart.jsp"><fmt:message key="startPage.shoppingCart" bundle="${msgs}" /></a>
             </div>
   		</div>
   		
   		<div id="logoName"><img src="css/img/apteka.jpg" width="120" height="120"></img></div>
 
 	<div id="fade" class="black-overlay"></div>
 		
 		<div id="envelope" class="envelope">
 		<a class="close-btn" title="Закрыть" href="javascript:void(0)" onclick = "document.getElementById('envelope').style.display='none';document.getElementById('fade').style.display='none'"></a>
 		
 			<form method='post' class='registration' onsubmit='return validate(this);'>
 				<div class='input_form'>
 					<label>Name: </label>
     				<input type='text' id='first_name' name='first_name'>
   					</div>
 				<div class='input_form'>
 					<label>Surname: </label>
     				<input type='text' id='second_name' name='second_name'>
   					</div>
   				<div class='input_form'>
     				<label>Password: </label>
     				<input type='text' id='password' name='password'>
 					</div>
 				<div class='input_form'>
     				<label>Repeat Password: </label>
     				<input type='text' id='repeat_password' name='repeat_password'>
 					</div>
 				<div class='input_form'>
 					<label>Email: </label>
 					<input type='text' id='email' name='email'>
 					</div>
 				<div class="input_form">
 					<br>
 					<input type="button" onclick="validate(this.form)" value="Register">
 					</div>
 		</form>	
 		</div>
   </div> <!-- конец header -->
   
   
   <div id="main">
   
    	<div id="container">
 			<div id="content">
 			
            
   <table width="98%" border="0" cellspacing="1">
   	<h2>Заказ</h2>
   	<h2><c:out value="${session_Id}" /></h2>
    <h2>Ваш заказ оформлен</h2>
   	Имя:
   	<h2><c:out value="${user.userName}" /></h2>
   	<h2>Номер заказа</h2>
   	<h2><c:out value="${customOrderId}" /></h2>
   	<c:forEach items="${sessionScope.drugsInOrder}" var="drug">
     	<tr>
     		<td align="center"><img src="${drug.imagePath}" width="140" height="120"></td>
     		<td align="left">
    			<h3 align="left"><a href ="controller.html?action=goodDetails&goodId=${drug.id}"><span style="color:#669900;"><c:out value="${drug.drugName}" /></span></a></h3><br> 
  			<font size="3px"><c:out value="${drug.description}" />
 
  			<td><c:out value = "${drug.quantity}"/></td>
  			<td><c:out value="${drug.price}" /></td>
   		</tr>
   		
   		</c:forEach>
   		<tr> <td></td><td></td>
   		<td><h2>Итого</h2></td>
   		<td></td><
   		<td><h2><c:out value="${totalPrice}" /></h2>
   		</td>
   		</tr>	
  	
   	</table>
   	
   
 <br>
 <br>
    </div>
  </div>
  
  
      <br>	  
      
   </div> 
      
   </div>
   </div> <!-- конец  wrapper-->
   <div id="footer">
 	    <p align="center"><a href="#">О компании</a></p >
         <p align="center"><a href="#">Контакты</a></p >
         
  </div>
  
  
 	<script>
     function sendError(mainElem, errorMessage) {
       mainElem.className = 'error';
       var errorMsgs = document.createElement('span');
       errorMsgs.className = "error-message";
       errorMsgs.innerHTML = errorMessage;
       mainElem.appendChild(errorMsgs);
     }
 
     function deleteError(mainElem) {
     	mainElem.className = '';
       if (mainElem.lastChild.className == "error-message") {
     	  mainElem.removeChild(mainElem.lastChild);
       }
     }
 
     function validate(form) {
       var elems = form.elements;
       
       var first_name = elems.first_name.value;
       var second_name = elems.second_name.value;
       var password = elems.password.value;
       var repeat_password = elems.repeat_password.value;
       var email = elems.email.value;
       
       
       deleteError(elems.first_name.parentNode);
       if (!first_name) {
     	  sendError(elems.first_name.parentNode, ' *Поле обязательно для заполнения.');
       }
       else if (!/[A-Za-z0-9_]/.test(first_name) && first_name < 5) {
     	  sendError(elems.first_name.parentNode, ' *Поле должно содержать только латинские символы.');
       }
 
       deleteError(elems.second_name.parentNode);
       if (!second_name) {
     	  sendError(elems.second_name.parentNode, ' *Поле обязательно для заполнения.');
       }
       
       deleteError(elems.password.parentNode);
       if (!password) {
     	  sendError(elems.password.parentNode, ' *Введите пароль.');
      
        
       } else if (password != repeat_password) {
     	  sendError(elems.password.parentNode, ' *Пароли не совпадают!');
       }
       
       
       deleteError(elems.email.parentNode);
       if (!email) {
     	  sendError(elems.email.parentNode, ' *Поле обязательно для заполнения.');
       }
       else if (!/[@]/.test(email) || !/[.]/.test(email)) {
     	  sendError(elems.email.parentNode, ' *Поле заполнено некорректно.');
       }
 
     }
   </script>
   </body>
   </html>