<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>
Home
</title>
<link href="css/bootstrap.css" rel="stylesheet">
<style type="text/css">
.normalfont {font-size: 11pt; font-family: "Geneva, Arial, Helvetica, sans-serif";}
.bigfont {font-size: 14pt; font-family: "Geneva, Arial, Helvetica, sans-serif";}
.darkbackgroundwithnormalfont {background-image: url(../gifs/brushed_metal_dkblue.jpg); font-size: 11pt; font-family: "Geneva, Arial, Helvetica, sans-serif"; color: black; }
.darkbackgroundwithhugefont {background-image: url(../gifs/brushed_metal_dkblue.jpg); font-size: 15pt; font-family: "Geneva, Arial, Helvetica, sans-serif"; color: black; }
.normalfontwithred {font-size: 11pt; font-family: "Geneva, Arial, Helvetica, sans-serif"; color: red;}
a {text-decoration: none;}
</style>
</head>
<!-- bgcolor="aqua" -->
<body class="bodybackground"style="margin-top: 0; margin-left: 0; margin-bottom: 8px; margin-right: 8px">

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
			<div class="span10" style="padding-top:75px; border:2px; border-left-style:solid; border-color:#3399FF; margin:10px; height:100%; overflow:auto">
					<table>					
						<tr valign="middle">						
						<td valign="middle" class="lightbackgroundwithnormalfont" style="width: 800px;">
						<table border="0" cellspacing="0" cellpadding="0" style="width:800px" class="smallfont" style="color: black;">
							<tr class="darkbackgroundwithhugefont" style="height: 35px">
								<td valign="middle" align="center"><p class="text-info">
								<b>							
								Welcome to Online Assignment!
								</b>
								</p>
								</td>
							</tr>
						</table>
						
						</td>
						</tr>
					</table>
					<br><br>
						
					<% String[][] data=(String[][])request.getAttribute("data");%>
						
					<table class="table table-striped" border="0" cellpadding="0" cellspacing="9" style="width:75%">
						<tr class="text-info">
							<th align="left"><b>
							Sections
							</b></th>
							<th align="left"><b>
							Active Assignments
							</b></th>
							<th align="left"><b>
							Deadline
							</b></th>
						</tr>
						
						<% for(int i=0; i<data.length; i++){ 
						if(data[i][0] != null){%>
						<tr class="normalfont">
							<td>
							<% out.print("<a href=\"professor_section_servlet_path?id="+data[i][3]+"\">"+data[i][0]+"</a>");%>
							</td>
							<td align="center" class="centerable normalfont">
							<% out.print(data[i][1]); %>
							</td>
							<td align="right" class="centerable">
							<% out.print(data[i][2]); %>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<%}
						}%>
					</table>
			
			</div>
		</div>
	</div>



<br />


<br />
</body>
</html>
















<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
<!--
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html> -->

<%-- 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>
Home
</title>
<style type="text/css">
<!--
.normalfont {font-size: 11pt; font-family: "Geneva, Arial, Helvetica, sans-serif";}
.bigfont {font-size: 14pt; font-family: "Geneva, Arial, Helvetica, sans-serif";}
.darkbackgroundwithnormalfont {background-image: url(../gifs/brushed_metal_dkblue.jpg); font-size: 11pt; font-family: "Geneva, Arial, Helvetica, sans-serif"; color: black; }
.darkbackgroundwithhugefont {background-image: url(../gifs/brushed_metal_dkblue.jpg); font-size: 15pt; font-family: "Geneva, Arial, Helvetica, sans-serif"; color: black; }
.normalfontwithred {font-size: 11pt; font-family: "Geneva, Arial, Helvetica, sans-serif"; color: red;}
a {text-decoration: none;}
.centertable {text-align: center; vertical-align: middle;}
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
<tr>
<td align="left" class="bigfont">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<b>
Sections
</b>
</td>
<td align="center" class="bigfont">
<b>
Active Assignments
</b>
</td>
<td align="right" class="bigfont">
<b>
Number of Students
</b>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</td>
</tr>
<tr>
<td>
&nbsp;
</td>
</tr>


<!-- jsp here -->

<% String[][] data=(String[][])request.getAttribute("data"); %>

<% for(int i=0; i<data.length; i++){ 
if(data[i][0] != null){%>
<tr class="normalfont">
<td>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<% out.print("<a href=\"professor_section_servlet_path?id="+data[i][3]+"\">"+data[i][0]+"</a>");%>
</td>
<td align="center" class="centerable normalfont">
<% out.print(data[i][1]); %>
</td>
<td align="right" class="centerable">
<% out.print(data[i][2]); %>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</td>
</tr>
<%}
}%>





</table>
<br />


<br />




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
</html>--%>