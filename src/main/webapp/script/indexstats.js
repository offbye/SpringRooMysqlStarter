$(function() {
	/*
	 * $.getJSON('/ecplatform/messages/userregstatic?type=all?&callback=?',
	 * function(data) { // Create the chart window.chart = new
	 * Highcharts.StockChart({ chart : { renderTo : 'userRegStats' },
	 * 
	 * rangeSelector : { selected : 1 },
	 * 
	 * title : { text : '用户统计' },
	 * 
	 * series : [ { name : '注册用户数', data : data, tooltip : { valueDecimals : 0 } } ]
	 * }); });
	 */
	var seriesOptions = [], yAxisOptions = [], seriesCounter = 0, names = [
			'All', 'WebSite' , 'AndroidPhone','iPhone'], colors = Highcharts.getOptions().colors;

	$.each(names, function(i, name) {

		$.getJSON('userregstats?type=' + name + '&callback=?',
				function(data) {

					seriesOptions[i] = {
						name : name,
						data : data
					};

					// As we're loading the data asynchronously, we don't know
					// what order it will arrive. So
					// we keep a counter and create the chart when all the data
					// is loaded.
					seriesCounter++;

					if (seriesCounter == names.length) {
						createChart();
					}
				});
	});

	// create the chart when all data is loaded
	function createChart() {

		chart = new Highcharts.StockChart(
				{
					title : { text : '注册用户统计' },
					chart : {
						renderTo : 'userRegStats'
					},

					rangeSelector : {
						selected : 0
					},

					yAxis : {
						labels : {
							formatter : function() {
								return (this.value > 0 ? '+' : '') + this.value
										+ '%';
							}
						},
						plotLines : [ {
							value : 0,
							width : 2,
							color : 'silver'
						} ]
					},

					plotOptions : {
						series : {
							compare : 'percent'
						}
					},

					tooltip : {
						pointFormat : '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.change}%)<br/>',
						valueDecimals : 0
					},

					series : seriesOptions
				});
	}

	$.getJSON('/ecplatform/messages/videocatstats?callback=?', function(data) {
		new Highcharts.Chart({
			chart : {
				renderTo : 'videoCatStats',
				plotBackgroundColor : null,
				plotBorderWidth : null,
				plotShadow : false
			},
			title : {
				text : '视频分类统计'
			},
			tooltip : {
				pointFormat : '{series.name}: <b>{point.percentage}%</b>',
				percentageDecimals : 1
			},
			plotOptions : {
				pie : {
					allowPointSelect : true,
					cursor : 'pointer',
					dataLabels : {
						enabled : false
					},
					showInLegend : false
				}
			},
			series : [ {
				type : 'pie',
				name : '占',
				data : data
			} ]
		});

	});
	
	
	$.getJSON('userformtypestats?callback=?', function(data) {
		new Highcharts.Chart({
			chart : {
				renderTo : 'fromTypeStats',
				plotBackgroundColor : null,
				plotBorderWidth : null,
				plotShadow : false
			},
			title : {
				text : '用户来源统计'
			},
			tooltip : {
				pointFormat : '{series.name}: <b>{point.percentage}%</b>',
				percentageDecimals : 1
			},
			plotOptions : {
				pie : {
					allowPointSelect : true,
					cursor : 'pointer',
					dataLabels : {
						enabled : false
					},
					showInLegend : false
				}
			},
			series : [ {
				type : 'pie',
				name : '占',
				data : data
			} ]
		});

	});

});