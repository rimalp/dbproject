
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>
Assignments
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


<h1>Active Assignments</h1>

<form name="otcForm" action="" method="post" enctype="">
<table border="0" cellpadding="0" cellspacing="9" width="100%" class="normalfont">
<!-- <table border="0" cellpadding="0" cellspacing="0" width="100%" align="center"> -->
<tr>
<td class="centertable bigfont"><b>Section</b></td>
<td class="centertable bigfont"><b>Description</b></td>
<td class="centertable bigfont"><b>Deadline</b></td>
<td class="centertable bigfont"><b>Completed</b></td>
</tr>
<tr>
<td>
&nbsp;
</td>
</tr>

<!-- jsp here -->


<!-- separate into active and old assignments????? -->


<% String[][] data = (String[][])request.getAttribute("assignmentCurrentData");
for(int i=0; i<data.length; i++){
if(data[i][0] != null) {%>
<tr>
<td class="centertable normalfont">
<% out.println("<a href=\"professor_assignment_servlet_path?id="+data[i][5]+"\">"+data[i][0]+"</a>"); %>
</td>
<td class="centertable normalfont"><% out.println(data[i][1]); %></td>
<td class="centertable normalfont"><% out.println(data[i][2]); %></td>
<td class="centertable normalfont"><% out.println(data[i][3]+"/"+data[i][4]); %></td>
</tr>
<%}
}%>


<tr>
<td>
&nbsp;
</td>
</tr>

</table>


<h1>Old Assignments</h1>

<table border="0" cellpadding="0" cellspacing="9" width="100%" class="normalfont">
<!-- <table border="0" cellpadding="0" cellspacing="0" width="100%" align="center"> -->
<tr>
<td class="centertable bigfont"><b>Section</b></td>
<td class="centertable bigfont"><b>Description</b></td>
<td class="centertable bigfont"><b>Deadline</b></td>
<td class="centertable bigfont"><b>Completed</b></td>
</tr>
<tr>
<td>
&nbsp;
</td>
</tr>

<% data = (String[][])request.getAttribute("assignmentOldData");
for(int i=0; i<data.length; i++){
if(data[i][0] != null) {%>
<tr>
<td class="centertable normalfont">
<% out.println("<a href=\"professor_assignment_servlet_path?id="+data[i][5]+"\">"+data[i][0]+"</a>"); %>
</td>
<td class="centertable normalfont"><% out.println(data[i][1]); %></td>
<td class="centertable normalfont"><% out.println(data[i][2]); %></td>
<td class="centertable normalfont"><% out.println(data[i][3]+"/"+data[i][4]); %></td>
</tr>
<%}
}%>

<tr>
<td>
&nbsp;
</td>
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
</html>