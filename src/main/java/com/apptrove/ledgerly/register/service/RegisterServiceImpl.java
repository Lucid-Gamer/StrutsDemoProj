package com.apptrove.ledgerly.register.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.apptrove.ledgerly.admin.models.BUILDING_MST;
import com.apptrove.ledgerly.admin.models.User;
import com.apptrove.ledgerly.admin.payload.RoleDTO;
import com.apptrove.ledgerly.register.dao.RegisterDao;
import com.apptrove.ledgerly.register.dao.RegisterDaoImpl;

public class RegisterServiceImpl implements RegisterService{

	private static final Logger logger = LogManager.getLogger(RegisterServiceImpl.class);
	
	private RegisterDaoImpl registerDaoImpl = new RegisterDaoImpl(); 
	
	@Override
	public List<RoleDTO> getAllRoles() {
		List<RoleDTO> resList = new ArrayList<RoleDTO>();
		try {
			logger.info("Inside getAllRoles method::::::::::::::::::::::::::::::");
			resList = registerDaoImpl.getAllRoles();
			logger.info("Got a list of roles of size: "+resList.size());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return resList;
	}

	@Override
	public User registerUser(User newUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BUILDING_MST> getAllBuildings() {
		return this.registerDaoImpl.getAllBuidings();
	}

}
