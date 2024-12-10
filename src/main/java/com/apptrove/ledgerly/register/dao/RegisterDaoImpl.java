package com.apptrove.ledgerly.register.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.apptrove.ledgerly.admin.models.APARTMENT_MST;
import com.apptrove.ledgerly.admin.models.BUILDING_MST;
import com.apptrove.ledgerly.admin.models.Role;
import com.apptrove.ledgerly.admin.models.User;
import com.apptrove.ledgerly.admin.payload.RoleDTO;
import com.apptrove.ledgerly.admin.utils.CommonUtils;
import com.apptrove.ledgerly.database.utils.DatabaseUtils;

public class RegisterDaoImpl implements RegisterDao {
	
	private static final Logger logger = LogManager.getLogger(RegisterDaoImpl.class);
	
	private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public User registerUser(User user, Integer roleId) {
		Date today = new  Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.YEAR, 2);
		Date validTill = calendar.getTime();
		try (Session session = DatabaseUtils.getSessionFactory().openSession()){
			session.beginTransaction();
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setAccountLocked(false);
			user.setCredentialBlocked(false);
			user.setCreatedOn(today);
			user.setLoginTries(0);

			user.setIsActive(false);
			user.setValidTill(validTill);
			
			Integer userId = (Integer) session.save(user);
			
			String hql = "INSERT INTO com_ldgr_user_roles(role_id,user_id) values (:roleId,:userId)";
			Query<?> query = session.createNativeQuery(hql);
			query.setParameter("roleId", roleId);
			query.setParameter("userId", userId);
			int res = query.executeUpdate();
			session.getTransaction().commit();
			logger.info("User registered with ID: " + userId + " and Role ID: " + roleId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("An Error Occurred: "+e.getMessage());
		}		
		return user;
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

	@Override
	public List<APARTMENT_MST> getApartmentByBuilding(Integer bldngId) {
		List<APARTMENT_MST> aptList = new ArrayList<APARTMENT_MST>();
		try (Session session = DatabaseUtils.getSessionFactory().openSession()){
			logger.info("Inside getApartmentByBuilding method:::::::::::::::::::::::::::::::::::::::::::");
			String hql = "FROM APARTMENT_MST WHERE bldngId = :bldngId";
	        Query<APARTMENT_MST> query = session.createQuery(hql, APARTMENT_MST.class);
	        query.setParameter("bldngId", bldngId);
			aptList = query.getResultList();
			logger.info("Found List of aptmnts: "+aptList.size());
			logger.info("Exiting getApartmentByBuilding method:::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.info("An exception occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return aptList;
	}

	@Override
	public boolean checkUsernameValidity(String username) {
		boolean flag = false;
		try (Session session = DatabaseUtils.getSessionFactory().openSession()){
			logger.info("Inside checkUsernameValidity method:::::::::::::::::::::::::::::::::::::::::::");
			String hql = "SELECT CASE WHEN COUNT(*) > 0 THEN FALSE ELSE TRUE END FROM User WHERE username= :username";
			Query<?> query = session.createQuery(hql);
			query.setParameter("username",username);
			flag = (Boolean)query.getSingleResult();
			logger.info("Exiting checkUsernameValidity method:::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.info("An exception occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	
	

}
