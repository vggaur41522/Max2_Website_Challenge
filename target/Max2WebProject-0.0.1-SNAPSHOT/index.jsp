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
    <div id="tagsZone" class="mainDetDiv" style="width: 70%;padding-left: 15px"><p> ** For these question, No User Interaction is required.....</p>
    	<p> ** System initialization loads the CSV file from resource folder. </p>
    	<p> ** Note: Later in Part III, User will get an opportunity to add data to CSV File.</p>
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
    
    <div id="selectedNode" class="selectedNode">
        <textarea hidden id="targetText" style="width: 90%; height:90%; color: gray"></textarea>
    </div>
    
</body>
</html>