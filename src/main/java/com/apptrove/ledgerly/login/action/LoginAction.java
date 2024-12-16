package com.apptrove.ledgerly.login.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.apptrove.ledgerly.admin.models.User;
import com.apptrove.ledgerly.admin.payload.LoginModel;
import com.apptrove.ledgerly.login.service.LoginService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	
	private static final long serialVersionUID = -8384690547453382802L;
	
	public static final Logger logger = LogManager.getLogger(LoginAction.class);

	private LoginModel loginModel = new LoginModel();
	
//	private String username;
	
//	private String password;

	private Map<String,Object> respObject = new HashMap<String,Object>();

	private LoginService loginService = new LoginService();

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

	public String login() {
		HttpServletRequest httpRequest = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = httpRequest.getSession();
		try {
//			loginModel = new LoginModel(username, password);
			if (loginModel!=null && loginModel.getUsername()!=null) {
				respObject = loginService.loginUser(loginModel);
				if (respObject.containsKey("user") && respObject.get("user")!=null) {
					User user = (User) respObject.get("user"); 
					session.setAttribute("user", user);
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
		HttpServletRequest httpRequest = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = httpRequest.getSession();
		try {
			User user = (User) session.getAttribute("user");
			if (user!=null) {
				session.removeAttribute("user");
			}
			session.invalidate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
	}

}
