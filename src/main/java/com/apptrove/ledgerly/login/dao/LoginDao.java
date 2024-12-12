package com.apptrove.ledgerly.login.dao;

import org.hibernate.Session;

import com.apptrove.ledgerly.admin.models.User;

public interface LoginDao {
	
	public boolean validateUsername(String username);
    
    public User loginUser(String username,String password);
    
    public void updateLoginDate(User user);
    
    public void updateRetryCountFail(String username,Integer retryCountUpdate);
    
    public void updateRetryCountSuccess(User user,Session session);
    
    public void lockUserAccount(String username);
    
    public void unlockUserAccount(String username);
    
    public void getLockedAccounts();
    
    public Integer getLoginTries(String username);

}
