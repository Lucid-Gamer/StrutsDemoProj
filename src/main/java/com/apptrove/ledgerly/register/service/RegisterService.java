package com.apptrove.ledgerly.register.service;

import java.util.List;

import com.apptrove.ledgerly.admin.models.BUILDING_MST;
import com.apptrove.ledgerly.admin.models.User;
import com.apptrove.ledgerly.admin.payload.RoleDTO;

public interface RegisterService {

	public List<RoleDTO> getAllRoles();
	
	public User registerUser(User newUser);
	
	public List<BUILDING_MST> getAllBuildings();
	
}
