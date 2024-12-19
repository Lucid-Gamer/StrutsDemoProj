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
		List<MenuMst> menuHeadersList = (List<MenuMst>) session.getAttribute("menuHeaders");
		List<MenuItemMst> menuOptionsList = (List<MenuItemMst>) session.getAttribute("menuOptions");
		String username = user.getUsername();

		ObjectMapper objectMapper = new ObjectMapper();

		String menuHeadersJson = objectMapper.writeValueAsString(menuHeadersList);
		String menuOptionsJson = objectMapper.writeValueAsString(menuOptionsList);
	%>
	<script type="application/json" id="menuHeadersJson"><%=menuHeadersJson%></script>
    <script type="application/json" id="menuOptionsJson"><%=menuOptionsJson%></script>
	<div>
		<nav
			class="navbar navbar-expand-lg navbar-light custom-dashboard-navbar">
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarTogglerDemo01"
				aria-controls="navbarTogglerDemo01" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarTogglerDemo01">
				<a class="navbar-brand custom-dashboard-navbar-brand" href="#">Ledgerly</a>
			</div>
			<button
				class="btn btn-outline-light me-auto custom-navbar-user-button"
				type="button">
				Welcome, <%= username %></button>
		</nav>

		<div class="custom-dashboard-container">
			<div class="card custom-dashboard-card">
				<div class="custom-dashboard-nav-buttons-div"
					id="dashboardNavButtonDiv">
					<div
						class="nav nav-pills btn btn-grp custom-dashboard-nav-buttons-div-pills"
						id="dashboardNavButtonTab">
						<ul class="nav nav-pills custom-dashboard-nav-buttons-div-pills"
							id="dashboardNavButtonTab">
							<!-- <li
								class="nav-item dropdown custom-dashboard-nav-item-dropdown show">
								<button class="btn nav-link dropdown-toggle" href="#"
									id="customusrMngmntDropdown" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="true">User
									Management</button>
								<div class="dropdown-menu show"
									aria-labelledby="customusrMngmntDropdown"
									id="usrMngmntDropdownMenu"></div>
							</li>
							<li class="nav-item dropdown custom-dashboard-nav-item-dropdown">
								<button class="btn nav-link dropdown-toggle" href="#"
									id="custombldngMngmntDropdown" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false">Building
									Management</button>
								<div class="dropdown-menu"
									aria-labelledby="custombldngMngmntDropdown"
									id="bldngMngmntDropdownMenu"></div>
							</li>
							<li class="nav-item dropdown custom-dashboard-nav-item-dropdown">
								<button class="btn nav-link dropdown-toggle" href="#"
									id="customaptmntMngmntDropdown" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false">Apartment
									Management</button>
								<div class="dropdown-menu"
									aria-labelledby="customaptmntMngmntDropdown"
									id="aptmntMngmntDropdownMenu"></div>
							</li>
							<li class="nav-item dropdown custom-dashboard-nav-item-dropdown">
								<button class="btn nav-link dropdown-toggle" href="#"
									id="customoccpntMngmntDropdown" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false">Occupant
									Management</button>
								<div class="dropdown-menu"
									aria-labelledby="customoccpntMngmntDropdown"
									id="occpntMngmntDropdownMenu"></div>
							</li> -->
						</ul>
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