package com.apptrove.ledgerly.menu.dao;

import java.util.List;

import com.apptrove.ledgerly.admin.models.MenuItemMst;
import com.apptrove.ledgerly.admin.models.MenuMst;

public interface MenuDao {

	public List<MenuMst> getMenuHeader();
	
	public List<MenuItemMst> getMenuOptions();
	
}
