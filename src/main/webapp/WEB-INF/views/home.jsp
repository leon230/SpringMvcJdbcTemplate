<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/resources/style.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/bootstrap.min.css" />" rel="stylesheet">
        <title>Ticket Manager Home</title>
    </head>
<jsp:include page="header.jsp" />
    <body>
    	<div class="wrapper">
	        <h1>Ticket List</h1>
	        <table class="mainTable">
	        	<th>No</th>
	        	<th>ID</th>
	        	<th>Ticket number</th>
	        	<th>Title</th>
	        	<th>Owner</th>
	        	<th>Cluster</th>
	        	<th>Open Date</th>
	        	<th>Close Date</th>
	        	<th>Description</th>
	        	<th>Reported by</th>
	        	<th>Status</th>
	        	<th>Priority</th>
	        	<th>Oracle ticket num</th>
	        	<th>Action</th>
	        	
				<c:forEach var="ticket" items="${listTicket}" varStatus="status">
	        	<tr>
	        		<td>${status.index + 1}</td>
	        		<td>${ticket.id}</td>
					<td>${ticket.number}</td>
					<td>${ticket.title}</td>
					<td>${ticket.owner}</td>
					<td>${ticket.cluster}</td>
					<td>${ticket.openDate}</td>
					<td>${ticket.closeDate}</td>
					<td>${ticket.description}</td>
					<td>${ticket.reportedBy}</td>
					<td>${ticket.status}</td>
					<td>${ticket.priority}</td>
					<td>${ticket.accOwner}</td>
					<td>
						<button class="btn btn-primary" onclick="location.href='editTicket?id=${ticket.id}'">Edit</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="btn btn-danger" onclick="location.href='deleteTicket?id=${ticket.id}'">Delete</button>
					</td>
							
	        	</tr>
				</c:forEach>	        	
			</table>
    	</div>
    </body>
</html>