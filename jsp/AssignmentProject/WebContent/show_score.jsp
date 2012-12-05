<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@ page import="java.util.ArrayList, java.util.Arrays" %>
<% String assignment=(String)request.getAttribute("assignment");
int total=(Integer)request.getAttribute("total");
int correct=(Integer)request.getAttribute("correct");
%>
<head>
<title>
<% out.println(assignment); %>
</title>

<link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="css/bootstrap-responsive.css" />
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
			<%@ include file="student_left_links.jsp"%>
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
								&nbsp;&nbsp;You got <% out.println(correct); %> questions right out of <% out.println(total); %> total questions!</b><br>
								</p>
								<p class="text-error">
								<% String pastDeadline=(String)request.getAttribute("deadline"); 
								if(!pastDeadline.equals("")){ out.println("("+pastDeadline+")"); } %>
								</p>
								</td>
							</tr>
						</table>
						
						</td>
						</tr>
				</table>
				<br><br>			
				
			</div>
		</div>
</div>





<%-- <table border="0" cellpadding="0" cellspacing="0" width="875px" style="height: 100%">
<tr valign="top">
<td style="width: 190px;" class="marginbackground">


<%@ include file="student_left_links.jsp" %>


</td>
<td style="width: 3px; background-color: black;">&nbsp;</td>
<td valign="top" class="lightbackgroundwithnormalfont" style="width: 800px;">
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
<b class="headerfont"><% out.println(assignment); %></b><br/><br/>


<h1>You got <% out.println(correct); %> questions right out of <% out.println(total); %> total questions.</h1>
<br/>
<% String pastDeadline=(String)request.getAttribute("deadline"); 
if(!pastDeadline.equals("")){ out.println(pastDeadline); } %>


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
</table> --%>
</body>
</html>