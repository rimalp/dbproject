
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>
Section 1
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


<!-- jsp here to get section name -->

<% String section=(String)request.getAttribute("section"); 
String[][] questions = (String[][])request.getAttribute("questions");
int qs = Integer.parseInt((String)request.getAttribute("numQs"));
int id=((Integer)request.getAttribute("ID")).intValue(); 
System.out.println("ASSIGNMENT ID SDFSDF SDG DS SDFSDF: "+id);%>
<b class="headerfont"><% out.println(section); %>: New Assignment</b><br/><br/>



<form method="post" action="make_assignment_servlet?questions=<% out.println(qs); %>&ID=<% out.println(id); %>">
<% for(int i=0; i<questions.length; i+=4){//assume 4 answers per question for now%>
<% out.println((i+1)+") "); %>Enter question: <% out.println("<input type=\"text\" value=\"" +questions[i][0]+"\" name=\"question\""+i+"\">"); %><br/>
Enter up to 4 potential answers:<br/>
a) <% out.println("<input type=\"text\"  value=\"" +questions[i+0][1]+"\"name=\"answer"+i+"a\">"); %><br/>
b) <% out.println("<input type=\"text\"  value=\"" +questions[i+1][1]+"\"name=\"answer"+i+"b\">"); %><br/>
c) <% out.println("<input type=\"text\"  value=\"" +questions[i+2][1]+"\"name=\"answer"+i+"c\">"); %><br/>
d) <% out.println("<input type=\"text\"  value=\"" +questions[i+3][1]+"\"name=\"answer"+i+"d\">"); %><br/>
Path of any images to be displayed with this problem: <input type="text"><br/>
<br/>
<table>
<tr>
<td>Mark any correct answers:</td>
<td>a)<input type="checkbox" name="correct" <%if(questions[i+0][2].equals("true")) out.println("checked=\"checked\""); %> value="1"></td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b)<input type="checkbox" name="correct" <%if(questions[i+1][2].equals("true")) out.println("checked=\"checked\""); %> value="2"></td>
</tr>
<tr>
<td></td>
<td>c)<input type="checkbox" name="correct"  <%if(questions[i+2][2].equals("true")) out.println("checked=\"checked\""); %> value="3"></td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;d)<input type="checkbox" name="correct"  <%if(questions[i+3][2].equals("true")) out.println("checked=\"checked\""); %> value="4"></td>
</tr>
</table>
<br/>
<%} %>
<input type="Submit" name="done" value="Save Assignment">
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