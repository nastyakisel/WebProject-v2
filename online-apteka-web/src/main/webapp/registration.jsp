<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<%@ include file="header.jsp" %>
<div id="wrapper">

<body>
	<div class="registration">
		<form method="post" action="controller.html" >
		<c:if test="${sessionScope.has_errors != null}">
				<c:forEach items="${sessionScope.has_errors}" var="error">
						<span class="errorText">
							<fmt:message key="registrationPage.existUserError" bundle="${msgs}" />
						</span>
				</c:forEach>
			</c:if>
			<br />

			<input type="hidden" name="action" value="registration" />

			<fmt:message key="registration.userName.label" bundle="${msgs}" />
			<br />
			<input type="text" id="user_login" name="user_login" />

			<br />
			<br />

		</form>
		
	</div>
	<%@ include file="footer.jsp" %>
	 </div> <!-- конец  wrapper-->
  
</body>
</html>