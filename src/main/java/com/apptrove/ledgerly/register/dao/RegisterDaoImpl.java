package com.apptrove.ledgerly.register.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.apptrove.ledgerly.admin.models.BUILDING_MST;
import com.apptrove.ledgerly.admin.models.Role;
import com.apptrove.ledgerly.admin.models.User;
import com.apptrove.ledgerly.admin.payload.RoleDTO;
import com.apptrove.ledgerly.admin.utils.CommonUtils;
import com.apptrove.ledgerly.database.utils.DatabaseUtils;

public class RegisterDaoImpl implements RegisterDao {
	
	private static final Logger logger = LogManager.getLogger(RegisterDaoImpl.class);

	@Override
	public User registerUser(User newUser) {
		User user = new User();
		try {
			
		} catch (Exception e) {
			
		}
		
		return null;
	}

	@Override
	public List<RoleDTO> getAllRoles() {
		List<RoleDTO> roleDtoList = new ArrayList<RoleDTO>();
		List<Role> roleList = new ArrayList<Role>();
		logger.info("Inside getAllRoles method:::::::::::::::::::::::::::::::::::::");
		try (Session session = DatabaseUtils.getSessionFactory().openSession()){
			StringBuilder hqlBuilder = new StringBuilder();
			hqlBuilder.append("FROM Role");
			Query<Role> query = session.createQuery(hqlBuilder.toString(),Role.class);
			roleList = query.getResultList();
			for(Role role : roleList) {
				logger.info(role.toString());
				roleDtoList.add(CommonUtils.roleToRoleDTO(role));
			}
			logger.info("Exiting getAllRoles method:::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return roleDtoList;
	}

	@Override
	public List<BUILDING_MST> getAllBuidings() {
		List<BUILDING_MST> bldngList = new ArrayList<BUILDING_MST>();
		try (Session session = DatabaseUtils.getSessionFactory().openSession()){
			logger.info("Inside getAllBuidings method:::::::::::::::::::::::::::::::::::::");
			StringBuilder hqlBuilder = new StringBuilder();
			hqlBuilder.append("FROM BUILDING_MST");
			Query<BUILDING_MST> query = session.createQuery(hqlBuilder.toString(),BUILDING_MST.class);
			bldngList = query.getResultList();
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return bldngList;
	}
	
	

}
