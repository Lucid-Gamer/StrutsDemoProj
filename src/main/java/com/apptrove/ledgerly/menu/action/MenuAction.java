package com.apptrove.ledgerly.menu.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.apptrove.ledgerly.menu.service.MenuService;
import com.opensymphony.xwork2.ActionSupport;

public class MenuAction extends ActionSupport{

	private static final long serialVersionUID = 4581985562344456468L;
	
	private static final Logger logger = LogManager.getLogger(MenuAction.class);
	
	private MenuService menuService = new MenuService();
	
	public MenuAction() {
		// TODO Auto-generated constructor stub
	}
	
	public MenuAction(MenuService menuService, Map<String, Object> respObject) {
		super();
		this.menuService = menuService;
		this.respObject = respObject;
	}

	private Map<String,Object> respObject = new HashMap<String,Object>();
	
	public String getMenu() {
		try {
			logger.info("Inside getMenu method:::::::::::::::::::::::::::::::::::::");
			respObject = menuService.getMenuHeaderAndOptions();
			if (!respObject.isEmpty()) {
				logger.info("Exiting getMenu method:::::::::::::::::::::::::::::::::::::");
				return SUCCESS;
			} else {
				logger.info("Exiting getMenu method:::::::::::::::::::::::::::::::::::::");
				return ERROR;
			}
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public Map<String, Object> getRespObject() {
		return respObject;
	}

	public void setRespObject(Map<String, Object> respObject) {
		this.respObject = respObject;
	}
	
}
