package com.apptrove.ledgerly.user.dao;

import java.util.List;

import com.apptrove.ledgerly.admin.models.User;

public interface UserDao {

	public User makerMethod(User user,Integer roleId);
	
	public User updateUser(User user);
	
	public List<User> unauthorizedUserList();
	
	public boolean authorizeUser(Integer userId);
	
	public boolean existsByRoleId(Integer roleId);
	
	public boolean existsByUserId(Integer userId);
}
