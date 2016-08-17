<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<sec:authorize access="hasRole('ROLE_USER')">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/resources/style.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/bootstrap.min.css" />" rel="stylesheet">
        <title>Ticket Manager Home</title>
    </head>

    <c:url value="home/editTicket?id=" var="editTicket" />
    <c:url value="home/deleteTicket?id=" var="deleteTicket" />

<jsp:include page="header.jsp" />
    <body>
    	<div class="wrapper">
	        <h1>Ticket List</h1>
	        <table class="mainTable" id ="mainTable">
	        <thead>
	            <th>Action</th>
	        	<th>ID</th>
	        	<th>Ticket number</th>
	        	<th>Title</th>
	        	<th>Owner</th>
	        	<th>Cluster</th>
	        	<th>Open Date</th>
	        	<th>Close Date</th>
	        	<th min-width: "300px">Description</th>
	        	<th>Reported by</th>
	        	<th>Status</th>
	        	<th>Priority</th>
	        	<th>Oracle ticket num</th>
	        	<th>No</th>
	        	</thead>
	        	<tbody>
				<c:forEach var="ticket" items="${listTicket}" varStatus="status">
	        	<tr>
	        	<td>
                    <button class="btn btn-primary" onclick="location.href='${editTicket}${ticket.id}'">Edit</button>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                     <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                        <button class="btn btn-danger" onclick="location.href='${deleteTicket}${ticket.id}'">Delete</button>
                     </c:if>

                </td>
                    <td class = "shortCol">${ticket.id}</td>
					<td>${ticket.number}</td>
					<td class = "longCol">${ticket.title}</td>
					<td>${ticket.owner}</td>
					<td>${ticket.cluster}</td>
					<td>${ticket.openDate}</td>
					<td>${ticket.closeDate}</td>
					<td class = "longCol"><div class = "longText">${ticket.description}</div></td>
					<td>${ticket.reportedBy}</td>
					<td>${ticket.tstatus}</td>
					<td>${ticket.priority}</td>
					<td>${ticket.accOwner}</td>
					<td>${status.index + 1}</td>

							
	        	</tr>
	        	</tbody>
				</c:forEach>	        	
			</table>
    	</div>
    </body>
</html>
</sec:authorize>