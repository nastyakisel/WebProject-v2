<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<fmt:setBundle basename="com.finalproject.onlineapteka.messages.msgs" var="msgs" />

<head>
	<meta charset="utf-8">

	<link rel="stylesheet" href="css/style.css" type="text/css">

	<title>
		<fmt:message key="application.title" bundle="${msgs}" />
	</title>
</head>

<br />
<br />
<body>
	<div class="largeFrame">
		<h2><fmt:message key="goodsList.header" bundle="${msgs}" /></h2>
		<br />
		<table border="1">
			<tr>
				<th>
					<fmt:message key="goodsList.id.header" bundle="${msgs}" />
				</th>
				<th>
					<fmt:message key="goodsList.goodsName.header" bundle="${msgs}" />
				</th>
				<th>
					<fmt:message key="goodsList.description.header" bundle="${msgs}" />
				</th>
				<th>
					<fmt:message key="goodsList.dosage.header" bundle="${msgs}" />
				</th>
				<th>
					<fmt:message key="goodsList.price.header" bundle="${msgs}" />
				</th>
			</tr>

			<c:forEach items="${drug_List}" var="drug">
				<tr>
					<td>
						<c:out value="${drug.id}" />
					</td>
					<td>
						<c:out value="${drug.drugName}" />
					</td>
					<td>
						<c:out value="${drug.description}" />
					</td>
					<td>
						<c:out value="${drug.dosage}" />
					</td>
					<td>
						<c:out value="${drug.price}" />
					</td>
					</tr>
					</c:forEach>
					</table>

	
</body>
</html>