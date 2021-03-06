<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<%@ include file="headerAdm.jsp" %>
<div id="wrapper">
  
  <div id="main">
  
   	<div id="container">
			<div id="content">

  <c:out value="${session_Id}" />
					<br />
  <table width="98%" border="0" cellspacing="1">
  	<!--  <button><a href = "addGood.jsp" >Добавить</a></button>-->
  	<tr><td>
  	<form method="post" action="controller.html?action=add&byId=no" >
  		
  		<input type="submit" name="addGood_but" value="<fmt:message key="admin.Add" bundle="${msgs}" />" />
  	</form> 
  <br> </td>
  <td>
  <form method="post" action="controller.html?action=add&byId=yes" >
  		
  		<input type="submit" name="addGood_but" value="<fmt:message key="admin.AddById" bundle="${msgs}" />" />
  	</form> 
  <br></td></tr>
  
  	<c:forEach items="${drug_List}" var="drug">
    	<tr>
    		<td align="center"><img src="${drug.imagePath}" width="140" height="120"></td>
    		<td align="left">
   			<h3 align="left"><span style="color:#669900;"><c:out value="${drug.drugName}" /></span></h3><br> 
 			<c:out value="${drug.description}" />
    		<td align="center"><a href ="controller.html?action=edit&drugId=${drug.id}"><fmt:message key="admin.Update" bundle="${msgs}" /></a></td>
    		<td align="center"><a href ="controller.html?action=delete&drugId=${drug.id}"><fmt:message key="admin.Delete" bundle="${msgs}" /></a></td>
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
              		<li><a href="controller.html?action=getGoodsByCategoryAdm&catId=${category.id}"><c:out value="${category.categoryName}" /></a></li>
                </c:forEach>
            </ul>
	 <br>
       
     </div>
     <br>	  
     
  </div> 
     
  </div>
  </div> <!-- конец  wrapper-->
  
  </body>

</html>