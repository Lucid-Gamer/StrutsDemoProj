package com.apptrove.ledgerly.user.dao;

import java.util.List;

import com.apptrove.ledgerly.admin.models.User;
import com.apptrove.ledgerly.admin.payload.UpdateModel;

public interface UserDao {

	public User makerMethod(User user,Integer roleId);
	
	public boolean updateUser(UpdateModel updateModel);
	
	public List<User> unauthorizedUserList();
	
	public boolean authorizeUser(Integer userId);
	
	public boolean existsByRoleId(Integer roleId);
	
	public boolean existsByUserId(Integer userId);
	
	public boolean rejectUser(Integer userId);
	
	public List<User> getAllActiveUsers();
	
	public boolean deactivateUser(Integer userId);
}
