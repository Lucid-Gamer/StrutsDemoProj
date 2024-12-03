package com.apptrove.ledgerly.register.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.apptrove.ledgerly.admin.models.BUILDING_MST;
import com.apptrove.ledgerly.admin.models.User;
import com.apptrove.ledgerly.admin.payload.RoleDTO;
import com.apptrove.ledgerly.register.service.RegisterServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport{

	private static final long serialVersionUID = -1496885308634234904L;
	
	private static final Logger logger = LogManager.getLogger(RegisterAction.class);
	
	private User user;
	
	private List<RoleDTO> roleList = new ArrayList<RoleDTO>();
	
	private List<BUILDING_MST> bldngList = new ArrayList<BUILDING_MST>();
	
	private Integer selectedRoleId;
	
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
	
	public String getAllBldngs() {
		return SUCCESS;
	}

	public List<BUILDING_MST> getBldngList() {
		return bldngList;
	}



	public void setBldngList(List<BUILDING_MST> bldngList) {
		this.bldngList = bldngList;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	
	
}
