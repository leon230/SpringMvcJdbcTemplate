<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/resources/style.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/bootstrap.min.css" />" rel="stylesheet">
        <title>Users</title>
    </head>
<jsp:include page="header.jsp" />
    <body>
    	<div class="wrapper">
	        <h1>Users</h1>
	        <table class="mainTable">
	        	<th>Username</th>
	        	<th>Password</th>
	        	<th>ID</th>
	        	<th>Role</th>


				<c:forEach var="user" items="${listuser}" varStatus="status">
	        	<tr>
	        		<td>${user.userName}</td>
					<td>${user.userPass}</td>
					<td>${user.userId}</td>
					<td>${user.userRole}</td>
	        	</tr>
				</c:forEach>
			</table>
    	</div>
    </body>
</html>