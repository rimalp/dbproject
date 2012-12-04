<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>
Account Settings
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
			<%@ include file="professor_left_links.jsp"%>
		</div>
			<div class="span10" style="padding-top: 50px; border: 2px; border-left-style: solid; border-color: #3399FF; margin: 10px; height: 100%; overflow:auto">
				<table>					
						<tr valign="middle">						
						<td valign="middle" class="lightbackgroundwithnormalfont" style="width:100%;">
						<table border="0" cellspacing="0" cellpadding="0" width="100%" class="smallfont" style="color: black;">
							<tr class="darkbackgroundwithhugefont" style="height: 35px">
								<td valign="middle" align="center">
								<p class="text-info" align="center">
								<b>							
									<br/><br/>
								</b>
								</p>
								</td>
							</tr>
						</table>
						
						</td>
						</tr>
				</table>



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
					
				<h4 class="darkbackgroundwithhugefont text-info">&nbsp;&nbsp;Change Password:</h5>



				

				<form class="form-horizontal" method="post" action="Account_Settings_Servlet">
				
				<div class="control-group">
					<label class="control-label" for="inputEmail">Old Password</label>
					<div class="controls">
						<input type="password" name="old" placeholder="Old Password">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputPassword">New Password</label>
					<div class="controls">
						<input type="password" name="new" placeholder="New Password">
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="inputPassword">Re-enter New Password:</label>
					<div class="controls">
						<input type="password" name="newConfirm" placeholder="New Password">
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						
						<button type="submit" class="btn btn-primary">Change</button>
					</div>
				</div>
				<!-- 
					Old Password: <input type="password" name="old"><br/>
					New Password: <input type="password" name="new"><br/>
					Confirm New Password: <input type="password" name="newConfirm"><br/> 
					<input class="btn btn-primary" type="Submit" name="Submit" value="Submit"> -->
				</form>			
			</div>
		</div>
</div>

 
 </body>
</html>