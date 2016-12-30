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
						</c:when>
				<c:when test="${sessionScope.userId == null}">	
              		<a href = "javascript:void(0)" onclick = "document.getElementById('envelope').style.display='block';document.getElementById('fade').style.display='block'"><fmt:message key="startPage.registration" bundle="${msgs}" /></a>
              		<a href = "login.jsp"><fmt:message key="startPage.login" bundle="${msgs}" /></a>
              		</c:when>
              		</c:choose>
              	<a href = "controller.html?action=getFromCart"><fmt:message key="startPage.shoppingCart" bundle="${msgs}" /></a>
            </div>
  		</div>
  		
  		<div id="logoName"><img src="css/img/apteka.jpg" width="120" height="120"></img></div>
 
  </div> <!-- конец header -->
  
  <div id="main">
  
   	<div id="container">
			<div id="content">
			
           
  <table width="98%" border="0" cellspacing="1">
  	
    	<tr>
    		<td align="center"><img src="${drug.imagePath}" width="140" height="120"></td>
    		<td align="left">
   			<h3 align="left"><span style="color:#669900;"><c:out value="${drug.drugName}" /></span></h3><br> 
 			<font size="3px"><c:out value="${drug.description}" />
 			<td align="center"><h3><span style="color:#669900;">Цена, руб.</span></h3><br>
 			<c:out value="${drug.price}" />
 			</td>
  		</tr>
  		<tr>
    		<td align="center"></td>
    		<td align="left">
 			<font size="3px"><c:out value="${drug.dosage}" /><br>
 			<font size="3px"><c:out value="${drug.instruction}" /></font></font></td>
 			<td>
 			<form method="post" action="controller.html">
 				<input type="hidden" name="action" value="addGoodtoCart" />
 				<input type="hidden" name="drugId" value="${drug.id}" />
 				<input type="submit" name="addCart_but" value="В корзину" onclick="this.value='Скрипт сработал'"  />
 				<!-- <input type="button" value="Перейти в корзину" onClick='location.href="cart.jsp"'>-->
 				<a href = "controller.html?action=getFromCart">Перейти в корзину</a>
 			</form>
 			 </td>
  		</tr>
	</table>
<br>
<br>
   </div>
 </div>
 
 
 <div class="leftCol" id="leftCol" align="center">
        <br>
        <h2 align="center"><span style="color:#669900;"><fmt:message key="goodsList.categoty.header" bundle="${msgs}" /></span></h2>
   
    <div id="menuLeft">
            <ul>
            	<c:forEach items="${category_List}" var="category">
              		<li><a href="controller.html?action=getGoodsByCategory&catId=${category.id}"><c:out value="${category.categoryName}" /></a></li>
                </c:forEach>
            </ul>
	 <br>
       
     </div>
     <br>	  
     
  </div> 
     
  </div>
  </div> <!-- конец  wrapper-->
  <div id="footer">
	    <p align="center"><a href="#">О компании</a></p >
        <p align="center"><a href="#">Контакты</a></p >
        
 </div>
  </body>
  </html>