var partIIPlaces = [];
var partIIIData = [];
var baseUrl = "https://max2inc-challenge.herokuapp.com/";
function onrefresh()
{
	// document.getElementById("finSub").disabled = false;
}

function throwErrorAlert(mesg)
{
    return  confirm("Tab: ["+mesg+"] already visited !!\nRevisiting could result duplicate selection. Please confirm ?");
}

$(document).ready(function() {
	
    $("#quest4Btn").click(function(){
        $.ajax({
            url: baseUrl + "quest4.html",
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
                console.log(data);
            }
        });
        
        $('button').removeClass('selected');
        $(this).addClass('selected');

    }); 
    
    $("#quest5Btn").click(function(){
        $.ajax({
            url: baseUrl + "quest5.html",
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
            url: baseUrl + "quest4.html",
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
            url: baseUrl + "quest5.html",
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
    		alert("Please enter Latitude less than 180");
    		return false;
		}
    	if($("#lon").val() == "" || $("#lon").val() == undefined )
		{
    		alert("Please enter Longitude less than 180");
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
            		$('#partIIDet').html('<pre style="width:70%"></pre>');
            		alert("Please enter valid Geo Coordinate !!");
        		}
            	if(data.response.venues.length == 0)
            	{
            		$('#partIIDet').html('<pre style="width:70%"></pre>');
            		alert("Please enter a valid Geo coordinate !!");
            	}
            	for(var i in data.response.venues)
        		{
            		rows += '<div id="detailZone" class = "detailPage" style="width: 100%;">';
            		rows += '<pre style="width:70%">' + data.response.venues[i].name + '</pre>';
            		rows += '</div>';
                    rows += '<br>';
                    partIIPlaces[i] = data.response.venues[i].name;
        		}
            	if(partIIPlaces.length > 0)
        		{
            		$.ajax({
            		    type : "POST",
            		    dataType: 'json',
            		    url : baseUrl + "partII.html", // Change to Heroku Path !!
            		    data : {
            		    	"myArr" : partIIPlaces  
            		    },
            		    success : function(response) {
            		    	$('#partIIDet').html('<pre style="width:70%">'+ JSON.stringify(response, undefined, 2) +'</pre>');
            		    },
            		    error : function(e) {
            		       alert('Error: ' + e);
            		    }
            		});
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
    
    $("#partIIIBtn").click(function(){
    	if(validateForm() == false)
    		return false;
	    	partIIIData[0] =  $('#fName').val(),
			partIIIData[1] =  $('#lName').val();
			partIIIData[2] =  $('#address').val();
			partIIIData[3] =  $('#zipCode').val();
			partIIIData[4] =  $('#phone').val();
			partIIIData[5] =  $('#color').val();
    	$.ajax({
		    type : "POST",
		    url : baseUrl + "partIII.html", // Change to Heroku Path !!
		    data : {
		    	"myArr" : partIIIData  
		    },
		    success : function(response) {
		       // do something ... 
		    	$('#partIIIDet').html('<h4 style="width:70%">'+ response +'</h4>');
		    },
		    error : function(e) {
		       alert('Error: ' + e);
		    }
		});
        $('button').removeClass('selected');
        $(this).addClass('selected');
    }); 
    
    $("#summaryBtn").click(function(){
    	alert("Summart Button");
        $.ajax({
            url: baseUrl + "summary.html",
            type: 'get',
            dataType: 'json',
            success: function(data){
            	var rows = '<div id="detailZone" class = "detailPage" style="width: 100%;">';
            	rows += '<button  type="button">Max2 Id</button>';
            	rows += '<button  type="button">First Name</button>';
            	rows += '<button  type="button">Last Name</button>';
            	rows += '<button  type="button">Address</button>';
            	rows += '<button  type="button">Phone No.</button>';
            	rows += '<button  type="button">Zip Code</button>';
            	rows += '<button  type="button">Color</button>';
            	rows += '</div>';
                for(var i in data){
                		rows += '<div id="detailZone" class = "detailPage" style="width: 100%;">';
                		rows += '<button  id="' +data[i].MaxId+ '" value="fname" type="button">' +data[i].MaxId+ '</button>';
                		rows += '<button  id="' +data[i].FName+ '" value="fname" type="button">' +data[i].FName+ '</button>';
                		rows += '<button  id="' +data[i].LName+ '" value="lname" type="button">' +data[i].LName+ '</button>';
                		rows += '<button  id="' +data[i].Address+ '" value="address" type="button">' +data[i].Address+ '</button>';
                		rows += '<button  id="' +data[i].PhoneNo+ '" value="phoneNo" type="button">' +data[i].PhoneNo+ '</button>';
                		rows += '<button  id="' +data[i].ZipCode+ '" value="zipCode" type="button">' +data[i].ZipCode+ '</button>';
                		rows += '<button  id="' +data[i].Color+ '" value="color" type="button">' +data[i].Color+ '</button>';
                		rows += '</div>';
                        rows += '<br>';
                }  
                $('#summaryDet').html(rows);
            },
            error: function(data){
            	//alert("data in Error---"+data);
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
    if( $('#fName').val().length == 0){
        alert("Enter First Name !!");
        $( "#fName" ).focus();
        return false;
    }
    if( $('#lName').val().length == 0){
        alert("Enter Last Name !!");
        $( "#lName" ).focus();
        return false;
    }
    if( $('#address').val().length == 0){
        alert("Enter Address !!");
        $( "#address" ).focus();
        return false;
    }
    if( $('#zipCode').val().length == 0){
        alert("Enter Zip Code !!");
        $( "#zipCode" ).focus();
        return false;
    }
    if( $('#phone').val().length == 0){
        alert("Enter Phone Number !!");
        $( "#phone" ).focus();
        return false;
    }
    if( $('#color').val().length == 0){
        alert("Enter Color !!");
        $( "#color" ).focus();
        return false;
    }
}