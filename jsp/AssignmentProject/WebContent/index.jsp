<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
<link type="text/css" rel="stylesheet"
	href="css/bootstrap-responsive.css" />


</head>
<body>


	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span2">
				<form method="post" action="loginpath">
					<input type="submit" name="show" value="Show First 5 Users" />
				</form>
			</div>
			<div class="span10">
				<div class="modal" align="center">

					<form class="well" method="post" action="loginpath">
						<h3 class="text-info">Login to Online Assignments</h3>
						Username: <input class="span4" type="text" name="username" /><br />
						Password: <input class="span4" type="password" name="password" /><br />
						<input class="btn btn-primary" type="submit" name="login"
							value="Login" /> <input class="btn btn-primary" type="submit"
							name="cancel" value="Cancel" />

					</form>
				</div>
			</div>
		</div>
	</div>



	begin test portion
	<br />
	<br />
	
<!-- to insert fake data into db -->
<form method="post" action="loginpath">
<input type="submit" name="insert" value="Insert" />
</form>


</body>
</html>
