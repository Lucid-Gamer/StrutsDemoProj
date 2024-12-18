<%@page import="com.apptrove.ledgerly.admin.models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link href="../resources/bootstrap-4.5.3-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="./style/dashboard.css" type="text/css" rel="stylesheet">
    <script src="../resources/jquery.min.js" type="text/javascript"></script>
    <script src="../resources/popper.min.js" type="text/javascript"></script>
    <script src="../resources/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
    <meta charset="UTF-8">
    <title>Dashboard Page</title>
</head>
<body>

<% 
if (session.getAttribute("user") != null) {
    User user = (User) session.getAttribute("user");
    String username = user.getUsername();
%>
<script>
$(document).ready(function() {
    var contextPath = window.location.pathname.split('/')[1];
    var url = '/'+contextPath+'/login/getMenu';

    $.ajax({
        url: url,
        method: 'GET',
        success: function(response) {
            if(response.status === "success") {
                var menuHeaders = response.menuHeaders;
                var menuOptions = response.menuOptions;

                var navButtonTab = $("#dashboardNavButtonTab");
                navButtonTab.empty();  // Clear any existing content

                // Loop through each menu header
                menuHeaders.forEach(function(menuHeader) {
                    var dropDownItem = `
                        <li class="nav-item dropdown custom-dashboard-nav-item-dropdown col-auto">
                            <a class="nav-link dropdown-toggle" href="#" id="custom${menuHeader.menuTabId}Dropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${menuHeader.menuName}</a>
                            <div class="dropdown-menu" aria-labelledby="custom${menuHeader.menuTabId}Dropdown">
                    `;

                    // Filter options for the current menu header
                    var filteredOptions = menuOptions.filter(function(option) {
                        return option.menuId === menuHeader.menuId;  // Match menuId
                    });

                    // Loop through filtered options and append them as dropdown items
                    filteredOptions.forEach(function(option) {
                        dropDownItem += `
                            <a class="dropdown-item" href="#" id="custom${option.menuItemNameId}">${option.menuItemName}</a>
                        `;
                    });

                    dropDownItem += '</div></li>';

                    // Append the dropdown menu to the navigation tab
                    navButtonTab.append(dropDownItem);
                });
            } else {
                console.error(response.message);
            }
        },
        error: function(xhr, status, error) {
            console.error("Error fetching menu: " + error);
        }
    });
});

</script>

<div>
    <nav class="navbar navbar-expand-lg navbar-light custom-dashboard-navbar">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
            <a class="navbar-brand custom-dashboard-navbar-brand" href="#">Ledgerly</a>
        </div>
        <button class="btn btn-outline-light me-auto custom-navbar-user-button" type="button">Welcome, <%= username %></button>
    </nav>

    <div class="custom-dashboard-container">
        <div class="card custom-dashboard-card">
            <div class="custom-dashboard-nav-buttons-div" id="dashboardNavButtonDiv">
                <ul class="nav nav-pills custom-dashboard-nav-buttons-div-pills" id="dashboardNavButtonTab">
                    
                </ul>
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
