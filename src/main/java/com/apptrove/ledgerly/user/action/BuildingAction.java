package com.apptrove.ledgerly.user.action;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class BuildingAction extends ActionSupport{

	private static final long serialVersionUID = 4628794721253433862L;

	private final static Logger logger = LogManager.getLogger(BuildingAction.class);
	
	private Map<String, Object> respObject;

	public Map<String, Object> getRespObject() {
		return respObject;
	}

	public void setRespObject(Map<String, Object> respObject) {
		this.respObject = respObject;
	}
	
	public BuildingAction() {
		// TODO Auto-generated constructor stub
	}
	
	public String getBuildingList() {
		return null;
	}
	
	public String getBldngAuthList() {
		return null;
	}
	
}
