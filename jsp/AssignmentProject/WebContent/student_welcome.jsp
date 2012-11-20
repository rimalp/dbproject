<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>

<!-- This page has all the links related to students  such as sections, assignments, profile settings... --> --%>


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
<form name="otcForm" action="" method="post" enctype="">
<table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
<tr>
<td align="left" class="bigfont">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<b>
Sections
</b>
</td>
<td align="right" class="bigfont">
<b>
Active Assignments
</b>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</td>
</tr>
<tr>
<td>
&nbsp;
</td>
</tr>
</table>

<!-- get info to populate page here (???) 
http://www.coderanch.com/t/534514/JSP/java/populating-table-database-values


http://stackoverflow.com/questions/13410284/displaying-mysql-query-results-from-servlet-to-jsp
http://www.java-forums.org/java-servlet/12717-how-call-method-servet-using-jsp.html
http://stackoverflow.com/questions/2354293/calling-a-java-method-in-jsp
-->
<% String[][] data=(String[][])request.getAttribute("data");
//out.println("<h1>"+data[0][0]+"</h1>");%>

<table border="0" cellpadding="0" cellspacing="9" width="100%" class="normalfont">
<% for(int i=0; i<data.length; i++){ 
if(data[i][0] != null){%>
<tr class="normalfont">
<td>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<% out.print("<a href=\"student_section_servlet_path\">"+data[i][0]+"</a>");%>
</td>
<td align="right">
<% out.print(data[i][1]); %>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</td>
</tr>
<% }
} %>
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
</html>