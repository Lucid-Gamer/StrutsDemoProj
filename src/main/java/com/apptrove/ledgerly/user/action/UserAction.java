package com.apptrove.ledgerly.user.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	
	private static final long serialVersionUID = -658159856442490554L;

	private static final Logger logger = LogManager.getLogger(UserAction.class);
	
	private Map<String, Object> respObject = new HashMap<String, Object>();
	
	public String makerAction() {
		HttpServletRequest httpRequest = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = httpRequest.getSession();
		try {
			logger.info("Inside makerAction method:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			String roleName = (String) session.getAttribute("roleName");
			if (roleName != null && (roleName.equals("ROLE_ADMIN") || roleName.equals("ROLE_MAKER"))) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			
		}
		
		return null;
	}

}
