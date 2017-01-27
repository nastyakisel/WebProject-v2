<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
 <!DOCTYPE html>
 <%@ include file="header.jsp" %>
   <div id="wrapper">
   
   <div id="main">
   
    	<div id="container">
 			<div id="content">
 			      
   <table width="98%" border="0" cellspacing="1">
   	<h2><fmt:message key="invoice.Order" bundle="${msgs}" /></h2>
   <br />
    <fmt:message key="invoice.Client" bundle="${msgs}" />
   	
   	<fmt:message key="invoice.Client.Name" bundle="${msgs}" />
   	<h2><c:out value="${user.firstName}, ${user.secondName}" /></h2>
   	<form method="post" action="controller.html" >
   	<c:set var="sumTotal" value="${0}" />
   	<c:forEach items="${sessionScope.shoppingCart}" var="drug">
     	<tr>
     		<input type="hidden" name="action" value="makeOrder" />
     		<td align="center"><img src="${drug.imagePath}" width="140" height="120"></td>
     		<td align="left">
     		<c:if test="${sessionScope.has_errors != null}">
    			<c:if test="${sessionScope.wrongId == drug.id}">
     		<b><fmt:message key="cartPage.quantity.Allert" bundle="${msgs}" /></b>
     		</c:if>
     		</c:if>
    			<h3 align="left"><a href ="controller.html?action=goodDetails&goodId=${drug.id}"><span style="color:#669900;"><c:out value="${drug.drugName}" /></span></a></h3><br> 
  			<font size="3px"><c:out value="${drug.description}" />
  			<input type="hidden" name="drugId" value="${drug.id}" />
  			<td><c:out value = "${drug.quantity}"/></td>
  			<td><c:out value="${drug.price * drug.quantity}" /></td>
   		</tr>
   		<c:set var="sumTotal" value="${sumTotal + drug.price * drug.quantity}" />
   		</c:forEach>
   		<tr> <td></td><td></td>
   		<td><h2><fmt:message key="invoice.Total" bundle="${msgs}" /></h2></td>
   		<td></td>
   		<input type="hidden" name="sumTotal" value="${sumTotal}" />
   		<td><h2><c:out value="${sumTotal}" /></h2>
   		</td>
   		</tr>	
  	<tr><input type="submit" name="order" value="<fmt:message key="invoice.MakeOrder" bundle="${msgs}" />" /></tr>
   	</table>
   	</form>
   
 <br>
 <br>
    </div>
  </div>
      <br>	   
   </div> 
      
   </div>
   <%@ include file="footer.jsp" %>
   </div> <!-- конец  wrapper-->
   
   </body>
   </html>