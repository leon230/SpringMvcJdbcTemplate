<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h1>HTTP Status 403 - Access is denied</h1>

	<c:choose>
		<c:when test="${empty username}">
			<h2>You do not have permission to access this page!</h2>
		</c:when>
		<c:otherwise>
			<h2>Username : ${username} <br/>You do not have permission to access this page!</h2>
		</c:otherwise>
	</c:choose>

	<sec:authorize access="hasRole('ROLE_USER')">
    		<!-- For login user -->
    		<c:url value="/logout" var="logoutUrl" />
    		<spring:url value="/" var="urlHome" />
            <spring:url value="/newTicket" var="urlAddTicket" />
            <spring:url value="/admin" var="urlAdmin" />

            <nav class="navbar navbar-inverse ">
            	<div class="container">

            		<div id="navbar">
                        <ul class="nav navbar-nav navbar-right">
                            <li class="active">
                            <c:if test="${pageContext.request.userPrincipal.name != null}">
                                <a href="javascript:formSubmit()">Logout</a>
                             </c:if>
                             <form action="${logoutUrl}" method="post" id="logoutForm">
                                <input type="hidden" name="${_csrf.parameterName}"
                                value="${_csrf.token}" />
                            </form>
                             </li>
                        </ul>
                    </div>
            	</div>
            </nav>


    		<script>
    			function formSubmit() {
    				document.getElementById("logoutForm").submit();
    			}
    		</script>
    </sec:authorize>

</body>
</html>