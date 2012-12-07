<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<% String section=(String)request.getAttribute("section"); 
%>
				
<title>
<% out.println(section); %>: New Assignment
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
								New Assignment Creation:<% out.println(section); %><br/><br/>
								</b>
								</p>
								</td>
							</tr>
						</table>
						
						</td>
						</tr>
				</table>
				
				<% int qs = Integer.parseInt((String)request.getAttribute("numQs"));
				int id=((Integer)request.getAttribute("ID")).intValue(); %>
				
				<form class="well" method="post" action="make_assignment_servlet?questions=<% out.println(qs); %>&ID=<% out.println(id); %>">
				<% for(int i=0; i<qs; i++){//assume 4 answers per question for now%>
				<% out.println((i+1)+") "); %>Enter question: <% out.println("<input type=\"text\" name=\"question"+i+"\">"); %><br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Enter up to 4 potential answers:<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a) <% out.println("<input type=\"text\" name=\"answer"+i+"a\">"); %><br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b) <% out.println("<input type=\"text\" name=\"answer"+i+"b\">"); %><br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;c) <% out.println("<input type=\"text\" name=\"answer"+i+"c\">"); %><br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;d) <% out.println("<input type=\"text\" name=\"answer"+i+"d\">"); %><br/>
<!-- 				Path of any images to be displayed with this problem: <input type="text"><br/>
 -->				<br/>
				<table>
				<tr>
				<td style="padding-left: 10 px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mark any correct answers:</td>
				<td style="padding-left: 10 px">a)<input type="checkbox" name="correct" value="<% out.print(i+1); %>a"></td>
				<td style="padding-left: 10 px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b)<input type="checkbox" name="correct" value="<% out.print(i+1); %>b"></td>
				</tr>
				<tr>
				<td></td>
				<td style="padding-left: 10 px">c)<input type="checkbox" name="correct" value="<% out.print(i+1); %>c"></td>
				<td style="padding-left: 10 px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;d)<input type="checkbox" name="correct" value="<% out.print(i+1); %>d"></td>
				</tr>
				</table>
				<br>
				<hr>
				<br>
				<%} %>
				<input class="btn btn-primary" type="Submit" name="done" value="Save Assignment">
				</form>
					
			</div>
		</div>
</div>
</body>
</html>


