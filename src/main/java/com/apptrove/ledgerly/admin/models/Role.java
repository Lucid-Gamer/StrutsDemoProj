package com.apptrove.ledgerly.admin.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "com_ldgr_role_mst")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer roleId;
	
	@Column(name = "role_name")
	private String roleName;
	
	@Column(name = "created_on")
	private Date createdOn;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	public Role() {
		// TODO Auto-generated constructor stub
	}

	public Role(Integer roleId, String roleName, Date createdOn, boolean isActive) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.createdOn = createdOn;
		this.isActive = isActive;
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

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", createdOn=" + createdOn + ", isActive="
				+ isActive + "]";
	}
	
}
