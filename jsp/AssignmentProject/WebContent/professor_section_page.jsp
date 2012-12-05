<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="css/bootstrap-responsive.css" />
<% String[] info = (String[])request.getAttribute("info"); 
String[][] students=(String[][])request.getAttribute("students");%>
<title>
<% out.println(info[0]); %>
</title>
<style type="text/css">
<!--
.normalfont {font-size: 11pt; font-family: "Geneva, Arial, Helvetica, sans-serif";}
.bigfont {font-size: 14pt; font-family: "Geneva, Arial, Helvetica, sans-serif";}
.darkbackgroundwithnormalfont {background-image: url(../gifs/brushed_metal_dkblue.jpg); font-size: 11pt; font-family: "Geneva, Arial, Helvetica, sans-serif"; color: black; }
.darkbackgroundwithhugefont {background-image: url(../gifs/brushed_metal_dkblue.jpg); font-size: 15pt; font-family: "Geneva, Arial, Helvetica, sans-serif"; color: black; }
.normalfontwithred {font-size: 11pt; font-family: "Geneva, Arial, Helvetica, sans-serif"; color: red;}
.centertable {text-align: center; vertical-align: middle;}
a {text-decoration: none;}
-->
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
			<div class="span10" style="padding-top: 72px; border: 2px; border-left-style: solid; border-color: #3399FF; margin: 10px; height: 100%; overflow:auto">
				<table>					
						<tr valign="middle">						
						<td valign="middle" class="lightbackgroundwithnormalfont" style="width: 800px;">
						<table border="0" cellspacing="0" cellpadding="0" width="100%" class="smallfont" style="color: black;">
							<tr class="darkbackgroundwithhugefont" style="height: 35px">
								<td valign="middle" align="center"><p class="text-info">
								<b>							
								Course-Section: <% out.println(info[0]); %>
								</b>
								</p>
								</td>
							</tr>
						</table>
						
						</td>
						</tr>
				</table>
				<br><br>

			<h3 class="darkbackgroundwithhugefont text-info">Course Information:</h3>
			<address>
						<strong><% out.println(info[0]); %></strong><br>
						Room <% out.println(info[1]); %><br>
						<% out.println(info[2]); %><br>
						<% out.println(info[3]); %><br>								
			</address>				

			
			<form method="post" action="make_assignment_servlet?new=<% out.println(request.getAttribute("CRN")); %>">
				<input type="Submit" name="newAssignment" value="Add Assignment">
			</form>
								
			
				<h3 class="darkbackgroundwithhugefont text-info">Assignments:</h3>
				<table class="table table-striped" border="0" cellpadding="0" cellspacing="9" style="width:800px">
				<col width="25%">
  				<col width="60%">
  				<col width="15%">
						<tr class="text-info">
							<th class="bigfont" align="left"><b>
							Name
							</b></th>
							<th class="centerable bigfont" align="left"><b>
							Description
							</b></th>
							<th  class="centerable bigfont" align="left"><b>
							Deadline
							</b></th>
						</tr>
						
							<% String[][] assignments = (String[][])request.getAttribute("assignments");
							for(int i=0; i<assignments.length; i++){
							if(assignments[i][0] != null){%>
						<tr>
							<td>
							<% out.println("<a href=\"professor_assignment_servlet_path?id="+assignments[i][3]+"\">"+assignments[i][0]+"</a>"); %></td>
							<td class="centertable normalfont"><% out.println(assignments[i][1]); %></td>
							<td class="centertable normalfont"><% out.println(assignments[i][2]); %></td>
						</tr>
							<% }
							}%>
					
					</table>
					<br>
					<br>
				
				<h3 class="darkbackgroundwithhugefont text-info">Students:</h3>
				<ul>
				<% for(int i=0; i<students.length && students[i][0] != null; i++){ %>
				<li>
				<% out.println("<a href=\"student_grades_servlet?email="+students[i][2]+"&crn="+(String)request.getParameter("CRN")+"\">"+students[i][0]+" "+students[i][1]+"</a> ("+students[i][2]+")"); %><br/>
				</li>
				<%} %>
				</ul>
				
				<br/>
				<br/>
									
			</div>
		</div>
</div>

