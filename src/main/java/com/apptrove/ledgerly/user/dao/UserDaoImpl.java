package com.apptrove.ledgerly.user.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.apptrove.ledgerly.admin.models.User;
import com.apptrove.ledgerly.database.utils.DatabaseUtils;
import com.opensymphony.xwork2.ActionContext;

public class UserDaoImpl implements UserDao {

	private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
	
	private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public User makerMethod(User user,Integer roleId) {
		HttpServletRequest httpRequest = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpSession httpSession = httpRequest.getSession();
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.YEAR, 4);
		Date validTill = calendar.getTime();
		Transaction transaction = null;
		try (Session session = DatabaseUtils.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setAccountLocked(true);
			user.setIsActive(false);
			user.setCredentialBlocked(true);
			user.setValidTill(validTill);
			user.setCreatedOn(now);
			User makerUser = (User) httpSession.getAttribute("user");
			user.setMakerCd(makerUser.getUserId());
			user.setMakerDt(now);
			Integer userId = (Integer) session.save(user);
			user.setUserId(userId);
			String hql = "INSERT INTO com_ldgr_user_roles(role_id,user_id) values (:roleId,:userId)";
			Query<?> query = session.createNativeQuery(hql);
			query.setParameter("roleId", roleId);
			query.setParameter("userId", userId);
			int res = query.executeUpdate();
			transaction.commit();
			logger.info("Exiting makerMethod for username: "+user.getUsername()+" :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			if (transaction != null) {
	            transaction.rollback();
	        }
			logger.info("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> unauthorizedUserList() {
		List<User> unauthorizedUserList = new ArrayList<User>();
		try (Session session = DatabaseUtils.getSessionFactory().openSession()) {
			String hql = "FROM User WHERE isActive = :isActive AND accountLocked = :accountLocked AND credentialBlocked = :credentialBlocked AND authorCd IS NULL and authorDt IS NULL";
			Query<User> query = session.createQuery(hql);
			query.setParameter("isActive", false);
			query.setParameter("accountLocked", true);
			query.setParameter("credentialBlocked", true);
			unauthorizedUserList = query.getResultList();
		} catch (Exception e) {
			logger.info("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return unauthorizedUserList;
	}

	@Override
	public User authorizeUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsByRoleId(Integer roleId) {
		boolean flag = false;
		try (Session session = DatabaseUtils.getSessionFactory().openSession()){
			logger.info("Inside existsByRoleId method for role id: "+roleId+" ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			String hql = "SELECT COUNT(r.roleId) FROM Role r WHERE r.roleId = :roleId";
			Query<Long> query1 = session.createQuery(hql, Long.class);
			query1.setParameter("roleId", roleId);
			Long count = query1.getSingleResult();
			if (count == null || count == 0) {
				logger.info("Role with role id: "+roleId+" does not exist!");
				flag = false;
			}else {
				flag = true;
			}
			
			logger.info("Exiting existsByRoleId method :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.info("An error occurred: "+e.getMessage());
		}
		return flag;
	}

}
