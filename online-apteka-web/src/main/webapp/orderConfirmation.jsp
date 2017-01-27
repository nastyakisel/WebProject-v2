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
   
    <h2><fmt:message key="invoice.OrderConfirm" bundle="${msgs}" /></h2>
   <fmt:message key="invoice.Client.Name" bundle="${msgs}" />
   	<h2><c:out value="${user.firstName}, ${user.secondName}" /></h2>
   	<h2><fmt:message key="invoice.OrderNumber" bundle="${msgs}" /></h2>
   	<h2><c:out value="${customOrderId}" /></h2>
   	<c:forEach items="${sessionScope.drugsInOrder}" var="drug">
     	<tr>
     		<td align="center"><img src="${drug.imagePath}" width="140" height="120"></td>
     		<td align="left">
    			<h3 align="left"><a href ="controller.html?action=goodDetails&goodId=${drug.id}"><span style="color:#669900;"><c:out value="${drug.drugName}" /></span></a></h3><br> 
  			<c:out value="${drug.description}" />
 
  			<td><c:out value = "${drug.quantity}"/></td>
  			<td><c:out value="${drug.price}" /></td>
   		</tr>
   		
   		</c:forEach>
   		<tr> <td></td><td></td>
   		<td><h2><fmt:message key="invoice.Total" bundle="${msgs}" /></h2></td>
   		<td></td>
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
    <%@ include file="footer.jsp" %>
   </div> <!-- конец  wrapper-->
   
   </body>
   </html>