<%-- 
<html>
<head>
<% String[] info = (String[])request.getAttribute("info"); 
String[][] students=(String[][])request.getAttribute("students");%>
<title>
<% out.println(info[0]); %>
</title>
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
button, button:focus, button:active {
    /* NOTE Remove all decorations */
    border: none;
    display: inline;
    margin: 0em;
    padding: 0em;
    outline: none;
    outline-offset: 0em;
    /* NOTE Look like a link */
    background: none;
    color: blue;
    cursor: pointer;
    font: inherit;
    text-decoration: none;
}
-->
</style>
</head>
<!-- bgcolor="aqua" -->
<body class="bodybackground" style="margin-top: 0; margin-left: 0; margin-bottom: 4px; margin-right: 4px">
<table border="0" cellpadding="0" cellspacing="0" width="875px" style="height: 100%">
<tr valign="top">
<td style="width: 190px;" class="marginbackground">


<%@ include file="professor_left_links.jsp" %>


</td>
<td style="width: 3px; background-color: black;">&nbsp;</td>
<td valign="top" class="lightbackgroundwithnormalfont" style="width: 686px;">
<CENTER><table border="0" cellspacing="0" cellpadding="0" width="100%" class="smallfont" style="color: black;">
<tr class="darkbackgroundwithhugefont" style="height: 35px">
<td colspan="3" valign="middle" align="center">
<b>
Database Project
</b>
</td>
</tr>
<tr style="height: 5px">
<td colspan="3">
&nbsp;
</td>
</tr>
</table></CENTER>
<hr/>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<b class="headerfont"><% out.println(info[0]); %></b><br/><br/>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<b class="bigbigfont">Assignments</b>

<table border="0" cellpadding="0" cellspacing="9" width="100%" class="normalfont">
<tr>
<td class="bigfont">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Name</b></td>
<td class="centertable bigfont"><b>Description</b></td>
<td class="centertable bigfont"><b>Deadline</b></td>
</tr>

<% String[][] assignments = (String[][])request.getAttribute("assignments");
for(int i=0; i<assignments.length; i++){
if(assignments[i][0] != null){%>
<tr>
<td class="normalfont">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<% out.println("<a href=\"professor_assignment_servlet_path?id="+assignments[i][3]+"\">"+assignments[i][0]+"</a>"); %></td>
<td class="centertable normalfont"><% out.println(assignments[i][1]); %></td>
<td class="centertable normalfont"><% out.println(assignments[i][2]); %></td>
</tr>
<% }
}%>

</table>

<!-- option to add an assignment HERE -->
<br/>

<form method="post" action="make_assignment_servlet?new=<% out.println(request.getAttribute("CRN")); %>">
<% out.println("<form method=\"post\" action=\"make_assignment_servlet?new="+request.getAttribute("CRN")+"\">");
<input type="Submit" name="newAssignment" value="Add Assignment">
<!-- <button name="newAssignment" type="button">Add Assignment</button> -->
</form>

<% out.println("<a href=\"make_assignment_servlet?new="+request.getAttribute("CRN")+"\">Add Assignment</a>");
<br/>
<br/>




&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<b class="bigbigfont">Students:</b><br/><!-- course title, time, day, room -->

<!-- name, email.... click on name to see scores on assignments -->
<% for(int i=0; i<students.length && students[i][0] != null; i++){ %>
<% out.println("<a href=\"student_grades_servlet?email="+students[i][2]+"&crn="+(String)request.getParameter("CRN")+"\">"+students[i][0]+" "+students[i][1]+"</a> ("+students[i][2]+")"); %><br/>
<%} %>
<br/>
<br/>


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<b class="bigbigfont">Section Information:</b><br/><!-- course title, time, day, room -->
<table border="0" cellpadding="0" cellspacing="9" width="100%" class="normalfont">
<tr>
<td class="bigfont">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Course Title</b></td>
<td class="centertable bigfont"><b>Location</b></td>
<td class="centertable bigfont"><b>Days</b></td>
<td class="centertable bigfont"><b>Time</b></td>
</tr>
<tr>
<td class="normalfont">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<% out.println(info[0]); %></td>
<td class="centertable normalfont">Room <% out.println(info[1]); %></td>
<td class="centertable normalfont"><% out.println(info[2]); %></td>
<td class="centertable normalfont"><% out.println(info[3]); %></td>
</tr>
</table>




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
</htm --%>l>
