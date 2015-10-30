/**
 * Author: H Pavan Kumar
 */



function generateDonut(resObject){
	var appList = resObject.appUsageList;
	//var temp = '[{"label":"whatsapp","value":1435676},{"label":"whatsapp1","value":143566}]';
	//var appList = JSON.parse(temp);
	//alert(JSON.stringify(appList));
	Morris.Donut({
	        element: 'morris-donut-chart-tg',
	        data: appList,
	        colors:["#9CC4E4", "#3A89C9"],
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

function generateDataPageBarChart(responseObj){
	//alert(JSON.stringify(responseObj));
	$('#morris-bar-chart-dataVol').empty();
	var dataObj = responseObj.dataListByDate;
	//alert(JSON.stringify(dataObj));
    Morris.Bar({
        element: 'morris-bar-chart-dataVol',
        data: dataObj,
        xkey: 'date',
        ykeys: ['bytes'],
        labels: ['Data Used (byes)'],
        barRatio: 0.4,
        xLabelAngle: 35,
        hideHover: 'true',
        resize: true
    }).on('click', function(i, row){
    	  console.log(i, row);
    	  var ddDateObj = row;
    	  getDataUsageByDate(ddDateObj.date);
    });
}

function generateCallPageBarChart(responseObj){
	//alert(JSON.stringify(responseObj));
	$('#morris-bar-chart-dataVol').empty();
	var dataObj = responseObj.dataListByDate;
	//alert(JSON.stringify(dataObj));
    Morris.Bar({
        element: 'morris-bar-chart-dataVol',
        data: dataObj,
        xkey: 'date',
        ykeys: ['duration'],
        labels: ['Call Duration'],
        barRatio: 0.4,
        xLabelAngle: 35,
        hideHover: 'true',
        resize: true
    });
}

var getBytesWithUnit = function( bytes ){
	//alert(bytes);
	if( isNaN( bytes ) ){ return; }
	var units = [ ' bytes', ' KB', ' MB', ' GB', ' TB', ' PB', ' EB', ' ZB', ' YB' ];
	var amountOf2s = Math.floor( Math.log( +bytes )/Math.log(2) );
	if( amountOf2s < 1 ){
		amountOf2s = 0;
	}
	var i = Math.floor( amountOf2s / 10 );
	bytes = +bytes / Math.pow( 2, 10*i );
 
	// Rounds to 3 decimals places.
        if( bytes.toString().length > bytes.toFixed(3).toString().length ){
            bytes = bytes.toFixed(3);
        }
	return bytes + units[i];
};