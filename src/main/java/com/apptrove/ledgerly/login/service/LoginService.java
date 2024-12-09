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
        logger.info("Inside loginUser method::::::::::::::::::::::::");
        try {
            User user = loginDaoImpl.loginUser(loginModel.getUsername(), loginModel.getPassword());
            if (user!=null) {
                respObj.put("user", user);
                respObj.put("status", "success");
                respObj.put("message", "User Login Successfull");
            } else {
                respObj.put("user", null);
                respObj.put("status", "error");
                respObj.put("message", "Invalid credentials");
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
