<!DOCTYPE html>
<html>
<head>
    <link href="http://getbootstrap.com/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://getbootstrap.com/examples/justified-nav/justified-nav.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <style type="text/css">
  		<%@include file="css/basicStyle.css" %>
	</style>
    <meta charset="ISO-8859-1">
    <title>MAX2 Inc. Coding Challenge</title>
</head>
<script>

</script>
<body onload="onrefresh();">
	<script type="text/javascript" src="js/critLoad.js"></script>
    <div class ="screenHeader">
		<h1 align="center">MAX2 Inc. Coding Challenge !!</h1>
	</div>
	<hr class="style-five">

    <br>
	<div class = "headerZone1">
           <label class="lbl"> PART - I </label>
    </div>
    <hr class="light" id="hr1">
    
    <div class = "headerZone">
        <button  class="hButn" id="labTest" value="Lab Test" type="button"><span>Question-1, 2 and 3:</span></button>
	</div>
    <div id="tagsZone" class="mainDetDiv" style="width: 70%;padding-left: 15px">
    	<p> ** For this section (1,2 and 3), No User Interaction is required.....</p>
    	<p> ** System initialization, loads the CSV file from resource folder. </p>
    	<p> ** Note: Later in Part III, User will get an opportunity to add data to CSV File.</p>
    	<p><b style="color: red;font-size: 14px"> ** IMP: System checks the input.csv file from Server resource folder : /WebContent/WEB-INF/dataSource/input.csv"</b></p>
    	<p> ** Since requirement specifications were open, I thought of reading input file from server specific folder would be the best implementation </p>
    	<p> ** [Second Option]: Other implementation would be to use the Upload File option of HTTP POST method [REST] </p>
   	</div>
    <hr class="light" id="hr1">
    
    <div class = "headerZone">
        <button  class="hButn" id="quest4Btn" value="Lab Test" type="button"><span>Question-4 [Non-JSON]: Print number of People belonging to a COLOR</span></button>
	</div>
    <div id="quest4Det" class="mainDetDiv" style="width: 70%;padding-left: 15px">
   	</div>
    <hr class="light" id="hr1">
   
    
    <div class = "headerZone">
        <button  class="hButn" id="quest5Btn" value="Lab Test" type="button"><span>Question-5 [Non-JSON]: Print People belonging to a COLOR</span></button>
	</div>
    <div id="quest5Det" class="mainDetDiv" style="width: 70%;padding-left: 15px">
   	</div>
    <hr class="light" id="hr1">
    
    <div class = "headerZone">
        <button  class="hButn" id="quest6Btn" value="Lab Test" type="button"><span>Question-6 [JSON]: Print Number of People belonging to a COLOR</span></button>
	</div>
    <div id="quest6Det" class="mainDetDiv" style="width: 70%;padding-left: 15px"></div>
    <hr class="light" id="hr1">
    
    <div class = "headerZone">
        <button  class="hButn" id="quest7Btn" value="Lab Test" type="button"><span>Question-7 [JSON]: Print People belonging to a COLOR</span></button>
	</div>
    <div id="quest7Det" class="mainDetDiv" style="width: 70%;padding-left: 15px">
   	</div>
    <hr class="light" id="hr1">
    
    <div class = "headerZone1">
           <label class="lbl"> PART - II </label>
    </div>
   	
   	<div class = "headerZone1">
            <div ><label class="lbl" style="color: black; font-size:15px"> Latitude :	</label><input id="lat" type="text" name="lat"></div>
            <div ><label class="lbl" style="color: black; font-size:15px"> Longitude :	</label><input id="lon"  type="text" name="lon"></div>
    </div>
    
    <div class = "headerZone">
        <button  class="hButn" id="partIIBtn" value="Lab Test" type="button"><span>PART-II : Consume 3rd party APIs.</span></button>
	</div>
	
    <div id="partIIDet" class="mainDetDiv" style="width: 70%;padding-left: 15px">
   	</div>
    <hr class="light" id="hr1">
    
    <div>
    <div class = "headerZone1">
           <label class="lbl"> PART - III </label>
    </div>
	<div >
	    <table  class="tableClass" style="width: 90%">
	    	<tr >
	            	<td ><label class="lbl" style="color: black; font-size:15px"> First Name :	</label></td>
	            	<td ><input id="fName" type="text" name="fName"></td>
	            	<td ><label class="lbl" style="color: black; font-size:15px"> Last Name :	</label></td>
	            	<td ><input id="lName" type="text" name="lName"></td>
	        </tr>
	        <tr >
	            	<td ><label class="lbl" style="color: black; font-size:15px"> Address :	</label></td>
	            	<td ><input id="address" type="text" name="address"></td>
	            	<td ><label class="lbl" style="color: black; font-size:15px"> Zip Code :	</label></td>
	            	<td ><input id="zipCode" type="text" name="zipCode"></td>
	        </tr>
	        <tr >
	            	<td ><label class="lbl" style="color: black; font-size:15px"> Phone Number :	</label></td>
	            	<td ><input id="phone" type="text" name="phone"></td>
	            	<td ><label class="lbl" style="color: black; font-size:15px"> Color :	</label></td>
	            	<td ><input id="color" type="text" name="color"></td>
	        </tr>
		</table>
</div>
<div class = "headerZone1">
    </div>
    <div class = "headerZone">
        <button  class="hButn" id="partIIIBtn" value="Lab Test" type="button"><span>PART-III : Deploy Sources on Heroku and Data Capturing.</span></button>
	</div>    
    
    <div id="partIIIDet" class="mainDetDiv" style="width: 70%;padding-left: 15px">
   	</div>
    <hr class="light" id="hr1">
    
    
</body>
</html>