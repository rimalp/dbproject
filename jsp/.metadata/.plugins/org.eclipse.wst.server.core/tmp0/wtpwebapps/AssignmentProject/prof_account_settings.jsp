<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>
Account Settings
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

<% String v = request.getParameter("valid");
if(v!=null && v.equals("false"))
{
	out.println("<font color=\"red\">Your password was not changed as the values you entered in the New Password and Confirm Password fields did not match </font>");
}
else if(v!=null && v.equals("oldFalse"))
{
	out.println("<font color=\"red\">Your password was not changed because the old password you entered is incorrect</font>");
}
	%>
<h1>Change Password</h1>
<form name="input" method="post" action="Account_Settings_Servlet">
Old Password: <input type="password" name="old"><br/>
New Password: <input type="password" name="new"><br/>
Confirm New Password: <input type="password" name="newConfirm"><br/>
<input type="submit" value="Submit">
</form>

<!-- 
<br/>

<h1>Add Students to a Section</h1>



<br/>


<h1>Add Sections</h1>


<br/> 
-->


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