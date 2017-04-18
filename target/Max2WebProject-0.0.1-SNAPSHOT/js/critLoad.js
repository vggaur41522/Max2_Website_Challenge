var labCodeArr = [];
var data2D3 = [];
var mapAllCode = new Map();
var symSel = false;
var labSel = false;
var ordSel = false;
var diagSel = false;
var treatSel = false;
var visiSel = false;

function onrefresh()
{
//    document.getElementById("finSub").disabled = false;
    
}

function throwErrorAlert(mesg)
{
    return  confirm("Tab: ["+mesg+"] already visited !!\nRevisiting could result duplicate selection. Please confirm ?");
}

$(document).ready(function() {
    var div = document.getElementById('selectedNode');
    div.style.visibility = "hidden";

    $("#quest4Btn").click(function(){
        $.ajax({
            url: "http://localhost:8080/Max2WebProject/quest4.html",
            type: 'get',
            dataType: 'json',
            success: function(data){
            	var rows = '';
            	for(var i in data){
            		rows += '<div id="detailZone" class = "detailPage" style="width: 100%;">';
            		rows += '<button  id="' +data[i].color+ '" value="colorButt" type="button">' +data[i].color+ '</button>';
            		rows += '<button  id="' +data[i].count+ '" value="colorButt" type="button">' +data[i].count+ '</button>';
            		rows += '</div>';
                    rows += '<br>';
                }  
            	$('#quest4Det').html(rows);
            },
            error: function(data){
            	alert("data in Error---"+data);
                console.log(data);
            }
        });
        
        $('button').removeClass('selected');
        $(this).addClass('selected');

    }); 
    
    $("#quest5Btn").click(function(){
        $.ajax({
            url: "http://localhost:8080/Max2WebProject/quest5.html",
            type: 'get',
            dataType: 'json',
            success: function(data){
            	var rows = '';
                for(var i in data){
                		rows += '<div id="detailZone" class = "detailPage" style="width: 100%;">';
                		rows += '<button  id="' +data[i].color+ '" value="colorButt" type="button">' +data[i].color+ '</button>';
                		rows += '<button  id="' +data[i].count+ '" value="colorButt" type="button">' +data[i].count+ '</button>';
                		rows += '<button  id="' +data[i].names+ '" value="colorButt" type="button">' +data[i].names+ '</button>';
                		rows += '</div>';
                        rows += '<br>';
                }  
                $('#quest5Det').html(rows);
            },
            error: function(data){
            	//alert("data in Error---"+data);
                console.log(data);
            }
        });
        
        $('button').removeClass('selected');
        $(this).addClass('selected');

    }); 
    
    $("#quest6Btn").click(function(){
        $.ajax({
            url: "http://localhost:8080/Max2WebProject/quest4.html",
            type: 'get',
            dataType: 'json',
            success: function(data){
            	var rows = '';
        		rows += '<div id="detailZone" class = "detailPage" style="width: 100%;">';
        		rows += '<pre style="width:70%">' + JSON.stringify(data, undefined, 2) + '</pre>';
        		rows += '</div>';
                rows += '<br>';
                $('#quest6Det').html(rows);
            },
            error: function(data){
                console.log(data);
            }
        });
        
        $('button').removeClass('selected');
        $(this).addClass('selected');

    }); 
    
    $("#quest7Btn").click(function(){
        $.ajax({
            url: "http://localhost:8080/Max2WebProject/quest5.html",
            type: 'get',
            dataType: 'json',
            success: function(data){
            	var rows = '';
        		rows += '<div id="detailZone" class = "detailPage" style="width: 100%;">';
        		rows += '<pre style="width:70%">' + JSON.stringify(data, undefined, 2) + '</pre>';
        		rows += '</div>';
                rows += '<br>';
                $('#quest7Det').html(rows);
            },
            error: function(data){
                console.log(data);
            }
        });
        
        $('button').removeClass('selected');
        $(this).addClass('selected');

    }); 
    
    $("#partIIBtn").click(function(){
    	if($("#lat").val() == "" || $("#lat").val() == undefined )
		{
    		alert("Please enter Latitude within less than 180");
    		return false;
		}
    	if($("#lon").val() == "" || $("#lon").val() == undefined )
		{
    		alert("Please enter Latitude within less than 180");
    		return false;
		}
    	var lat = $("#lat").val();
    	var lon = $("#lon").val();
    	var urlStr = "https://api.foursquare.com/v2/venues/search?ll="+lat+","+lon+"&oauth_token=X00IYURZ5KQP2ARIGIOSRCT1VUXCKJJUUX4CSGXWW4UJFQZS&v=20170416";
        $.ajax({
            url: urlStr,
            type: 'get',
            dataType: 'json',
            success: function(data){
            	var rows = '';
            	if(data.meta.code != "200")
        		{
            		alert("Please enter valid GEO COORDINATES");
        		}
            	for(var i in data.response.venues)
        		{
            		rows += '<div id="detailZone" class = "detailPage" style="width: 100%;">';
            		rows += '<pre style="width:70%">' + data.response.venues[i].name + '</pre>';
            		rows += '</div>';
                    rows += '<br>';
                    $('#partIIDet').html(rows);
        		}

            },
            error: function(data){
            	alert("Please provide valid input (lat/lon).. System responded BAD REQUEST");
                console.log(data);
            }
        });
        
        $('button').removeClass('selected');
        $(this).addClass('selected');

    }); 
    
});

function outputDocument(){
	var rows = '';
}


function validateForm()
{
    if( $('#startDate').val().length == 0){
        alert("Enter Start Date !!");
        return false;
    }
}