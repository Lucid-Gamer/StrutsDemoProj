package com.apptrove.ledgerly.admin.payload;

import java.io.Serializable;

import com.apptrove.ledgerly.admin.models.User;

public class RegisterModel implements Serializable{

	private static final long serialVersionUID = -1223649480592316285L;

	private User user;
	
	private Integer roleId;
	
	public RegisterModel() {
		// TODO Auto-generated constructor stub
	}

	public RegisterModel(User user, Integer roleId) {
		super();
		this.user = user;
		this.roleId = roleId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "RegisterModel [user=" + user + ", roleId=" + roleId + "]";
	}
	
	
}
