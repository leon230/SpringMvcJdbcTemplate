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
<jsp:include page="header.jsp" />
<body>
	<div class="container">
		<h1>New/Edit Ticket</h1>
		<form:form action="saveTicket" method="post" modelAttribute="TicketForm">
		<form:errors path="*" class="errorblock" element="div"/>

			<form:hidden path="id"/>

			<spring:bind path="number">
			<div class="form-group">
				<label class="col-sm-2 control-label">Number</label>
				<div class="col-sm-10">
					<form:input path="number" type="text" class="form-control " id="number" placeholder="Ticket number" />
					<form:errors path="number" class="control-label" />
				</div>
			</div>
			</spring:bind>

			<spring:bind path="title">
			<div class="form-group">
				<label class="col-sm-2 control-label">Ticket title</label>
				<div class="col-sm-10">
					<form:input path="title" type="text" class="form-control " id="title" placeholder="Ticket title" />
					<form:errors path="title" class="control-label" />
				</div>
			</div>
			</spring:bind>

			<spring:bind path="cluster">
			<div class="form-group">
				<label class="col-sm-2 control-label">Cluster</label>
				<div class="col-sm-10">
					<form:select path="cluster" class="form-control">
						<form:option value="" label="--- Select ---" />
						<form:options items="${clusters}" />
					</form:select>
					<form:errors path="cluster" class="control-label" />
				</div>
			</div>
			</spring:bind>

			<spring:bind path="owner">
			<div class="form-group">
				<label class="col-sm-2 control-label">Ticket owner</label>
				<div class="col-sm-10">
					<form:input path="owner" type="text" class="form-control " id="owner" placeholder="Ticket owner" />
					<form:errors path="owner" class="control-label" />
				</div>
			</div>
			</spring:bind>

			<spring:bind path="openDate">
			<div class="form-group">
				<label class="col-sm-2 control-label">Open date</label>
				<div class="col-sm-10">
					<form:input path="openDate" type="text" class="form-control " id="openDate" placeholder="Open date" />
					<form:errors path="openDate" class="control-label" />
				</div>
			</div>
			</spring:bind>
			<spring:bind path="closeDate">
            <div class="form-group">
                <label class="col-sm-2 control-label">Open date</label>
                <div class="col-sm-10">
                    <form:input path="closeDate" type="text" class="form-control " id="closeDate" placeholder="Close date" />
                    <form:errors path="closeDate" class="control-label" />
                </div>
            </div>
            </spring:bind>

            <spring:bind path="description">
            <div class="form-group">
                <label class="col-sm-2 control-label">Ticket owner</label>
                <div class="col-sm-10">
                    <form:textarea path="description" type="text" class="form-control " id="description" placeholder="Ticket description" />
                    <form:errors path="description" class="control-label" />
                </div>
            </div>
            </spring:bind>

            <spring:bind path="reportedBy">
            <div class="form-group">
                <label class="col-sm-2 control-label">Ticket owner</label>
                <div class="col-sm-10">
                    <form:input path="reportedBy" type="text" class="form-control " id="reportedBy" placeholder="Reported by" />
                    <form:errors path="reportedBy" class="control-label" />
                </div>
            </div>
            </spring:bind>

            <spring:bind path="priority">
            <div class="form-group">
                <label class="col-sm-2 control-label">Priority</label>
                <div class="col-sm-10">
                    <form:select path="priority" class="form-control">
                        <form:option value="" label="--- Select ---" />
                        <form:options items="${priorities}" />
                    </form:select>
                    <form:errors path="priority" class="control-label" />
                </div>
            </div>
            </spring:bind>

            <spring:bind path="status">
            <div class="form-group">
                <label class="col-sm-2 control-label">Status</label>
                <div class="col-sm-10">
                    <form:select path="status" class="form-control">
                        <form:option value="" label="--- Select ---" />
                        <form:options items="${statuses}" />
                    </form:select>
                    <form:errors path="status" class="control-label" />
                </div>
            </div>
            </spring:bind>

            <spring:bind path="accOwner">
            <div class="form-group">
                <label class="col-sm-2 control-label">Oracle ticket</label>
                <div class="col-sm-10">
                    <form:input path="accOwner" type="text" class="form-control " id="accOwner" placeholder="Oracle ticket" />
                    <form:errors path="accOwner" class="control-label" />
                </div>
            </div>
            </spring:bind>

			<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" value="Save">
				<a href="/TicketSys"> Cancel </a>
			</div>
		</div>



		</form:form>
	</div>
</body>
</html>