<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<% String section=(String)request.getAttribute("section");
String crn=(String)request.getAttribute("CRN"); 
String error=(String)request.getAttribute("error");%>
				
<title>
<% out.println(section); %>: New Assignment
</title>

<link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="css/bootstrap-responsive.css" />

<style type="text/css">
<!--
.normalfont {font-size: 11pt; font-family: "Geneva, Arial, Helvetica, sans-serif";}
.bigfont {font-size: 14pt; font-family: "Geneva, Arial, Helvetica, sans-serif";}
.bigbigfont {font-size: 16pt; font-family: "Geneva, Arial, Helvetica, sans-serif";}
.darkbackgroundwithnormalfont {background-image: url(../gifs/brushed_metal_dkblue.jpg); font-size: 11pt; font-family: "Geneva, Arial, Helvetica, sans-serif"; color: black; }
.darkbackgroundwithhugefont {background-image: url(../gifs/brushed_metal_dkblue.jpg); font-size: 15pt; font-family: "Geneva, Arial, Helvetica, sans-serif"; color: black; }
.normalfontwithred {font-size: 11pt; font-family: "Geneva, Arial, Helvetica, sans-serif"; color: red;}
.centertable {text-align: center; vertical-align: middle;}
a {text-decoration: none;}
.headerfont {font-size: 18pt; font-family: "Geneva, Arial, Helvetica, sans-serif";}
</style>
</head>
<body class="bodybackground" style="margin-top: 0; margin-left: 0; margin-bottom: 4px; margin-right: 4px">


<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container">
        <p class="brand" align="left">The Assignment Project</p>
        <div class="nav-collapse">
           <p align="right"> Signed in as <% String e = (String)request.getSession().getAttribute("name"); out.println(e); %></p>
           <p class="text-info" align="right">
           <a class="text-info" href="loginpath?id=logout_link">Logout</a>
           </p>
        </div>
      </div>
    </div>
  </div>
  
  
  <div class="container-fluid span15">

	<div class="row-fluid">
		<div class="span2">
			<%@ include file="professor_left_links.jsp"%>
		</div>
			<div class="span10" style="padding-top: 100px; border: 2px; border-left-style: solid; border-color: #3399FF; margin: 10px; height: 100%; overflow:auto">
				<table>					
						<tr valign="middle">						
						<td valign="middle" class="lightbackgroundwithnormalfont" style="width:100%;">
						<table border="0" cellspacing="0" cellpadding="0" width="100%" class="smallfont" style="color: black;">
							<tr class="darkbackgroundwithhugefont" style="height: 35px">
								<td valign="middle" align="center">
								<p class="text-info" align="center">
								<b>							
								New Assignment Creation:<% out.println(section); %><br/><br/>
								</b>
								</p>
								</td>
							</tr>
						</table>
						
						</td>
						</tr>
				</table>
				
				
				<% if(error != null){ out.println("<font color=\"red\">"+error+"</font>"); } %>
				
				<form class="well span8" method="post" action="make_assignment_servlet?id=<% out.println(crn); %>">
				
				<div class="control-group">
					<label class="control-label">Assignment Name:</label>
					<input type="text" name="name" placeholder="Name">
				</div>
				<div class="control-group">
					<label class="control-label"> Deadline<b class="text-error">(YYYY/MM/DD):</b></label>
					<input type="text" placeholder="Deadline" name="deadline">
				</div>
				
				<div>
					<label class="control-label">Description:</label>
					<textarea name="description" placeholder="About the Assignment"></textarea>
				</div>
				
				<div class="control-group">
					<label class="control-label">Number of Questions:</label>
					<input type="text" name="numQuestions" placeholder="10">
				</div>
				
				<div class="control-group">
					<button type="submit" class="btn btn-primary">Save Assignment</button>
				</div>
					
				</form>
			</div>
		</div>
</div>
</body>
</html>
			

<%-- <!-- jsp here to get section name -->
<% String section=(String)request.getAttribute("section");
String crn=(String)request.getAttribute("CRN"); 
String error=(String)request.getAttribute("error");%>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<b class="headerfont"><% out.println(section); %>: New Assignment</b><br/><br/>

<% if(error != null){ out.println("<font color=\"red\">"+error+"</font>"); } %>

<form method="post" action="make_assignment_servlet?id=<% out.println(crn); %>">
Enter a name for the assignment:<input type="text" name="name"><br/>
Enter Deadline (YYYY/MM/DD):<input type="text" name="deadline"><br/>
Enter a description of the assignment:<br/>
<textarea name="description"></textarea><br/>
Number of questions:<input type="text" size="3" name="numQuestions"><br/>

<input type="Submit" name="id" value="Save Assignment">

</form>

<table border="0" cellspacing="0" cellpadding="2" width="100%">
<tr style="height: 5px">
<td colspan="3">
&nbsp;
</td>
</tr>
</table>
</td>
<td style="width: 3px; background-color: black;">&nbsp;</td>
<td style="width: 190px" class="marginbackground">
&nbsp;
</td>
</tr>
</table>
</body>
</html>
 --%>