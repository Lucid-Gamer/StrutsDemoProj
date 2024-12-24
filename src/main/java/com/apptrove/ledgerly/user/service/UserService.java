package com.apptrove.ledgerly.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.apptrove.ledgerly.admin.models.User;
import com.apptrove.ledgerly.user.dao.UserDaoImpl;

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
	
	public Map<String, Object> getUnauthUserList() {
		Map<String, Object> respObject = new HashMap<String, Object>();
		try {
			logger.info("Inside getUnauthUserList method :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			List<User> userList = userDaoImpl.unauthorizedUserList();
			logger.info("Users List found: "+userList.size());
			if (userList != null && !userList.isEmpty()) {
				respObject.put("unauthorizedUserList", userList);
				respObject.put("status", "success");
				respObject.put("message","Users List found: "+userList.size());
				respObject.put("errorCode", "000");
			} else {
				respObject.put("unauthorizedUserList", null);
				respObject.put("status","success");
				respObject.put("message", "No Users found");
				respObject.put("errorCode", "000");
			}
		} catch (Exception e) {
			logger.info("An error occurred: "+e.getMessage());
			e.printStackTrace();
			respObject.put("status", "failed");
			respObject.put("message", e.getMessage());
			respObject.put("errorCode", "-2");
		}
		
		return respObject;
	}

}
