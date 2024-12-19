<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="com.apptrove.ledgerly.admin.models.MenuItemMst"%>
<%@page import="com.apptrove.ledgerly.admin.models.MenuMst"%>
<%@page import="java.util.List"%>
<%@page import="com.apptrove.ledgerly.admin.models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<link href="../resources/bootstrap-4.5.3-dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="./style/dashboard.css" type="text/css" rel="stylesheet">
<script src="../resources/jquery.min.js" type="text/javascript"></script>
<script src="../resources/popper.min.js" type="text/javascript"></script>
<script src="../resources/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
<script src="./scripts/dashboard.js"></script>
<meta charset="UTF-8">
<title>Dashboard Page</title>
</head>

<body>
	<%
	if (session.getAttribute("user") != null && session.getAttribute("menuHeaders") != null
			&& session.getAttribute("menuOptions") != null) {
		User user = (User) session.getAttribute("user");
		String username = user.getUsername();
	%>
	<div>
		<nav class="navbar navbar-expand-lg navbar-light custom-dashboard-navbar">
			<button class="navbar-toggler" id="custom-dashboard-navbar-toggler-button" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarTogglerDemo01">
				<a class="navbar-brand custom-dashboard-navbar-brand" href="#">Ledgerly</a>
			</div>
			<div class="dropdown">
				<button class="btn btn-outline-light dropdown-toggle me-auto custom-navbar-user-button" data-toggle="dropdown" id="navbarCustomUserButton" type="button">Welcome, <%= username %></button>
				<div class="dropdown-menu dropdown-menu-right custom-dashboard-navbar-dropdown-menu" aria-labelledby="navbarCustomUserButton">
					<a class="dropdown-item">Username: <%= user.getFirstName() %> <%= user.getLastName() %></a>
					<a class="dropdown-item">Role: </a>
					<div class="dropdown-divider"></div>
                	<a class="dropdown-item text-danger" href="logoutAction">Logout</a>
				</div>
			</div>
		</nav>
		<div class="custom-dashboard-container">
			<div class="card custom-dashboard-card">
				<div class="custom-dashboard-nav-buttons-div" id="dashboardNavButtonDiv">
					<div class="nav nav-pills btn btn-grp custom-dashboard-nav-buttons-div-pills" id="dashboardNavButtonTab">
						
					</div>
				</div>
				<div class="custom-dashboard-main-content" id="dashboardMainContent">
					<!-- Main content area for the selected menu item -->
				</div>
			</div>
		</div>
	</div>

	<%
	} else {
	%>
	<a href="../index.jsp">Please login again</a>
	<%
	}
	%>

</body>

</html>