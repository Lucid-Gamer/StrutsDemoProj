package com.apptrove.ledgerly.login.dao;

import com.apptrove.ledgerly.admin.models.User;

public interface LoginDao {
    
    public User loginUser(String username,String password);

}
