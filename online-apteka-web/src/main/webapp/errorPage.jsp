<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<%@ include file="header.jsp" %>
<div id="wrapper">
  <div id="main">
  
   	<div id="container">
			<div id="content">
			
           
   </div>
 </div>
 <h2>Error occurred</h2>
 <c:out value="${errorMessage}" />
 
 
 <br /> 
 <br />
 <br />
 <br />
  </div> 
     <%@ include file="footer.jsp" %>
  </div>
  
  </div> <!-- конец  wrapper-->
  
  </body>
  </html>