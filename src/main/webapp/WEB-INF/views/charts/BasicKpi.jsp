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
            google.setOnLoadCallback(drawPrioritySolveChart);

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
				var ProgressChart_div = document.getElementById('ProgressChart_div');
				var chart = new google.visualization.PieChart(ProgressChart_div);

				// Wait for the chart to finish drawing before calling the getImageURI() method.
							  google.visualization.events.addListener(chart, 'ready', function () {
								ProgressChart_div.innerHTML = '<img src="' + chart.getImageURI() + '">';
								console.log(ProgressChart_div.innerHTML);
							  });


                chart.draw(data, options);
				document.getElementById('ProgressChart_prnt').outerHTML = '<a href="' + chart.getImageURI() + '">Printable version</a>';
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
				var Prioritychart_div = document.getElementById('Prioritychart_div');
                var chart = new google.visualization.PieChart(Prioritychart_div);

				// Wait for the chart to finish drawing before calling the getImageURI() method.
							  google.visualization.events.addListener(chart, 'ready', function () {
								Prioritychart_div.innerHTML = '<img src="' + chart.getImageURI() + '">';
								console.log(Prioritychart_div.innerHTML);
							  });

                chart.draw(dataPriority, optionsPriority);
				document.getElementById('Prioritychart_prnt').outerHTML = '<a href="' + chart.getImageURI() + '">Printable version</a>';
            }
            function drawPrioritySolveChart() {

                            // Create the data table.
                            var dataPrioritySolve = google.visualization.arrayToDataTable([
                                                                                  ['Year','High', 'Medium', 'Low', { role: 'annotation' } ],
                                                                                  <c:forEach items="${prioritySolveDataList}" var="entry">
                                                                                      ['${entry.key}',
																					  ${entry.value},
																					  ${entry.value2},
																					  ${entry.value3},
																					  '' ],
                                                                                  </c:forEach>
                                                                            ]);
                            // Set chart options
                            var optionsPrioritySolve = {
                                legend: { position: 'top', maxLines: 3 },
								isStacked: 'percent',
								Text: 'label',
                                bar: { groupWidth: '75%' },
                                'width' : 600,
                                'height' : 400,
								vAxis: {
								minValue: 0,
								ticks: [0, .3, .6, .9, 1]
							  },
							   series: {
								0:{color:'red'},
								1:{color:'orange'},
								2:{color:'green'}
							  }

                            };

                            // Instantiate and draw our chart, passing in some options.
							var PrioritySolvechart_div = document.getElementById('PrioritySolvechart_div');
                            var chart = new google.visualization.ColumnChart(PrioritySolvechart_div);

							// Wait for the chart to finish drawing before calling the getImageURI() method.
							  google.visualization.events.addListener(chart, 'ready', function () {
								PrioritySolvechart_div.innerHTML = '<img src="' + chart.getImageURI() + '">';
								console.log(PrioritySolvechart_div.innerHTML);
							  });

                            chart.draw(dataPrioritySolve, optionsPrioritySolve);
							document.getElementById('PrioritySolvechart_prnt').outerHTML = '<a href="' + chart.getImageURI() + '">Printable version</a>';
                        }
        </script>
    </head>
<div class = "headerbar">
<jsp:include page="../header.jsp" />
</div>
<body>




<h1>${title}</h1>
<div align = "center">
	<table>
      <tr>
        <td class = "chart_td"><div id="ProgressChart_div"></div><div id='ProgressChart_prnt'></div></td>
        <td class = "chart_td"><div id="Prioritychart_div"></div><div id='Prioritychart_prnt'></div></td>
	  <tr>
		<td class = "chart_td"><div id="PrioritySolvechart_div"></div><div id='PrioritySolvechart_prnt'></div></td>
	  </tr>
      </tr>
    </table>
</div>


</body>

</html>