package com.apptrove.ledgerly.register.dao;

import java.util.List;

import com.apptrove.ledgerly.admin.models.BUILDING_MST;
import com.apptrove.ledgerly.admin.models.User;
import com.apptrove.ledgerly.admin.payload.RoleDTO;

public interface RegisterDao {

	public User registerUser(User newUser);
	
	public List<RoleDTO> getAllRoles();
	
	public List<BUILDING_MST> getAllBuidings();
}
