<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ include file="include/head-min.jsp"%>
<body>
	<div class="navbar">
		<div class="navbar-inner">
			<a class="brand" href="#">Content Management</a>
		</div>
	</div>
	<div id="maincontainer" class="container-fluid">
		<form name="f" action="${contextpath}/j_spring_security_check" method="POST">
			<label for="username">Username</label> 
			<input id="username" type="text" name="j_username" placeholder="Username">
			<label for="password">Password</label> 
			<input id="password" type="password" name="j_password" placeholder="Password">
			<input class="btn btn-primary" name="submit" type="submit" value="Login">
		</form>	
		<p>
		<div id="ajaxerror" style="display: none">
			<div id="errormessage"></div>
			<button id="errorbutton" class="btn btn-danger">Ok...</button>
		</div>

	</div>
</body>
</html>



