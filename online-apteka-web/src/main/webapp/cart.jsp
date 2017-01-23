<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
 <!DOCTYPE html>
<%@ include file="header.jsp" %>
<script type="text/javascript" src="js/registration.js"></script>
   <div id="wrapper">
   <div id="main">
   
    	<div id="container">
 			<div id="content">
 			
   <c:if test="${not empty shoppingCart }">    
   <table width="98%" border="0" cellspacing="1">
   	
   	<form method="post" action="controller.html" >
   	<c:set var="sumTotal" value="${0}" />
   	<c:forEach items="${sessionScope.shoppingCart}" var="drug">
     		
     	<tr>
     		<input type="hidden" name="action" value="checkCart" />
     		<td><input type="checkbox" name="checkbox" value = "${drug.id}"/></td>
     		<td align="center"><img src="${drug.imagePath}" width="140" height="120"></td>
     		<td align="left">
     		
     		<c:if test="${sessionScope.has_errors != null}">
     			<c:forEach items="${sessionScope.has_errors}" var="error">
     				<c:if test="${error.errorMessage == 'incorrect quantity'}">
    					<c:if test="${sessionScope.wrongId == drug.id}">  
     						<b><fmt:message key="cartPage.quantity.Allert" bundle="${msgs}" /></b>
     				</c:if>
     				</c:if>
     				
     				<c:if test="${error.errorMessage == 'no recipe'}">
    					<c:if test="${sessionScope.wrongId == drug.id}">  
     						<b><fmt:message key="cartPage.recipe.Allert" bundle="${msgs}" /></b>
     				</c:if>
     				</c:if>
     				</c:forEach>
     				</c:if>
     				
    			<h3 align="left"><a href ="controller.html?action=goodDetails&goodId=${drug.id}"><span style="color:#669900;"><c:out value="${drug.drugName}" /></span></a></h3><br> 
  			<font size="3px"><c:out value="${drug.description}" />
  			<input type="hidden" name="drugId" value="${drug.id}" />
  			<td><input type="text" name ="quantity" value = "${drug.quantity}"/></td>
  			<td><c:out value="${drug.price * drug.quantity}" /></td>
   		</tr>
   		<c:set var="sumTotal" value="${sumTotal + drug.price * drug.quantity}" />
   		
   		</c:forEach>
   		<tr> <td></td><td></td>
   		<td><h2><fmt:message key="cartPage.total" bundle="${msgs}" /></h2></td>
   		<td></td>
   		<td><h2><c:out value="${sumTotal}" /></h2>
   		</td>
   		</tr>	
  	<tr><input type="submit" name="count" value="<fmt:message key="cartPage.count" bundle="${msgs}" />" /></tr>
  	<tr><input type="submit" name="delete" value="<fmt:message key="cartPage.delete" bundle="${msgs}" />" /></tr>
  	<tr>
  	<c:if test="${sessionScope.userId == null}">	
              		
  	<input type="submit" name="buy" onclick = "alert('Сначала надо войти в систему!')" value="<fmt:message key="cartPage.buy" bundle="${msgs}" />" />
  	</c:if>
  	
  	<c:if test="${sessionScope.userId != null}">	
              		
  	<input type="submit" name="buy" value="<fmt:message key="cartPage.buy" bundle="${msgs}" />" />
  	</c:if>
  	
  	</tr>
   	</table>
   	</c:if>
    <c:if test="${empty shoppingCart}">
    <h2><fmt:message key="cartPage.emptyCart" bundle="${msgs}" /></h2>
    </c:if>
   
    </div>
  </div>
      <br>	  
   <br />
   </div> <!-- конец  wrapper-->
  <%@ include file="footer.jsp" %>
   </body>
   </html>