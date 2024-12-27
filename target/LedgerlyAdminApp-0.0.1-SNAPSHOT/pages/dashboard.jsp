<%@page import="com.apptrove.ledgerly.admin.models.Role"%>
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
<link href="${pageContext.request.contextPath}/resources/bootstrap-4.5.3-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/style/dashboard.css" type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/style/usermaker.css" type="text/css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/popper.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/dashboard.js"></script>
<meta charset="UTF-8">
<title>Dashboard Page</title>
</head>

<body>
	<%
	if (session.getAttribute("user") != null && session.getAttribute("menuHeaders") != null
			&& session.getAttribute("menuOptions") != null && session.getAttribute("role") != null) {
		User user = (User) session.getAttribute("user");
		Role role = (Role) session.getAttribute("role");
		String username = user.getUsername();
		String roleName = role.getRoleName().substring(5).toLowerCase();
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
					<a class="dropdown-item">Role: <%= roleName %></a>
					<div class="dropdown-divider"></div>
                	<a class="dropdown-item custom-dashboard-logout-button-dropdown-item"><button class="btn btn-outline-danger custom-dashboard-logout-button" id="userLogoutButton" onclick="logoutAction()">Logout</button></a>
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

	<!-- Modal -->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="logoutModalLabel" aria-hidden="true" >
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="logoutModalLabel">Confirm Logout</h5>
					<button type="button" class="btn-close" data-dismiss="modal" aria-label="Close" >
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="container-fluid"> Are you sure you want to logout?</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-danger" onclick="logoutAction()" id="confirmLogoutButton">Logout</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var contextPath = window.location.pathname.split('/')[1];
	var logoutUrl = '/' + contextPath + '/logout'

	$("#userLogoutButton").click(function(e) {
		e.preventDefault();
		$('#logoutModal').modal('show');
	});

	function logoutAction() {
		window.location.href=logoutUrl;	
	}
	</script>

	<%
	} else {
	%>
	<a href="${pageContext.request.contextPath}/index.jsp">Please login again</a>
	<%
	}
	%>

</body>

</html>