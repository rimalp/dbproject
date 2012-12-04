<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<% String[] info = (String[])request.getAttribute("info"); %>
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


<%@ include file="student_left_links.jsp" %>


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
<% out.println("<a href=\"student_assignment_servlet_path?id="+assignments[i][3]+"\">"+assignments[i][0]+"</a>"); %></td>
<td class="centertable normalfont"><% out.println(assignments[i][1]); %></td>
<td class="centertable normalfont"><% out.println(assignments[i][2]); %></td>
</tr>
<% }
}%>


<br/>

<tr>
<td>Assigment Name </td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;Grade(%) </td>
</tr>
<%String[][] grades = (String[][])request.getAttribute("assignments_grades"); %>
<%for(int r=0; r<grades.length && grades[r][0] != null; r++) { %>
<tr>
<td><%out.print(grades[r][0]); %></td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;<%out.print(Double.parseDouble(grades[r][1])*100+"%"); %> </td>
</tr>
<%} %>

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
</html>