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
    <link href="<c:url value="/resources/jquery-ui.css" />" rel="stylesheet">
       <script src="<c:url value="/resources/external/jquery/jquery.js" />"></script>
       <script src="<c:url value="/resources/jquery-ui.js" />"></script>

    	<script type="text/javascript">
        $( "input[name=openDate]" ).datepicker({
        	format: 'yyyy-mm-dd'
        });
        </script>



    <title>New/Edit Ticket</title>
</head>
<jsp:include page="header.jsp" />
<body>
	<div align="center">
		<h1>New/Edit Ticket</h1>
		<form:form action="saveTicket" method="post" modelAttribute="TicketForm">
		<form:errors path="*" class="errorblock" element="div"/>
		<table>
			<form:hidden path="id"/>
			<spring:bind path="number">
			<tr>
				<td>Number:</td>
				<td><form:input path="number" /></td>
				<td><form:errors path="number" class="error" /></td>
			</tr>
			</spring:bind>
			<tr>
				<td>Title:</td>
				<td><form:input path="title" /></td>
			</tr>
			<spring:bind path="cluster">
			<tr>
				<td>Cluster:</td>
				<td><form:select path="cluster">
                    <form:option value="" label="...." />
                    <form:options items="${clusters}" />
                    </form:select></td>
			</tr>
			</spring:bind>
			<spring:bind path="owner">
			<tr>
				<td>Owner:</td>
				<td><form:input path="owner" /></td>
			</tr>
			</spring:bind>
			<spring:bind path="openDate">
            			<tr>
            				<td>Open date:</td>
            				<td><form:input path="openDate" /></td>
            			</tr>
            			</spring:bind>
			<tr>
				<td align="center"><input type="submit" value="Save"></td>
				<td align="center"><a href="/SpringMvcJdbcTemplate"> Cancel </a></td>
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>