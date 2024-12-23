package com.apptrove.ledgerly.login.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.apptrove.ledgerly.admin.models.Role;
import com.apptrove.ledgerly.admin.models.User;
import com.apptrove.ledgerly.admin.payload.LoginModel;
import com.apptrove.ledgerly.login.dao.LoginDaoImpl;
import com.opensymphony.xwork2.ActionContext;

public class LoginService {

	public static final Logger logger = LogManager.getLogger(LoginService.class);

	private LoginDaoImpl loginDaoImpl = new LoginDaoImpl();

	public Map<String, Object> loginUser(LoginModel loginModel) {
		Map<String, Object> respObj = new HashMap<>();
		boolean flag = false;
		logger.info("Inside loginUser method::::::::::::::::::::::::");
		try {
			flag = loginDaoImpl.validateUsername(loginModel.getUsername());
			if (flag) {
				User user = loginDaoImpl.loginUser(loginModel.getUsername(), loginModel.getPassword());

				if (user != null && loginDaoImpl.existsRoleByUserId(user.getUserId())) {
					Integer retryCount = user.getLoginTries() == null ? 0 : user.getLoginTries();
					if (!user.getIsActive()) {
						respObj.put("user", null);
						respObj.put("role", null);
						respObj.put("status", "failed");
						respObj.put("message", "User not yet Active");
					} else if (retryCount < 3 && !user.getAccountLocked()) {
						loginDaoImpl.updateLoginDate(user);
						Role role = loginDaoImpl.getUserRoleForLogin(user);
						respObj.put("user", user);
						respObj.put("role", role);
						respObj.put("status", "success");
						respObj.put("message", "Login succesfull");
					} else if (retryCount >= 3) {
						if (user.getAccountLocked()) {
							respObj.put("user", null);
							respObj.put("role", null);
							respObj.put("status", "errorLock");
							respObj.put("message", "Account Locked");
						} else {
							loginDaoImpl.lockUserAccount(user.getUsername());
							respObj.put("user", null);
							respObj.put("role", null);
							respObj.put("status", "errorLock");
							respObj.put("message", "Account Locked");
						}
					}
				} else {
					Integer count = loginDaoImpl.getLoginTries(loginModel.getUsername());
					if (count == 2) {
						loginDaoImpl.lockUserAccount(loginModel.getUsername());
						respObj.put("user", null);
						respObj.put("role", null);
						respObj.put("status", "errorLock");
						respObj.put("message", "Account Locked");
					} else if (count < 2) {
						loginDaoImpl.updateRetryCountFail(loginModel.getUsername(), count + 1);
						respObj.put("user", null);
						respObj.put("role", null);
						respObj.put("status", "errorPass");
						respObj.put("message", "Invalid Password");
					} else {
						respObj.put("user", null);
						respObj.put("role", null);
						respObj.put("status", "errorLock");
						respObj.put("message", "Account Locked due to too many retries");
					}
				}
			} else {
				respObj.put("user", null);
				respObj.put("role", null);
				respObj.put("status", "errorUname");
				respObj.put("message", "Username is incorrect");
			}

		} catch (Exception e) {
			logger.info("An error occurred: " + e.getMessage());
			e.printStackTrace();
			respObj.put("user", null);
			respObj.put("role", null);
			respObj.put("status", "error");
			respObj.put("message", "An error occurred");
		}
		return respObj;
	}

	public Map<String, Object> logoutUser() {
		Map<String, Object> respObj = new HashMap<String, Object>();
		HttpServletRequest httpRequest = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = httpRequest.getSession(false);
		try {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				if (loginDaoImpl.validateUsername(user.getUsername())) {
					session.removeAttribute("user");
					session.invalidate();
					respObj.put("status", "success");
					respObj.put("message", "User logged out succesfully");
					respObj.put("code", "000");
				} else {
					respObj.put("status", "failed");
					respObj.put("message", "User not found");
					respObj.put("code", "001");
				}
			} else {
				respObj.put("status", "failed");
				respObj.put("message", "Session already invalidated");
				respObj.put("code", "002");
			}
		} catch (Exception e) {
			logger.error("An error occurred: " + e.getMessage());
			e.printStackTrace();
			respObj.put("status", "failed");
			respObj.put("message", "Something went wrong");
			respObj.put("code", "-1");
		}

		return respObj;
	}

}
