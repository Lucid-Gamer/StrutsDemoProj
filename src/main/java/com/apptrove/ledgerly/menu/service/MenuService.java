package com.apptrove.ledgerly.menu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.apptrove.ledgerly.admin.models.MenuItemMst;
import com.apptrove.ledgerly.admin.models.MenuMst;
import com.apptrove.ledgerly.menu.dao.MenuDaoImpl;

public class MenuService {

	private static final Logger logger = LogManager.getLogger(MenuService.class);

	private MenuDaoImpl menuDaoImpl = new MenuDaoImpl();

	public Map<String, Object> getMenuHeaderAndOptions() {
		Map<String, Object> menuMap = new HashMap<String, Object>();
		List<MenuMst> menuList = new ArrayList<MenuMst>();
		List<MenuItemMst> menuItemList = new ArrayList<MenuItemMst>();
		try {
			logger.info("Inside getMenuHeaderAndOptions method:::::::::::::::::::::::::::::::::::::::::::::::");
			menuList = menuDaoImpl.getMenuHeader();
			menuItemList = menuDaoImpl.getMenuOptions();
			if (!menuList.isEmpty() && !menuItemList.isEmpty()) {
				menuMap.put("menuHeaders", menuList);
				menuMap.put("menuOptions", menuItemList);
				menuMap.put("status", "success");
				menuMap.put("message", "Menu fetch succesfull");
				logger.info("Succesfully fetched menu::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			} else {
				menuMap.put("menuHeaders", null);
				menuMap.put("menuOptions", null);
				menuMap.put("status", "failed");
				menuMap.put("message", "Menu fetch unsuccesfull");
				logger.info("Menu fetch unsuccesfull::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			}
			logger.info("Exiting getMenuHeaderAndOptions method:::::::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.error("An error occurred: " + e.getMessage());
			e.printStackTrace();
			menuMap.put("menuHeaders", null);
			menuMap.put("menuOptions", null);
			menuMap.put("status", "failed");
			menuMap.put("message", "Menu fetch unsuccesfull");
		}
		return menuMap;
	}

}
