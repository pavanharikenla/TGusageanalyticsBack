/**
 * Author: H Pavan Kumar
 */



function generateDonut(resObject){
	var agelist = resObject.agegroup;
	//alert(JSON.stringify(agelist));
	Morris.Donut({
	        element: 'morris-donut-chart-tg',
	        data: agelist,
	        resize: true
	    });
}

function generateAreaChart(resObject){
	var categoryList = resObject.categoryData;
	//alert(JSON.stringify(categoryList));
	// Area Chart
    Morris.Area({
        element: 'morris-area-chart-tg',
        data: categoryList,
        xkey: 'period',
        ykeys: ['Smartphones', 'Tablets', 'Products'],
        labels: ['Smartphones', 'Tablets', 'Products'],
        pointSize: 2,
        hideHover: 'auto',
        parseTime: false,
        resize: true
    });
}



function generatePieChartProduct(responseObj){
	var productData = responseObj.productList;
	//alert(JSON.stringify(productData));
	// Flot Pie Chart with Tooltips
	

	    var data = productData;
	    //alert(data);
	    var options = {
	            series: {
	                pie: {show: true}
	            },grid: {
		            hoverable: true
		        },tooltip: true,
		        tooltipOpts: {
		            content: "%p.0%, %s", // show percentages, rounding to 2 decimal places
		            shifts: {
		                x: 20,
		                y: 0
		            },
		            defaultTheme: false
		        }
	         };

	    $.plot($("#flot-pie-chart-tg"), data, options);
}
function generatepieChartSmartPh(responseObj){
	var productData = responseObj.phonesAndTablets;
	//alert(JSON.stringify(productData));
	// Flot Pie Chart with Tooltips
	

	    var data = productData;
	    //alert(data);
	    var options = {
	            series: {
	                pie: {show: true}
	            },grid: {
		            hoverable: true
		        },tooltip: true,
		        tooltipOpts: {
		            content: "%p.0%, %s", // show percentages, rounding to 2 decimal places
		            shifts: {
		                x: 20,
		                y: 0
		            },
		            defaultTheme: false
		        }
	         };

	    $.plot($("#flot-pie-chart-tg-sm"), data, options);
}

function generateLineChart(responseObj){
		//alert(responseObj);
	var dataObj = responseObj.sentimentData;
	//alert(JSON.stringify(dataObj));
	Morris.Line({
		  element: 'flot-line-chart-tg',
		  data: dataObj,
		  xkey: 'period',
		  ykeys: ['negative', 'neutral','positive'],
		  labels: ['negative', 'neutral','positive'],
		  lineColors: ['red','orange' ,'green'],
		  parseTime: false
	});
}

function requestCharts(){
	window.location.href = contextpath + "/charts";
}
function requestHome(){
	window.location.href = contextpath + "/";
}
function requestSearch(){
	window.location.href = contextpath + "/search";
}