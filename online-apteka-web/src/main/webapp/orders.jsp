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
   	<h2><fmt:message key="orders" bundle="${msgs}" /></h2>
   	<h2><c:out value="${session_Id}" /></h2>
 
   	<fmt:message key="order.clientName" bundle="${msgs}" />
   	<h2><c:out value="${user.firstName}, ${user.secondName}" /></h2>
  
   	<c:forEach items="${sessionScope.customOrderList}" var="customOrder">
     	<tr>
     		<td align="left">
    			<h3 align="left"><a href ="controller.html?action=orderDetails&orderId=${customOrder.id}"><span style="color:#669900;"><c:out value="${customOrder.id}" /></span></a></h3><br> 
			</td>
			<td><c:out value = "${customOrder.totalPrice}"/></td>
			<td><c:out value = "${customOrder.orderDate}"/></td>
			<c:if test="${customOrder.orderStatus == 0}">
				<td><fmt:message key="order.active" bundle="${msgs}" /></td>
			</c:if>
			<c:if test="${customOrder.orderStatus == 1}">
				<td><fmt:message key="order.fulfilled" bundle="${msgs}" /></td>
			</c:if>
			
   		</tr>
   		
   		</c:forEach>
  	
   	</table>
   	
 <br>
 <br>
    </div>
  </div>
  
      <br>	      
   </div> 
      <%@ include file="footer.jsp" %>
   </div>
   
   </div> <!-- конец  wrapper-->
  
   </body>
   </html>