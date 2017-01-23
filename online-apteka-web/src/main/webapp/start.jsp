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
  	<c:out value="${session_Id}" />
  	<c:forEach items="${drug_List}" var="drug">
    	<tr>
    		<td align="center"><img src="${drug.imagePath}" width="140" height="120"></td>
    		<td align="left">
   			<h3 align="left"><a href ="controller.html?action=goodDetails&goodId=${drug.id}"><c:out value="${drug.drugName}" /></a></h3><br> 
 			<c:out value="${drug.description}" />
    		<td align="center"><a href ="controller.html?action=goodDetails&goodId=${drug.id}"><fmt:message key="details" bundle="${msgs}" /></a></td>
  		</tr>
  	</c:forEach>
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