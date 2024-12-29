package com.apptrove.ledgerly.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.apptrove.ledgerly.admin.models.User;
import com.apptrove.ledgerly.user.dao.UserDaoImpl;
import com.opensymphony.xwork2.ActionContext;

public class UserService {
	
	private static final Logger logger = LogManager.getLogger(UserService.class);
	
	private UserDaoImpl userDaoImpl = new UserDaoImpl();
	
	public Map<String, Object> registerUser(User user,Integer roleId) {
		Map<String, Object> respObject = new HashMap<>();
		try {
			if (!userDaoImpl.existsByRoleId(roleId)) {
				logger.info("Role Id: "+roleId+ " does not exist");
				respObject.put("status","failed");
				respObject.put("message", "Role Id: "+roleId+" does not exist");
				respObject.put("errorCode", "-1");
				return respObject;
			}
			
			respObject.put("user", userDaoImpl.makerMethod(user, roleId));
			respObject.put("status", "success");
			respObject.put("message","User created with user id: "+user.getUserId());
			
		} catch (Exception e) {
			logger.info("An error occurred: "+e.getMessage());
			e.printStackTrace();
			respObject.put("status", "failed");
			respObject.put("message", e.getMessage());
			respObject.put("errorCode", "-2");
		}
		
		return respObject;
		
	}
	
	public List<User> getUnauthUserList() {
		List<User> userList = new ArrayList<User>();
		try {
			logger.info("Inside getUnauthUserList method :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			userList = userDaoImpl.unauthorizedUserList();
			logger.info("Users List found: "+userList.size());
		} catch (Exception e) {
			logger.info("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return userList;
	}
	
	public Map<String, Object> authorizeUser(Integer userId) {
		Map<String, Object> respObject = new HashMap<String, Object>();
		HttpServletRequest httpRequest = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = httpRequest.getSession();
		boolean flag = false;
		try {
			logger.info("Inside authorizeUser method::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			if (userDaoImpl.existsByUserId(userId) && session.getAttribute("user") != null) {
				flag = userDaoImpl.authorizeUser(userId);
			} else {
				respObject.put("status", "failed");
				respObject.put("message","Use Authorization unsuccesfull.User not found.");
				respObject.put("errorCd","001");
				return respObject;
			}
			
			
			if (flag) {
				respObject.put("status", "success");
				respObject.put("message","User Authorization succesfull");
				respObject.put("errorCd","000");
			} else {
				respObject.put("status", "failed");
				respObject.put("message","User Authorization unsuccesfull. An error occurred. ");
				respObject.put("errorCd","002");
			}
			logger.info("Exiting authorizeUser method::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.info("An error occurred: "+e.getMessage());
			respObject.put("status", "failed");
			respObject.put("message","An error occurred: "+e.getMessage());
			respObject.put("errorCd","-1");
			e.printStackTrace();
		}
		return respObject;
	}
	
	public Map<String, Object> rejectUser(Integer userId) {
		Map<String, Object> respObject = new HashMap<String, Object>();
		HttpServletRequest httpRequest = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = httpRequest.getSession();
		boolean flag = false;
		try {
			logger.info("Inside rejectUser method::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			if (userDaoImpl.existsByUserId(userId) && session.getAttribute("user") != null) {
				flag = userDaoImpl.rejectUser(userId);
			} else {
				respObject.put("status", "failed");
				respObject.put("message","User Rejection unsuccessfull.User not found.");
				respObject.put("errorCd","001");
				return respObject;
			}
			
			
			if (flag) {
				respObject.put("status", "success");
				respObject.put("message","User Rejection succesfull");
				respObject.put("errorCd","000");
			} else {
				respObject.put("status", "failed");
				respObject.put("message","User Authorization unsuccesfull. An error occurred. ");
				respObject.put("errorCd","002");
			}
			logger.info("Exiting rejectUser method::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.info("An error occurred: "+e.getMessage());
			respObject.put("status", "failed");
			respObject.put("message","An error occurred: "+e.getMessage());
			respObject.put("errorCd","-1");
			e.printStackTrace();
		}
		return respObject;
	}
	
	public List<User> getAllActiveUsers() {
		List<User> userList = new ArrayList<User>();
		try {
			logger.info("Inside getUnauthUserList method :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			userList = userDaoImpl.getAllActiveUsers();
			logger.info("Users List found: "+userList.size());
		} catch (Exception e) {
			logger.info("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return userList;
	}

}
