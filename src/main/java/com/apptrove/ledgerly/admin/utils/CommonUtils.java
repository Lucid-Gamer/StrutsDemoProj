package com.apptrove.ledgerly.admin.utils;

import java.util.List;

import com.apptrove.ledgerly.admin.models.Role;
import com.apptrove.ledgerly.admin.payload.RoleDTO;

public class CommonUtils {

	public static RoleDTO roleToRoleDTO(Role role) {
		RoleDTO roleDto = new RoleDTO();
		try {
			roleDto.setRoleId(role.getRoleId());
			roleDto.setRoleName(role.getRoleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return roleDto;
	}
	
}
