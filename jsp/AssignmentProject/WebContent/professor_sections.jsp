<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="css/bootstrap-responsive.css" />
<title>
Sections
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
								Track courses, assignments and students here!
								</b>
								</p>
								</td>
							</tr>
						</table>
						
						</td>
						</tr>
				</table>
				<br><br>
				<table class="table table-striped" border="0" cellpadding="0" cellspacing="9" style="width:800px">
						<tr class="text-info">
							<th align="left"><b>
							Sections
							</b></th>
							<th align="left"><b>
							Number of Students
							</b></th>
							<th align="left"><b>
							Active Assignments
							</b></th>
							<th align="left"><b>
							Next Due Date
							</b></th>
							</tr>
						
							<% String[][] data = (String[][])request.getAttribute("sectionData");
							String[][] empty=(String[][])request.getAttribute("empty");
							for(int i=0; i<data.length; i++){
							if(data[i][0] != null){%>
							<tr>
								<td class="centertable normalfont">
								<% out.println("<a href=\"professor_section_servlet_path?id="+data[i][4]+"\">"+data[i][0]+"</a>");%>
								</td>
								<td class="centertable normalfont"><% out.println(data[i][1]); %></td>
								<td class="centertable normalfont"><% out.println(data[i][2]); %></td>
								<td class="centertable normalfont"><% if(data[i][2] != null) {
									out.println(data[i][3]); 
									}%></td>
							</tr>
							<%}
							}%>
							<% for(int i=0; i<empty.length; i++){
								if(empty[i][0] != null){%>
								<tr>
									<td class="centertable normalfont">
									<% out.println("<a href=\"professor_section_servlet_path?id="+empty[i][2]+"\">"+empty[i][0]+"</a>");%>
									</td>
									<td class="centertable normalfont"><% out.println(empty[i][1]); %></td>
									<td class="centertable normalfont"><% out.println("0"); %></td>
									<td class="centertable normalfont"><%%></td>
								</tr>
								<%}
								}%>
						
					</table>
			
			</div>
		</div>
</div>
</body>
</html>



<%-- 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>
Sections
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
<form name="otcForm" action="" method="post" enctype="">
<table border="0" cellpadding="0" cellspacing="9" width="100%" class="normalfont">
<!-- <table border="0" cellpadding="0" cellspacing="0" width="100%" align="center"> -->
<tr>
<td class="centertable bigfont"><b>Sections</b></td>
<td class="centertable bigfont"><b>Students</b></td>
<td class="centertable bigfont"><b>Active Assignments</b></td>
<td class="centertable bigfont"><b>Next Due Date</b></td>
</tr>
<tr>
<td>
&nbsp;
</td>
</tr>

<!-- jsp stuff here -->

<% String[][] data = (String[][])request.getAttribute("sectionData"); 
for(int i=0; i<data.length; i++){
if(data[i][0] != null){%>
<tr>
<td class="centertable normalfont">
<% out.println("<a href=\"professor_section_servlet_path?id="+data[i][4]+"\">"+data[i][0]+"</a>");%>
</td>
<td class="centertable normalfont"><% out.println(data[i][1]); %></td>
<td class="centertable normalfont"><% out.println(data[i][2]); %></td>
<td class="centertable normalfont"><% if(data[i][2] != null) {
	out.println(data[i][3]); 
	}%></td>
</tr>
<%}
}%>




<tr>
<td>
&nbsp;
</td>
</tr>

</table>

</body>
</html>
 --%>
