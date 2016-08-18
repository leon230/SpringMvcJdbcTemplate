<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page session="true"%>
<html>

	<h1>${title}</h1>
    <head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Google Chart - Servlet 3</title>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
        // Load the Visualization API and the piechart package.
        google.load('visualization', '1.0', {
            'packages' : [ 'corechart' ]
        });

        // Set a callback to run when the Google Visualization API is loaded.
        google.setOnLoadCallback(drawChart);

        // Callback that creates and populates a data table,
        // instantiates the pie chart, passes in the data and
        // draws it.
        function drawChart() {

            // Create the data table.
            var data = google.visualization.arrayToDataTable([
                                                                  ['Country', 'Area(square km)'],
                                                                  <c:forEach items="${pieDataList}" var="entry">
                                                                      [ '${entry.key}', ${entry.value} ],
                                                                  </c:forEach>
                                                            ]);
            // Set chart options
            var options = {
                'title' : 'Area-wise Top Seven Countries in the World',
                is3D : true,
                pieSliceText: 'label',
                tooltip :  {showColorCode: true},
                'width' : 900,
                'height' : 500
            };

            // Instantiate and draw our chart, passing in some options.
            var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    </script>
    </head>


<body>
 <div style="width: 600px;">
        <div id="chart_div"></div>
    </div>

<div id="chart_div"></div>
	<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>

</body>
</html>