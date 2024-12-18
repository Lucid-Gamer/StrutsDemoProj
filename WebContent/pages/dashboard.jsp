<%@page import="com.apptrove.ledgerly.admin.models.User" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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

            <% if (session.getAttribute("user") !=null) { User user=(User) session.getAttribute("user"); String
                username=user.getUsername(); %>
                <script>
                    $(document).ready(function () {
                        var contextPath = window.location.pathname.split('/')[1];
                        var url = '/' + contextPath + '/login/getMenu';

                        var tempOptions = [
                            {
                                "authorCd": 0,
                                "authorDt": "2024-12-16T00:00:00",
                                "id": 1,
                                "isActive": true,
                                "makerCd": 0,
                                "makerDt": "2024-12-16T00:00:00",
                                "menuId": 100,
                                "menuItemName": "User Maker",
                                "menuItemNameId": "100_UserMaker"
                            },
                            {
                                "authorCd": 0,
                                "authorDt": "2024-12-16T00:00:00",
                                "id": 2,
                                "isActive": true,
                                "makerCd": 0,
                                "makerDt": "2024-12-16T00:00:00",
                                "menuId": 100,
                                "menuItemName": "User Author",
                                "menuItemNameId": "100_UserAuthor"
                            },
                            {
                                "authorCd": 0,
                                "authorDt": "2024-12-16T00:00:00",
                                "id": 3,
                                "isActive": true,
                                "makerCd": 0,
                                "makerDt": "2024-12-16T00:00:00",
                                "menuId": 100,
                                "menuItemName": "User Reader",
                                "menuItemNameId": "100_UserReader"
                            },
                            {
                                "authorCd": 0,
                                "authorDt": "2024-12-16T00:00:00",
                                "id": 4,
                                "isActive": true,
                                "makerCd": 0,
                                "makerDt": "2024-12-16T00:00:00",
                                "menuId": 100,
                                "menuItemName": "User Updater",
                                "menuItemNameId": "100_UserUpdater"
                            },
                            {
                                "authorCd": 0,
                                "authorDt": "2024-12-16T00:00:00",
                                "id": 5,
                                "isActive": true,
                                "makerCd": 0,
                                "makerDt": "2024-12-16T00:00:00",
                                "menuId": 100,
                                "menuItemName": "User Deleter",
                                "menuItemNameId": "100_UserDeleter"
                            }
                        ];

                        $.ajax({
                            url: url,
                            method: 'GET',
                            success: function (response) {
                                if (response.status === "success") {
                                    var menuOptions = response.menuOptions;

                                    console.log("menuOptions: ", menuOptions);
                                    var index = 0;

                                    var userManagementOptions = menuOptions.filter(function (option) {
                                        return option.menuId == 100;
                                    });

                                    var buildingManagementOptions = menuOptions.filter(function (option) {
                                        return option.menuId == 200;
                                    });

                                    var apartmentManagementOptions = menuOptions.filter(function (option) {
                                        return option.menuId == 300;
                                    });

                                    var occupantManagementOptions = menuOptions.filter(function (option) {
                                        return option.menuId == 400;
                                    });

                                    appendOptionsToDropdown('usrMngmntDropdownMenu', userManagementOptions);
                                    appendOptionsToDropdown('bldngMngmntDropdownMenu', buildingManagementOptions);
                                    appendOptionsToDropdown('aptmntMngmntDropdownMenu', apartmentManagementOptions);
                                    appendOptionsToDropdown('occpntMngmntDropdownMenu', occupantManagementOptions);
                                } else {
                                    console.error(response.message);
                                }
                            },
                            error: function (xhr, status, error) {
                                console.error("Error fetching menu: " + error);
                            }
                        });

                        function appendOptionsToDropdown(menuId, options) {
                            var dropdownMenu = $('#' + menuId);
                            dropdownMenu.empty();

                            options.forEach(function (option) {
                                dropdownMenu.append(`
                <li class="dropdown-item" href="#" id="custom${option.menuItemNameId}">${option.menuItemName}</li>
            `);
                            });
                        }
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
                                    <ul class="nav nav-pills custom-dashboard-nav-buttons-div-pills" id="dashboardNavButtonTab">
                                        <li class="nav-item dropdown custom-dashboard-nav-item-dropdown show">
                                            <button class="btn nav-link dropdown-toggle" href="#" id="customusrMngmntDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">User Management</button>
                                            <div class="dropdown-menu show" aria-labelledby="customusrMngmntDropdown" id="usrMngmntDropdownMenu"></div>
                                        </li>
                                        <li class="nav-item dropdown custom-dashboard-nav-item-dropdown">
                                            <button class="btn nav-link dropdown-toggle" href="#" id="custombldngMngmntDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Building Management</button>
                                            <div class="dropdown-menu" aria-labelledby="custombldngMngmntDropdown" id="bldngMngmntDropdownMenu"></div>
                                        </li>
                                        <li class="nav-item dropdown custom-dashboard-nav-item-dropdown">
                                            <button class="btn nav-link dropdown-toggle" href="#" id="customaptmntMngmntDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Apartment Management</button>
                                            <div class="dropdown-menu" aria-labelledby="customaptmntMngmntDropdown" id="aptmntMngmntDropdownMenu"></div>
                                        </li>
                                        <li class="nav-item dropdown custom-dashboard-nav-item-dropdown">
                                            <button class="btn nav-link dropdown-toggle" href="#" id="customoccpntMngmntDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Occupant Management</button>
                                            <div class="dropdown-menu" aria-labelledby="customoccpntMngmntDropdown" id="occpntMngmntDropdownMenu"></div>
                                        </li>
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
                } 
                else 
                { 
                %>
                <a href="../index.jsp">Please login again</a>
                <% 
                } 
                %>

        </body>

        </html>