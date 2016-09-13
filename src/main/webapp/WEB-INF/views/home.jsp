<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="cg" uri="customTLD.tld" %>

<sec:authorize access="hasRole('ROLE_USER')">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/resources/style.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/bootstrap.min.css" />" rel="stylesheet">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="<c:url value="/resources/js/jsmain.js" />"></script>
        <title>Ticket Manager Home</title>
    </head>

    <c:url value="home/editTicket?id=" var="editTicket" />
    <c:url value="home/deleteTicket?id=" var="deleteTicket" />
    <c:url value="home/export" var="ExportData" />
<div class = "headerbar">
<jsp:include page="header.jsp" />
</div>
    <body>

    <a href="./filter"> Filter data </a>
    <a>|</a>
    <a href="${ExportData}"> Export data </a>
    <a>|</a>

	<hr>

    	<div class="wrapper">
	        <h1>${title}</h1>
	        <table class="mainTable" id ="mainTable">
	        <thead>
	            <th>Action</th>
	        	<th>ID</th>
	        	<th>Ticket number</th>
	        	<th>Title</th>
	        	<th>Owner</th>
	        	<th>Cluster</th>
	        	<th>Request Date</th>
	        	<th>Open Date</th>
	        	<th>Due Date</th>
	        	<th>Close Date</th>
	        	<th min-width: "300px">Description</th>
	        	<th>Reported by</th>
	        	<th>Status</th>
	        	<th>Priority</th>
	        	<th>Oracle ticket num</th>
	        	<th>No</th>
	        	</thead>
	        	<tbody>
				<c:set var="cluster" scope = "session" value="${filter.clusters}"/>
				<c:set var="tstatus" scope = "session" value="${filter.statuses}"/>
				<c:set var="priority" scope = "session" value="${filter.priorities}"/>
				<c:set var="currentDate" scope = "session" value="${currentDate}"/>

				<c:forEach var="ticket" items="${listTicket}" varStatus="status">

                   <tr class = "${cg:changeClass(ticket.priority, ticket.tstatus, ticket.dueDate, currentDate, ticket.openDate)}">

                            <td>
                                <a href="${editTicket}${ticket.id}">Edit</a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                 <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                                    <a href ="${deleteTicket}${ticket.id}">Delete</a>
                                 </c:if>

                            </td>
                                <td class = "shortCol"><a href="#" onclick="javascript:showTicketActions('id-${status.index + 1}');"> + </a>${ticket.id}</td>
                                <td >${ticket.number}</td>
                                <td class = "longCol">${ticket.title}</td>
                                <td>${ticket.owner}</td>
                                <td>${ticket.cluster}</td>
                                <td>${ticket.requestDate}</td>
                                <td>${ticket.openDate}</td>
                                <td>${ticket.dueDate}</td>
                                <td>${ticket.closeDate}</td>
                                <td class = "longCol"><div class = "longText">${ticket.description}</div></td>
                                <td>${ticket.reportedBy}</td>
                                <td>${ticket.tstatus}</td>
                                <td>${ticket.priority}</td>
                                <td>${ticket.accOwner}</td>
                                <td>${status.index + 1}</td>

                    </tr>

                    <tr class="trActionsDet" id = "id-${status.index + 1}">
						<td> Ticket Action Details	<td>
                    </tr>

				</c:forEach>
			</tbody>
			</table>
    	</div>
    </body>
</html>
</sec:authorize>