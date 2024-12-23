/**
 * 
 */

function loadPage(url) {
	var contextPath = window.location.pathname.split('/')[1];
	$("#dashboardMainContent").html("<p>Loading...</p>");

	$.ajax({
		url: '/' + contextPath + url,
		type: 'GET',
		success: function(response) {
			$("#dashboardMainContent").html(response);
		},
		error: function(xhr, status, error) {
			$("#dashboardMainContent").html('<p>Error loading page. Please try again.</p>');
		}
	})
}

$(document).ready(function() {
	/*var menuHeadersList = JSON.parse(document.getElementById("menuHeadersJson").textContent);
	var menuOptionsList = JSON.parse(document.getElementById("menuOptionsJson").textContent);*/

	var contextPath = window.location.pathname.split('/')[1];
	var menuUrl = '/' + contextPath + '/login/getMenu';

	$.ajax({
		url: menuUrl,
		method: 'GET',
		success: function(response) {
			if (response.status === "success") {
				var menuHeadersList = response.menuHeaders;
				var menuOptionsList = response.menuOptions;

				var navContainer = $('#dashboardNavButtonTab');

				navContainer.empty();

				menuHeadersList.forEach(menuHeader => {
					var dropDownItem = `
								 <div class="nav-item dropdown custom-dashboard-nav-item-dropdown">
								 	<button class="btn nav-link dropdown-toggle custom-dashboard-nav-button" href="#" id="custom${menuHeader.menuTabId}Dropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${menuHeader.menuName}</button>
									<ul class="dropdown-menu" aria-labelledby="custom${menuHeader.menuTabId}Dropdown"> 
							`;

					var filterOptions = menuOptionsList.filter(function(option) {
						return option.menuId === menuHeader.menuId;
					});

					filterOptions.forEach(function(option) {
						dropDownItem += `
									<li><a class="dropdown-item" href="#" id="custom${option.menuItemNameId}" onclick="loadPage('${option.itemMenuAction}')">${option.menuItemName}</a></li>
								`;
					});

					dropDownItem += '</ul></li>';

					navContainer.append(dropDownItem);
				});

			} else {
				console.error(response.message);
			}
		},
		error: function(xhr, status, error) {
			console.error("Error fetching menu: " + error);
		}
	});

	var logoutUrl = '/' + contextPath + '/logout'

	$("#userLogoutButton").click(function(e) {
		e.preventDefault();
		$('#logoutModal').modal('show');
	});

	$('#confirmLogoutButton').click(function() {
		$.ajax({
			url: logoutUrl,
			method: 'GET',
			success: function(response) {
				if (response.status === "success") {
					alert("Logout succesfull");
					window.location.href = '../index.jsp';
				}
			},
			error: function(xht, status, message) {
				console.error("An error occurred: ", message);
			}
		});
	});



});
