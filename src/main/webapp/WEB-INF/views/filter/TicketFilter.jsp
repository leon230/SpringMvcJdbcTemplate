<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<c:url value="/resources/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/style.css" />" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/jsmain.js" />"></script>

    <title>Filter Ticket</title>
</head>
<jsp:include page="../header.jsp" />
       <body>
       	<h2>Ticket filter</h2>
<a href="#" onclick="javascript:resetFilter();"> Reset filter </a>
<div class="container">
       	<form:form action="ApplyFilter" method="post" modelAttribute="filter">
       	<form:errors path="*" class="errorblock" element="div"/>
		<div class="form-group">
				<label class="col-sm-2 control-label">Cluster:</label>
				<div class="filterDiv">
					<c:forEach var="cluster" items="${clusters}">
						<p><form:checkbox path="clusters" value = "${cluster}" />${cluster}</p>
					</c:forEach>
				</div>
		</div>

		<div class="form-group">
        				<label class="col-sm-2 control-label">Priorities:</label>
        				<div class="filterDiv">
        					<c:forEach var="priority" items="${priorities}">
								<p><form:checkbox path="priorities" value = "${priority}" />${priority}</p>
							</c:forEach>
        				</div>
        		</div>

        		<div class="form-group">
                				<label class="col-sm-2 control-label">Statuses:</label>
                				<div class="filterDiv">
                					<c:forEach var="pstatus" items="${statuses}">
										<p><form:checkbox path="statuses" value = "${pstatus}" />${pstatus}</p>
									</c:forEach>
                				</div>
                		</div>
		<div class="form-group">
        			<div class="col-sm-offset-2 col-sm-10">
        				<input type="submit" name="submit" value="Submit">
        			</div>
        		    </div>


       	</form:form>
</div>
       </body>
       </html>