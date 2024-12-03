package com.apptrove.ledgerly.admin.payload;

import java.io.Serializable;

public class RoleDTO implements Serializable{

	private static final long serialVersionUID = 4253335978095458616L;
	
	private Integer roleId;
	
	private String roleName;
	
	public RoleDTO() {
		// TODO Auto-generated constructor stub
	}

	public RoleDTO(Integer roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "RoleDTO [roleId=" + roleId + ", roleName=" + roleName + "]";
	}
	
	
	

}
