/**
 * Author: H Pavan Kumar
 */
var start = 0;
var sentimentTypeGlobal = "";
var app= angular.module('topguns',[]);
app.controller('homecontroller', function($scope,$http) {
    	$scope.data = [];
    	$scope.data.doClick = function(item,event){
    		$('#tweetTable').focus();
    		$("#processNextPrev").append("<img id='processing' src='"+contextpath+"/resources/images/process.gif' height='50' width='50'/>");
    		var clickedId = event.currentTarget.id;
    		//alert(clickedId);
    		var sentimentType ="";
    		if(clickedId == 'positiveSentiment'){
    			sentimentType = "positive";
    			sentimentTypeGlobal = sentimentType;
    			start =0;
    		}else if(clickedId == 'neutralSentiment'){
    			sentimentType = "neutral";
    			sentimentTypeGlobal = sentimentType;
    			start =0;
    		}else if(clickedId == 'negativeSentiment'){
    			sentimentType = "negative";
    			sentimentTypeGlobal = sentimentType;
    			start=0;
    		}
    		if(sentimentType == ""){
    			sentimentType = sentimentTypeGlobal;
    		}
    		if(clickedId == 'prevNav'){
	    		start = Number(start) -10;
	    		$('#nextNav').css("display","");
	    	}else if(clickedId == 'nextNav'){
	    		start = Number(start) +10;
	    	}
    		if(Number(start)>9){
	        	$('#prevNav').css("display","");
	        }else{
	        	$('#prevNav').css("display","none");
	        }
    		var startVar = Number(start);
    		//alert(sentimentType);
    		//alert(startVar);
    		var responsePromise = $http({
    	        method: 'GET',
    	        url: contextpath+"/tweetresponse?type="+sentimentType+"&start="+startVar+"&end=10",
    	        //params: 'limit=10, sort_by=created:desc',
    	        headers: {"Content-Type": "application/json",
    	        			"Content-Type": "application/x-www-form-urlencoded",
    	        			"X-Requested-With": "XMLHttpRequest"
    	        		 }
    	     });
    	     responsePromise.success(function(data){
    	        // With the data succesfully returned, call our callback
    	    	 $("#processNextPrev").empty();
    	    	$('#endRecord').val('');
    	    	
    	    	//alert(startVar);
    	        var response = data.tweetList;
    	        //alert(response);
    	        var trData = "";
    	        for(var i in response){
    	        	trData = trData + '<tr><td>'+response[i].tweet +"</td></tr>";
    	        }
    	        if(clickedId == 'nextNav' && trData == ""){
    	        	$('#nextNav').css("display","none");
    	        	start = Number(start) -10;
    	        }else{
    	        	$('#tweetTable tbody').empty();
    	        	$('#tweetTable tbody').append(trData);
    	        }
    	        
    	    });
    	    responsePromise.error(function(data,status,headers,config){
    	    	//alert(status);
    	        alert("Exception returned from Server. Please try later.");
    	    });
    	};
});
function closeHourlyChart(){
	$('#morris-bar-chart-dialyVol').empty();
	$('#morris-bar-chart-dialyVol').css('min-height','10px');
	$('#morris-bar-chart-dialyVol-panel').removeClass('panel');
	$('#morris-bar-chart-dialyVol-panel-head').css("display","none");
	$('#hourlySpanTitle').html('');
}

function getDataUsageByDate(dateObj){
	var urlLnk = contextpath+"/getDataByDate?dateVal="+dateObj;
	$.ajax({url: urlLnk, success: function(result){
        var jsonObjResp = JSON.parse(result);
        var hourlyList = jsonObjResp.hourlyListByDate;
        generateDataPageHourlyChart(hourlyList);
        $('#morris-bar-chart-dialyVol-panel').addClass('panel');
    	$('#morris-bar-chart-dialyVol-panel-head').css("display","");
    	$('#hourlySpanTitle').html(dateObj);
    }});
}

function generateDataPageHourlyChart(responseObj){
	//alert(JSON.stringify(responseObj));
	$('#morris-bar-chart-dialyVol').empty();
	$('#morris-bar-chart-dialyVol').css('min-height','250px');
	
//	var dataObj = responseObj.dataListByDate;
	//alert(JSON.stringify(dataObj));
    Morris.Bar({
        element: 'morris-bar-chart-dialyVol',
        data: responseObj,
        xkey: 'time',
        ykeys: ['bytes'],
        labels: ['Data Used (byes)'],
        barRatio: 0.4,
        xLabelAngle: 35,
        hideHover: 'true',
        resize: true
    });
}