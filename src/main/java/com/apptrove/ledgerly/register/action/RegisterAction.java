package com.apptrove.ledgerly.register.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.Parameter;
import org.json.JSONObject;
import org.apache.struts2.dispatcher.Parameter;


import com.apptrove.ledgerly.admin.models.APARTMENT_MST;
import com.apptrove.ledgerly.admin.models.BUILDING_MST;
import com.apptrove.ledgerly.admin.models.User;
import com.apptrove.ledgerly.admin.payload.RegisterModel;
import com.apptrove.ledgerly.admin.payload.RoleDTO;
import com.apptrove.ledgerly.admin.payload.UsernameValidityResponse;
import com.apptrove.ledgerly.register.service.RegisterServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport{

	private static final long serialVersionUID = -1496885308634234904L;
	
	private static final Logger logger = LogManager.getLogger(RegisterAction.class);
	
	private List<RoleDTO> roleList = new ArrayList<RoleDTO>();
	
	private List<BUILDING_MST> bldngList = new ArrayList<BUILDING_MST>();
	
	private List<APARTMENT_MST> aptmntList = new ArrayList<APARTMENT_MST>();

	private Integer selectedRoleId;
	
	private Integer selectedBldngId;
	
	private String username;
	
	private UsernameValidityResponse response;
	
	private RegisterModel registerModel;
	
	private Map<String, Object> respObj = new HashMap<String, Object>();

	private RegisterServiceImpl registerService = new RegisterServiceImpl();
	
	public String getAllRoles() {
		try {
			logger.info("Entering getAllRoles method:::::::::::::::::::::::::::::::::::::::::::::::");
			List<RoleDTO> roles = registerService.getAllRoles();
			setRoleList(roles);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String getAllBuildings() {
		try {
			logger.info("Entering getAllBuildings method:::::::::::::::::::::::::::::::::::::::::::::::");
			List<BUILDING_MST> bldng_List= registerService.getAllBuildings();
			setBldngList(bldng_List);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String getAptmntByBldng() {
		try {
			logger.info("Entering getAptmntByBldng method:::::::::::::::::::::::::::::::::::::::::::::::");
			List<APARTMENT_MST> aptmnt_List= registerService.getAptmntByBuilding(selectedBldngId);
			setAptmntList(aptmnt_List);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String checkUsernameValidity() {
		try {
			logger.info("Entering checkUsernameValidity method:::::::::::::::::::::::::::::::::::::::::::::::");
			boolean flag = registerService.isValidUsername(username);
			if (flag) {
				UsernameValidityResponse resp = new UsernameValidityResponse("Username is available", flag);
				setResponse(resp);
				return SUCCESS;
			} else {
				UsernameValidityResponse resp = new UsernameValidityResponse("Username already taken", flag);
				setResponse(resp);
				return INPUT;
			}
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
			UsernameValidityResponse resp = new UsernameValidityResponse("An error occurred: "+e.getMessage(), false);
			setResponse(resp);
			return ERROR;
		}
	}
	
	public String registerUser() {
		try {
			logger.info("Entering registerUser method::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			HttpServletRequest httpRequest = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			HttpSession session = httpRequest.getSession();
			User user = registerService.registerUser(registerModel.getUser(), registerModel.getRoleId());
			if (user!=null) {
				session.setAttribute("user", user);
				respObj.put("status", "success");
				respObj.put("message", "Registration Succesfull");
				logger.info("User Registration Successfull::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
				logger.info("Exiting registerUser method::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
				return SUCCESS;
			} else {
				respObj.put("status", "error");
				respObj.put("message", "Registration Failed!!");
				logger.info("User Registration Failed!!!!!::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
				logger.info("Exiting registerUser method::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
				return ERROR;
			}
		} catch (Exception e) {
			respObj.put("success", false);
			respObj.put("Message", "Registration Failed!!");
			respObj.put("errorMsg", e.getMessage());
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
		
		
	}
	
	public List<APARTMENT_MST> getAptmntList() {
		return aptmntList;
	}

	public void setAptmntList(List<APARTMENT_MST> aptmntList) {
		this.aptmntList = aptmntList;
	}

	public Integer getSelectedBldngId() {
		return selectedBldngId;
	}

	public void setSelectedBldngId(Integer selectedBldngId) {
		this.selectedBldngId = selectedBldngId;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<BUILDING_MST> getBldngList() {
		return bldngList;
	}

	public void setBldngList(List<BUILDING_MST> bldngList) {
		this.bldngList = bldngList;
	}

	public List<RoleDTO> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleDTO> roleList) {
		this.roleList = roleList;
	}

	public Integer getSelectedRoleId() {
		return selectedRoleId;
	}

	public void setSelectedRoleId(Integer selectedRoleId) {
		this.selectedRoleId = selectedRoleId;
	}

	public UsernameValidityResponse getResponse() {
		return response;
	}

	public void setResponse(UsernameValidityResponse response) {
		this.response = response;
	}

	public RegisterModel getRegisterModel() {
		return registerModel;
	}

	public void setRegisterModel(RegisterModel registerModel) {
		this.registerModel = registerModel;
	}

	public Map<String,Object> getRespObj() {
		return respObj;
	}

	public void setRespObj(Map<String,Object> respObj) {
		this.respObj = respObj;
	}
	
}
