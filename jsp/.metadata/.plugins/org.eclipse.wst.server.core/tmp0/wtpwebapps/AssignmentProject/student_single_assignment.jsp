<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@ page import="java.util.ArrayList, java.util.Arrays" %>
<% String[][] questions= (String[][])request.getAttribute("questions");
ArrayList<String> prevAnswers=new ArrayList(Arrays.asList((String[])request.getAttribute("answersGiven")));
String assignment=(String)request.getAttribute("assignment");
String section = (String)request.getAttribute("section");
%>
<head>
<title>
<% out.println(assignment); %>
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
<b class="headerfont"><% out.println(section+": "+assignment); %></b><br/><br/>

<% for(int i=0; i<questions.length; i++){
	if(questions[i][0] != null){%>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<p><% out.println(questions[i][0]); %></p>
<form>
<% int num=1; int q=0;
//if questions null, download link?
while(i<questions.length && questions[i][0].equals(questions[i+1][0])) {%>
<% String c="";
//if student has selected this answer before, check the box
if(prevAnswers.contains(questions[i][3])){ c="checked"; }
out.println("<input type=\"radio\" name=\"answer"+q+"\" value=\""+questions[i][3]+"\" "+c+">"+questions[i][1]); %><br/>
<% num++;
i++;
}
q++;
%>
</form>
<%
}
}%>

<input type="Submit" name="Save" value="Save"> <input type="Submit" name="Submit" value="Submit">
<!-- do we want a save button ?? (maybe later if time... need to change db)
when submited, update the assigned table in the db -->


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