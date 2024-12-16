package com.apptrove.ledgerly.login.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.apptrove.ledgerly.admin.models.User;
import com.apptrove.ledgerly.admin.payload.LoginModel;
import com.apptrove.ledgerly.login.dao.LoginDaoImpl;

public class LoginService {
    
    public static final Logger logger = LogManager.getLogger(LoginService.class); 

    private LoginDaoImpl loginDaoImpl = new LoginDaoImpl();

    public Map<String,Object> loginUser(LoginModel loginModel) {
        Map<String,Object> respObj = new HashMap<>();
        boolean flag = false;
        logger.info("Inside loginUser method::::::::::::::::::::::::");
        try {
            
            
            flag = loginDaoImpl.validateUsername(loginModel.getUsername());
            if (flag) {
            	User user = loginDaoImpl.loginUser(loginModel.getUsername(), loginModel.getPassword());
            	if (user!=null) {
                    Integer retryCount = (Integer) user.getLoginTries();
                    if (retryCount < 3 && !user.getAccountLocked()) {
						loginDaoImpl.updateLoginDate(user);
						respObj.put("user", user);
	                    respObj.put("status", "success");
	                    respObj.put("message", "Login succesfull");
					} else if (retryCount >= 3) {
						if (user.getAccountLocked()) {
							respObj.put("user", null);
		                    respObj.put("status", "errorLock");
		                    respObj.put("message", "Account Locked");
						} else {
							loginDaoImpl.lockUserAccount(user.getUsername());
							respObj.put("user", null);
		                    respObj.put("status", "errorLock");
		                    respObj.put("message", "Account Locked");
						}
					}
                } else {
                	Integer count = loginDaoImpl.getLoginTries(loginModel.getUsername());
                	if (count == 2) {
                		loginDaoImpl.lockUserAccount(loginModel.getUsername());
						respObj.put("user", null);
	                    respObj.put("status", "errorLock");
	                    respObj.put("message", "Account Locked");
					} else if (count < 2) {
						loginDaoImpl.updateRetryCountFail(loginModel.getUsername(), count+1);
						respObj.put("user", null);
	                    respObj.put("status", "errorPass");
	                    respObj.put("message", "Invalid Password");
					} else {
						respObj.put("user", null);
	                    respObj.put("status", "errorLock");
	                    respObj.put("message", "Account Locked due to too many retries");
					}
                }
			} else {
				respObj.put("user", null);
                respObj.put("status", "errorUname");
                respObj.put("message", "Username is incorrect");
			}
            
        } catch (Exception e) {
            logger.info("An error occurred: "+e.getMessage());
            e.printStackTrace();
            respObj.put("user", null);
            respObj.put("status", "error");
                respObj.put("message", "An error occurred");
        }
        return respObj;
    }

}
