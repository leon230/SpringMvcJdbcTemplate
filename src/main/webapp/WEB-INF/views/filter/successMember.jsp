<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Spring MVC checkbox</title>
</head>

<body>
	<h2>The courses you selected are shown below:</h2>
	<br>
	<c:forEach var="course" items="${member.courses}">
			<c:out value="${course}"/><br>
	</c:forEach>

</body>
</html>