<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
       <html>
       <title>Spring MVC checkbox</title>
       </head>

       <body>
       	<h2>Ticket filter</h2>

       	<form:form action="ApplyFilter" method="post" modelAttribute="member">
       		<table>
       			<tr>
       				<td>Are you a new member?</td>
       				<td><form:checkbox path="newMember" />
       				</td>
       			</tr>
       			<tr>
       				<td>Choose the courses you like:</td>
       				<td><form:checkboxes path="courses" items="${courses}" />
       				</td>
       			</tr>
       			<tr>
       				<td><input type="submit" name="submit" value="Submit"></td>
       			</tr>
       			<tr>
       		</table>
       	</form:form>

       </body>
       </html>