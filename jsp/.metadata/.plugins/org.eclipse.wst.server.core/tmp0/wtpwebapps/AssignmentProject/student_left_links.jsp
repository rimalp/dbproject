<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="0" cellpadding="0" cellspacing="0" width="100%" class="normalfont" style="height: 100%" >
<tr style="height: 61px; border-bottom-width: 0px; " valign="top">
<td>

</td>
</tr>
<tr style="height: 10px;">
<td>
&nbsp;
</td>
</tr>

<tr class="darkbackgroundwithnormalfont" style="height: 20px;">
<td align="center">
<b>
<% String e = (String)request.getSession().getAttribute("name");
out.println(e); %>
</b>
</td>
</tr>
<tr style="height: 10px;">
<td>
&nbsp;
</td>
</tr>

<tr style="height: 35px;">
<td>
&nbsp;&nbsp;&nbsp;&nbsp;&#8226;&nbsp;
<a href="loginpath?id=student_home_link" style="color: darkblue;">
Home Page
</a>
</td>
</tr>

<tr style="height: 35px;">
<td>
&nbsp;&nbsp;&nbsp;&nbsp;&#8226;&nbsp;
<a href="student_welcome_path?id=sections" style="color: darkblue;">
Sections
</a>
</td>
</tr>

<tr style="height: 35px;">
<td>
&nbsp;&nbsp;&nbsp;&nbsp;&#8226;&nbsp;
<a href="student_welcome_path?id=assignments" style="color: darkblue;">
Assignments
</a>
</td>
</tr>

<tr style="height: 35px;">
<td>
&nbsp;&nbsp;&nbsp;&nbsp;&#8226;&nbsp;
<a href="account_settings.jsp" style="color: darkblue;">
Account Settings
</a>
</td>
</tr>

<tr style="height: 35px;">
<td>
&nbsp;&nbsp;&nbsp;&nbsp;&#8226;&nbsp;
<a href="loginpath?id=logout_link" style="color: darkblue;">
Log Out
</a>
</td>
</tr>

<!-- more links here? -->

<tr style="height: 10px;">
<td>
&nbsp;
</td>
</tr>
<tr>
<td>
&nbsp;
</td>
</tr>

<tr style="height: 35px;">
<td>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="" style="color: darkblue;">
Help
</a>
</td>
</tr>

</table>


</body>
</html>