<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<%@ include file="headerAdm.jsp" %>
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
 			<b><fmt:message key="admin.Dosage" bundle="${msgs}" />:</b>
 			<font size="3px"><c:out value="${drug.dosage}" /><br>
 			<font size="3px"><c:out value="${drug.instruction}" /></font></font></td>
 			<td>
 			<align="center"><a href ="controller.html?action=edit&drugId=${drug.id}"><fmt:message key="admin.Update" bundle="${msgs}" /></a>
 			<td align="center"><a href ="controller.html?action=delete&drugId=${drug.id}"><fmt:message key="admin.Delete" bundle="${msgs}" /></a></td>
 			 </td>
  		</tr><td align="center"></td>
  		<tr><td align="center"></td>
  		<td><b><fmt:message key="admin.Recipe" bundle="${msgs}" />:</b><br> 
  		<c:if test="${drug.needRecipe == 0}">  
  		<fmt:message key="admin.noResipe" bundle="${msgs}" />
  		</c:if>
  		<c:if test="${drug.needRecipe == 1}">
  		<fmt:message key="admin.isResipe" bundle="${msgs}" />
  		</c:if>
  		</td>
  		</tr>
  		<tr><td align="center"></td>
  		<td><b><fmt:message key="admin.Rest" bundle="${msgs}" /></b><br>
  		<c:out value="${drug.quantity}" />
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
              		<li><a href="controller.html?action=getGoodsByCategoryAdm&catId=${category.id}"><c:out value="${category.categoryName}" /></a></li>
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