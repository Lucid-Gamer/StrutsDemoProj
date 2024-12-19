/**
 * 
 */

$(document).ready(function () {
    var menuHeadersList = JSON.parse(document.getElementById("menuHeadersJson").textContent);
    var menuOptionsList = JSON.parse(document.getElementById("menuOptionsJson").textContent);
	
	var navContainer = $('#dashboardNavButtonTab');
	
	navContainer.empty();
	
	menuHeadersList.forEach(menuHeader=> {
		var dropDownItem = `
			 <li class="nav-item dropdown custom-dashboard-nav-item-dropdown">
			 	<button class="btn nav-link dropdown-toggle" id="custom${menuHeader.menuTabId}Dropdown" ata-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${menuHeader.menuName}</button>
			 </li>	
				<ul class="dropdown-menu" aria-labelledby="custom${menuHeader.menuTabId}Dropdown"> 
		`;
		
		var filterOptions = menuOptionsList.filter(function(option) {
			return option.menuId = menuHeader.menuId;
		});
		
		filterOptions.forEach(function(option) {
			dropDownItem += `
				<li><a class="dropdown-item" href="#" id="custom${option.menuItemNameId}">${option.menuItemName}</a></li>
			`;
		});
		
		dropDownItem += '</ul>';
		
		navContainer.append(dropDownItem);
	})

    
});
