<%@page import="com.apptrove.ledgerly.admin.models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="../resources/bootstrap-4.5.3-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
<script src="../resources/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Dashboard Page</title>
</head>
<body>
	<%
	if (session.getAttribute("user") != null) {
		User user = (User) session.getAttribute("user");
		String username = user.getUsername();
<<<<<<< HEAD
	%>
	<div>
		<nav class="navbar navbar-expand-lg navbar-light custom-dashboard-navbar">
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarTogglerDemo01"
				aria-controls="navbarTogglerDemo01" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarTogglerDemo01">
				<a class="navbar-brand" href="#">Hidden brand</a>
				<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
					<li class="nav-item active"><a class="nav-link" href="#">Home
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
					<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a>
					</li>
				</ul>
				<button class="btn btn-outline-success my-2 my-sm-0 me-auto" type="submit">Search</button>
			</div>
		</nav>
	</div>
	<%
=======
%>
	<div></div>
<%
>>>>>>> c929b0b649851786585b3649951a65667958449c
	} else {
	%>
	<a href="../index.jsp">Please login again</a>
	<%
	}
	%>
</body>
</html>