package com.apptrove.ledgerly.user.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.apptrove.ledgerly.admin.models.User;
import com.apptrove.ledgerly.admin.payload.RegisterModel;
import com.apptrove.ledgerly.user.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	private static final long serialVersionUID = -658159856442490554L;

	private static final Logger logger = LogManager.getLogger(UserAction.class);

	private RegisterModel registerModel;

	private UserService userService = new UserService();

	private List<User> userList;

	private Map<String, Object> respObject = new HashMap<String, Object>();

	public UserAction(RegisterModel registerModel, Map<String, Object> respObject) {
		super();
		this.registerModel = registerModel;
		this.respObject = respObject;
	}

	public UserAction() {
		// TODO Auto-generated constructor stub
	}

	public String makerAction() {
		HttpServletRequest httpRequest = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = httpRequest.getSession();
		try {
			logger.info(
					"Inside makerAction method:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			String roleName = (String) session.getAttribute("roleName");
			if (session.getAttribute("user") == null) {
				respObject.put("status", "failed");
				respObject.put("message", "Maker User Session Expired");
				respObject.put("errorCode", "001");
				return ERROR;
			}

			if (roleName != null && (roleName.equals("ROLE_ADMIN") || roleName.equals("ROLE_MAKER"))) {
				respObject = userService.registerUser(registerModel.getUser(), registerModel.getRoleId());
				return SUCCESS;
			} else {
				respObject.put("status", "failed");
				respObject.put("message", "User not authorized to register another user");
				respObject.put("errorCode", "002");
				return ERROR;
			}
		} catch (Exception e) {
			logger.error("An error occurred: " + e.getMessage());
			e.printStackTrace();
			respObject.put("status", "failed");
			respObject.put("message", e.getMessage());
			respObject.put("errorCode", "003");
			return ERROR;
		}

	}

	public String getUnauthUserList() {
		HttpServletRequest httpRequest = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = httpRequest.getSession();
		try {
			logger.info(
					"Inside makerAction method:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			String roleName = (String) session.getAttribute("roleName");
			if (session.getAttribute("user") == null) {
				respObject.put("status", "failed");
				respObject.put("message", "User Session Expired");
				respObject.put("errorCode", "001");
				addActionError("User Session Expired");
				return ERROR;
			}

			if (roleName != null && (roleName.equals("ROLE_ADMIN") || roleName.equals("ROLE_AUTHOR"))) {
				respObject = userService.getUnauthUserList();
				if (respObject.containsKey("unauthorizedUserList")) {
					userList = (List<User>) respObject.get("unauthorizedUserList");
					return SUCCESS;
				} else {
					return ERROR;
				}
				
			} else {
				respObject.put("status", "failed");
				respObject.put("message", "User not authorized for this role");
				respObject.put("errorCode", "002");
				addActionError("User not authorized for this role");
				return ERROR;
			}
		} catch (Exception e) {
			logger.error("An error occurred: " + e.getMessage());
			e.printStackTrace();
			respObject.put("status", "failed");
			respObject.put("message", e.getMessage());
			respObject.put("errorCode", "003");
			addActionError(e.getMessage());
			return ERROR;
		}
	}

	public RegisterModel getRegisterModel() {
		return registerModel;
	}

	public void setRegisterModel(RegisterModel registerModel) {
		this.registerModel = registerModel;
	}

	public Map<String, Object> getRespObject() {
		return respObject;
	}

	public void setRespObject(Map<String, Object> respObject) {
		this.respObject = respObject;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}
