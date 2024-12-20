package com.apptrove.ledgerly.login.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONPropertyIgnore;

import com.apptrove.ledgerly.admin.models.Role;
import com.apptrove.ledgerly.admin.models.User;
import com.apptrove.ledgerly.admin.payload.LoginModel;
import com.apptrove.ledgerly.login.service.LoginService;
import com.apptrove.ledgerly.menu.service.MenuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	
	private static final long serialVersionUID = -8384690547453382802L;
	
	public static final Logger logger = LogManager.getLogger(LoginAction.class);

	private LoginModel loginModel;

	private Map<String,Object> respObject = new HashMap<String,Object>();

	private LoginService loginService = new LoginService();
	
	private MenuService menuService = new MenuService();

	public Map<String, Object> getRespObject() {
		return respObject;
	}

	public void setRespObject(Map<String, Object> respObject) {
		this.respObject = respObject;
	}
	public LoginModel getLoginModel() {
		return loginModel;
	}

	public void setLoginModel(LoginModel loginModel) {
		this.loginModel = loginModel;
	}
	
	@JSONPropertyIgnore
	public String login() {
		HttpServletRequest httpRequest = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = httpRequest.getSession();
		try {
			respObject = loginService.loginUser(loginModel);
			if (respObject.containsKey("user") && respObject.get("user")!=null && respObject.containsKey("role") && respObject.get("user") != null) {
				User user = (User) respObject.get("user");
				Role role = (Role) respObject.get("role");
				respObject = menuService.getMenuHeaderAndOptions();
				if (respObject.get("menuHeaders") != null && respObject.get("menuOptions") != null && role != null) {
					session.setAttribute("user", user);
					session.setAttribute("role", role);
					session.setAttribute("roleName", role.getRoleName());
					session.setAttribute("menuHeaders", respObject.get("menuHeaders"));
					session.setAttribute("menuOptions", respObject.get("menuOptions"));
					return SUCCESS;
				} else {
					return ERROR;
				}
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String logout() {
		try {
			respObject = loginService.logoutUser();
			if (respObject.containsKey("status") && respObject.get("status").equals("success")) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String getMenu() {
		try {
			logger.info("Inside getMenu method:::::::::::::::::::::::::::::::::::::");
			respObject = menuService.getMenuHeaderAndOptions();
			if (!respObject.isEmpty()) {
				logger.info("Exiting getMenu method:::::::::::::::::::::::::::::::::::::");
				return SUCCESS;
			} else {
				logger.info("An error occurred::::::::::::::::::::::::::::::::::::::::::");
				return ERROR;
			}
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
	}

}
