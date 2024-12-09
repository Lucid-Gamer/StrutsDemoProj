package com.apptrove.ledgerly.register.service;

import java.util.List;

import com.apptrove.ledgerly.admin.models.APARTMENT_MST;
import com.apptrove.ledgerly.admin.models.BUILDING_MST;
import com.apptrove.ledgerly.admin.models.User;
import com.apptrove.ledgerly.admin.payload.RoleDTO;

public interface RegisterService {

	public List<RoleDTO> getAllRoles();
	
	public User registerUser(User newUser,Integer roleId);
	
	public List<BUILDING_MST> getAllBuildings();
	
	public List<APARTMENT_MST> getAptmntByBuilding(Integer bldngId);
	
	public boolean isValidUsername(String username);
	
}
