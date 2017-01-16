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
					<c:if test="${error.errorMessage == 'loginPage.errorUser'}">
						<span class="errorText">
							<fmt:message key="loginPage.errorUser" bundle="${msgs}" />
						</span>
					</c:if>
				</c:forEach>
			</c:if>
			<br />

			<input type="hidden" name="action" value="login" />

			<fmt:message key="loginPage.userName.label" bundle="${msgs}" />
			<br />
			<input type="text" id="user_login" name="user_login" value="<c:out value='${sessionScope.current_user.userName}' default=''  />" />

			<br />
			<br />

			<fmt:message key="loginPage.password.label" bundle="${msgs}" />
			<br />
			<input type="text" id="user_password" name="user_password" />
				
				<c:if test="${sessionScope.has_errors != null}">
				<c:forEach items="${sessionScope.has_errors}" var="error">
					<c:if test="${error.errorMessage == 'loginPage.errorPassword'}">
						<span class="errorText">
							<fmt:message key="loginPage.errorPassword" bundle="${msgs}" />
						</span>
					</c:if>
				</c:forEach>
			</c:if>
			
			<br />
			<br />
			<input type="submit" name="login_but" value="<fmt:message key='loginPage.button' bundle='${msgs}' />" />
		</form>
		
	</div>
	
	
</body>
</html>