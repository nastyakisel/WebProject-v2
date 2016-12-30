<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.requestLocale}"/>
<fmt:setBundle basename="com.finalproject.onlineapteka.messages.msgs" var="msgs" />

<head>
	<meta charset="utf-8">

	<link rel="stylesheet" href="css/style.css" type="text/css">

	<title>
		<fmt:message key="application.title" bundle="${msgs}" />
	</title>
</head>

<body>
	<h1><fmt:message key="welcomePage.header" bundle="${msgs}" /></h1>
	<div class="login">
		
			<a href = "controller.html?action=logout"><fmt:message key="loginOut.Out" bundle="${msgs}" /></a>
		</form>
	</div>
	<c:out value="${session_Id}" />
					<br />
	
					
					<br />
						<c:out value="${session_user.id}" />
					<br />
						<c:out value="${session_user.userName}" />
					
				

</body>
</html>