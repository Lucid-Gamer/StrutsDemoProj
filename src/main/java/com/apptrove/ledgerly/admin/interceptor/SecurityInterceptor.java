package com.apptrove.ledgerly.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SecurityInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = -7387188736836578715L;

	private static final Logger logger = LogManager.getLogger(SecurityInterceptor.class);
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest httpRequest = ServletActionContext.getRequest();
		HttpServletResponse httpResponse = ServletActionContext.getResponse();
		
		HttpSession session = httpRequest.getSession(false);
		
		logger.info("Inside intercept method::::::::::::::::::::::::::::::::::::::::::::::");
		
		if (session == null || session.getAttribute("user") == null) {
			logger.info("Login required::::::::::::::::::::::::::::::::::::::::::::::::::::");
			return "loginRequired";
		}
		logger.info("Exiting intercept method::::::::::::::::::::::::::::::::::::::::::::::");
		return invocation.invoke();
	}
	
	

}
