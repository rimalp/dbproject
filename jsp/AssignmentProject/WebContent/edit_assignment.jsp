<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<% String section=(String)request.getAttribute("section"); 
%>
				
<title>
<% out.println(section); %>: Edit Assignment
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
								Edit Assignment:<% out.println(section); %><br/><br/>
								</b>
								</p>
								</td>
							</tr>
						</table>
						
						</td>
						</tr>
				</table>
				
				<%String[][] questions = (String[][])request.getAttribute("questions");
				int id=((Integer)request.getAttribute("ID")).intValue(); 
				%>
				
				<form class="well" method="post" action="make_assignment_servlet?ID=<% out.println(id); %>&numQuestions=<% out.println(questions.length); %>">
				<% int i;
				for(i=0; i<questions.length && questions[i][0]!=null; i+=4){//assume 4 answers per question for now%>
				<% out.println((i/4+1)+") "); %>Enter question: <% out.println("<input class=\"span9\" type=\"text\"  a value=\"" +questions[i][0]+"\" name=\"question"+(i/4)+"\">"); %> &nbsp;&nbsp;&nbsp;&nbsp;<text class="text-error">Delete&nbsp;&nbsp;</text><input type="checkbox" name="delete" value="<% out.println(questions[i][0]); %>"><br/>
				Enter up to 4 potential answers:<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a) <% out.println("<input  class=\"span7\" type=\"text\"  value=\"" +questions[i][1]+"\"name=\"answer"+(i/4)+"a\">"); %><br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b) <% out.println("<input  class=\"span7\" type=\"text\"  value=\"" +questions[i+1][1]+"\"name=\"answer"+(i/4)+"b\">"); %><br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;c) <% out.println("<input  class=\"span7\" type=\"text\"  value=\"" +questions[i+2][1]+"\"name=\"answer"+(i/4)+"c\">"); %><br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;d) <% out.println("<input  class=\"span7\" type=\"text\"  value=\"" +questions[i+3][1]+"\"name=\"answer"+(i/4)+"d\">"); %><br/>
<!-- 				Path of any images to be displayed with this problem: <input type="text"><br/>
 -->				
 				<br/>
				<table>
				<tr>
				<td&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>Mark any correct answers:</td>
				<td>a)<input type="checkbox" name="correct" <%if(questions[i][2].equals("true")) out.println("checked=\"checked\""); %> value="<% out.print((i/4+1)); %>a"></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b)<input type="checkbox" name="correct" <%if(questions[i+1][2].equals("true")) out.println("checked=\"checked\""); %> value="<% out.print((i/4+1)); %>b"></td>
				</tr>
				<tr>
				<td></td>
				<td>c)<input type="checkbox" name="correct"  <%if(questions[i+2][2].equals("true")) out.println("checked=\"checked\""); %> value="<% out.print((i/4+1)); %>c"></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;d)<input type="checkbox" name="correct"  <%if(questions[i+3][2].equals("true")) out.println("checked=\"checked\""); %> value="<% out.print((i/4+1)); %>d"></td>
				</tr>
				</table>
				<br>
				<hr>
				<br>
				<%} %>
				
				
				<input class="btn btn-primary" type="Submit" name="done" <% if(i==0) out.println("disabled"); %> value="Save Assignment">
				</form>
				

					
								
				
				<%-- <b class="headerfont"><% out.println(section); %>: New Assignment</b><br/><br/>
				
				
				<!-- "make_assignment_servlet?ID=<% out.println(id); %> -->
				<form method="post" action="make_assignment_servlet?ID=<% out.println(id); %>&numQuestions=<% out.println(questions.length); %>">
				<% int i; 
				for(i=0; i<questions.length && questions[i][0]!=null; i+=4){//assume 4 answers per question for now%>
				<% out.println((i+1)+") "); %>Enter question: <% out.println("<input type=\"text\" value=\"" +questions[i][0]+"\" name=\"question"+i+"\">"); %><input type="checkbox" name="delete" value="<% out.println(questions[i][0]); %>"><br/>
				Enter up to 4 potential answers:<br/>
				a) <% out.println("<input type=\"text\"  value=\"" +questions[i][1]+"\"name=\"answer"+i+"a\">"); %><br/>
				b) <% out.println("<input type=\"text\"  value=\"" +questions[i+1][1]+"\"name=\"answer"+i+"b\">"); %><br/>
				c) <% out.println("<input type=\"text\"  value=\"" +questions[i+2][1]+"\"name=\"answer"+i+"c\">"); %><br/>
				d) <% out.println("<input type=\"text\"  value=\"" +questions[i+3][1]+"\"name=\"answer"+i+"d\">"); %><br/>
				Path of any images to be displayed with this problem: <input type="text"><br/>
				<br/>
				<table>
				<tr>
				<td>Mark any correct answers:</td>
				<td>a)<input type="checkbox" name="correct" <%if(questions[i][2].equals("true")) out.println("checked=\"checked\""); %> value="1"></td>
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
				<input type="Submit" name="done" <% if(i==0) out.println("disabled"); %> value="Save Assignment">
				</form> --%>
				
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
//int qs = Integer.parseInt((String)request.getAttribute("numQs"));
int id=((Integer)request.getAttribute("ID")).intValue(); 
%>
<b class="headerfont"><% out.println(section); %>: New Assignment</b><br/><br/>


<!-- "make_assignment_servlet?ID=<% out.println(id); %> -->
<form method="post" action="make_assignment_servlet?ID=<% out.println(id); %>&numQuestions=<% out.println(questions.length); %>">
<% int i; 
for(i=0; i<questions.length && questions[i][0]!=null; i+=4){//assume 4 answers per question for now%>
<% out.println((i+1)+") "); %>Enter question: <% out.println("<input type=\"text\" value=\"" +questions[i][0]+"\" name=\"question"+i+"\">"); %><input type="checkbox" name="delete" value="<% out.println(questions[i][0]); %>"><br/>
Enter up to 4 potential answers:<br/>
a) <% out.println("<input type=\"text\"  value=\"" +questions[i][1]+"\"name=\"answer"+i+"a\">"); %><br/>
b) <% out.println("<input type=\"text\"  value=\"" +questions[i+1][1]+"\"name=\"answer"+i+"b\">"); %><br/>
c) <% out.println("<input type=\"text\"  value=\"" +questions[i+2][1]+"\"name=\"answer"+i+"c\">"); %><br/>
d) <% out.println("<input type=\"text\"  value=\"" +questions[i+3][1]+"\"name=\"answer"+i+"d\">"); %><br/>
Path of any images to be displayed with this problem: <input type="text"><br/>
<br/>
<table>
<tr>
<td>Mark any correct answers:</td>
<td>a)<input type="checkbox" name="correct" <%if(questions[i][2].equals("true")) out.println("checked=\"checked\""); %> value="1"></td>
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
<input type="Submit" name="done" <% if(i==0) out.println("disabled"); %> value="Save Assignment">
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
</html> --%>