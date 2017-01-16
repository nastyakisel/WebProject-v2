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
  	
    	<tr>
    		<td align="center"><img src="${drug.imagePath}" width="140" height="120"></td>
    		<td align="left">
   			<h3 align="left"><span style="color:#669900;"><c:out value="${drug.drugName}" /></span></h3><br> 
 			<font size="3px"><c:out value="${drug.description}" />
 			<td align="center"><h3><span style="color:#669900;"><fmt:message key="price" bundle="${msgs}" /></span></h3><br>
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
 				<input type="submit" name="addCart_but" value="<fmt:message key="cart.add" bundle="${msgs}" />" onclick="this.value='Скрипт сработал'"  />
 				<!-- <input type="button" value="Перейти в корзину" onClick='location.href="cart.jsp"'>->
 				<a href = "controller.html?action=getFromCart"><fmt:message key="cart.go" bundle="${msgs}" /></a>-->
 				<c:if test="${sessionScope.has_errors != null}">
 					<c:if test="${sessionScope.has_errors == drug.id}">
 					<span class="errorText">
							<fmt:message key="cartPage.drugExists" bundle="${msgs}" />
						</span>
 					</c:if>
 					
 				</c:if>
 				

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
  <%@ include file="footer.jsp" %>
  </div> <!-- конец  wrapper-->
 
  </body>
  </html>