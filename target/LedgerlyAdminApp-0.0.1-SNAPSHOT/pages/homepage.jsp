<%@page import="com.apptrove.ledgerly.admin.models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}/resources/bootstrap-4.5.3-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/pages/style/dashboard.css" type="text/css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/resources/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/resources/popper.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
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
            url : url,
            method : 'GET',
            success : function(response) {
                if(response.status === "success") {
                	console.log(response);
                    var menuHeaders = response.menuHeaders;
                    var menuOptions = response.menuOptions;
                    
                    var navButtonTab = $("#dashboardNavButtonTab");
                    navButtonTab.empty();  // Clear any existing items
                    
                    // Dynamically create the dropdown buttons and their options
                    menuHeaders.forEach(function(menuHeader) {
                        var dropDownItem = `
                            <div class="nav-item dropdown custom-dashboard-nav-item-dropdown col-auto">
                                <button class="btn nav-link dropdown-toggle" href="#" id="custom${menuHeader.menuTabId}Dropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${menuHeader.menuName}</button>
                                <ul class="dropdown-menu" aria-labelledby="custom${menuHeader.menuTabId}Dropdown">
                        `;

                        var filteredOptions = menuOptions.filter(function(option) {
                            return option.menuId === menuHeader.menuId;
                        });

                        debugger;
                        
                        
                        filteredOptions.forEach(function(option) {
                            console.log(option.menuItemName); 
                            dropDownItem += `
                                <li><a class="dropdown-item" href="#">${option.menuItemName}</a></li>  
                            `;
                        });

                        dropDownItem += '</ul></div>';

                        navButtonTab.append(dropDownItem); // Append the dropdown to the nav
                    });
                } else {
                    console.error(response.message);
                }
            },
            error : function(xhr, status, error) {
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
        <button class="btn btn-outline-light me-auto custom-navbar-user-button" type="button">Welcome, <%=username%></button>
    </nav>

    <div class="custom-dashboard-container">
        <div class="card custom-dashboard-card">
            <div class="custom-dashboard-nav-buttons-div" id="dashboardNavButtonDiv">
                <div class="nav nav-pills btn btn-grp custom-dashboard-nav-buttons-div-pills" id="dashboardNavButtonTab">
                    <!-- Dynamic menu items will be added here -->
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
