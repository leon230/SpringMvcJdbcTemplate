<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<c:url value="/resources/style.css" />" rel="stylesheet">
    <title>New/Edit Ticket</title>
</head>
<body>
	<div align="center">
		<h1>New/Edit Ticket</h1>
		<form:form action="saveTicket" method="post" modelAttribute="ticket">
		<form:errors path="*" class="errorblock" element="div"/>
		<table>
			<form:hidden path="id"/>
			<tr>
				<td>Number:</td>
				<td><form:input path="number" /></td>
				<td><form:errors path="number" class="error" /></td>
			</tr>
			<tr>
				<td>Title:</td>
				<td><form:input path="title" /></td>
			</tr>
			<tr>
				<td>Owner:</td>
				<td><form:input path="owner" /></td>
			</tr>
			<tr>
				<td>Cluster:</td>
				<td><form:input path="cluster" /></td>
			</tr>
			<tr>
				<td align="center"><input type="submit" value="Save"></td>
				<td align="center"><a href="/SpringMvcJdbcTemplate"> Cancel </a></td>
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>