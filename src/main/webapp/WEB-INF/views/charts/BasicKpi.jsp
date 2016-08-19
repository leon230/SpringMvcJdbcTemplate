<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page session="true"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Basic KPI charts</title>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
            // Load the Visualization API and the piechart package.
            google.load('visualization', '1.0', {
                'packages' : [ 'corechart' ]
            });

            // Set a callback to run when the Google Visualization API is loaded.
            google.setOnLoadCallback(drawProgressChart);
            google.setOnLoadCallback(drawPriorityChart);

            // Callback that creates and populates a data table,
            // instantiates the pie chart, passes in the data and
            // draws it.
            function drawProgressChart() {

                // Create the data table.
                var data = google.visualization.arrayToDataTable([
                                                                      ['Progress', 'Number'],
                                                                      <c:forEach items="${progressDataList}" var="entry">
                                                                          [ '${entry.key}', ${entry.value} ],
                                                                      </c:forEach>
                                                                ]);
                // Set chart options
                var options = {
                    'title' : 'Ticket progress',
                    is3D : true,
                    pieSliceText: 'label',
                    tooltip :  {showColorCode: true},
                    'width' : 600,
                    'height' : 400
                };

                // Instantiate and draw our chart, passing in some options.
                var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
                chart.draw(data, options);
            }
			function drawPriorityChart() {

                // Create the data table.
                var dataPriority = google.visualization.arrayToDataTable([
                                                                      ['Priority', 'Number'],
                                                                      <c:forEach items="${priorityDataList}" var="entry">
                                                                          [ '${entry.key}', ${entry.value} ],
                                                                      </c:forEach>
                                                                ]);
                // Set chart options
                var optionsPriority = {
                    'title' : 'Ticket priority',
                    is3D : true,
                    pieSliceText: 'label',
                    tooltip :  {showColorCode: true},
                    'width' : 600,
                    'height' : 400
                };

                // Instantiate and draw our chart, passing in some options.
                var chart = new google.visualization.PieChart(document.getElementById('Prioritychart_div'));
                chart.draw(dataPriority, optionsPriority);
            }
        </script>
    </head>
<div class = "headerbar">
<jsp:include page="../header.jsp" />
</div>
<body>
<h1>${title}</h1>
	<table>
      <tr>
        <td class = "chart_border"><div id="chart_div"></div></td>
        <td class = "chart_border"><div id="Prioritychart_div"></div></td>
      </tr>
    </table>


</body>

</html>