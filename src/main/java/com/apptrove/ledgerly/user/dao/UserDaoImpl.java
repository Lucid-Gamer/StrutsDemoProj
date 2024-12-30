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
	public User makerMethod(User user, Integer roleId) {
		HttpServletRequest httpRequest = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		HttpSession httpSession = httpRequest.getSession();
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.YEAR, 4);
		Date validTill = calendar.getTime();
		Transaction transaction = null;
		try (Session session = DatabaseUtils.getSessionFactory().openSession()) {
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
			logger.info("Exiting makerMethod for username: " + user.getUsername()
					+ " :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.info("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User updateUser(User user) {
		Transaction transaction = null;
		try (Session session = DatabaseUtils.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			logger.info("In updateUser method for userId: "+user.getUserId()+" :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			session.saveOrUpdate(user);
			transaction.commit();
			logger.info("Exiting updateUser method:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return user;
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
			logger.info("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}
		return unauthorizedUserList;
	}

	@Override
	public boolean authorizeUser(Integer userId) {
		HttpServletRequest httpRequest = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		HttpSession httpSession = httpRequest.getSession();
		Date currentDate = new Date();
		Transaction transaction = null;
		boolean flag = false;
		try (Session session = DatabaseUtils.getSessionFactory().openSession()) {
			logger.info("Inside authorizeUser method for user id: " + userId
					+ " ::::::::::::::::::::::::::::::::::::::::::");
			transaction = session.getTransaction();
			transaction.begin();
			User authorUser = (User) httpSession.getAttribute("user");
			String hql = "UPDATE User SET isActive = :isActive , credentialBlocked = :credentialBlocked , accountLocked = :accountLocked,  authorCd = :authorCd , authorDt = :authorDt WHERE userId = :userId";
			Query<?> query = session.createQuery(hql);
			query.setParameter("isActive", true);
			query.setParameter("credentialBlocked", false);
			query.setParameter("accountLocked", false);
			query.setParameter("authorCd", authorUser.getUserId());
			query.setParameter("authorDt", currentDate);
			query.setParameter("userId", userId);

			int rowsAffected = query.executeUpdate();
			logger.info("Rows affected: " + rowsAffected
					+ " :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			flag = rowsAffected > 0 ? true : false;
			logger.info(
					"Exiting authorizeUser method::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			transaction.commit();
		} catch (Exception e) {
			logger.info("An error occurred: " + e.getMessage());
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return flag;
	}

	@Override
	public boolean existsByRoleId(Integer roleId) {
		boolean flag = false;
		try (Session session = DatabaseUtils.getSessionFactory().openSession()) {
			logger.info("Inside existsByRoleId method for role id: " + roleId
					+ " ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			String hql = "SELECT COUNT(r.roleId) FROM Role r WHERE r.roleId = :roleId";
			Query<Long> query1 = session.createQuery(hql, Long.class);
			query1.setParameter("roleId", roleId);
			Long count = query1.getSingleResult();
			if (count == null || count == 0) {
				logger.info("Role with role id: " + roleId + " does not exist!");
				flag = false;
			} else {
				flag = true;
			}

			logger.info(
					"Exiting existsByRoleId method :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.info("An error occurred: " + e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean existsByUserId(Integer userId) {
		boolean flag = false;
		try (Session session = DatabaseUtils.getSessionFactory().openSession()) {
			logger.info("Inside existsByUserId method for user id: " + userId
					+ " ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			String hql = "SELECT COUNT(*) FROM User WHERE userId = :userId";
			Query<Long> query = session.createQuery(hql, Long.class);
			query.setParameter("userId", userId);
			Long result = query.uniqueResult();
			flag = result > 0 ? true : false;
			logger.info(
					"Exiting existsByUserId method:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.info("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean rejectUser(Integer userId) {
		HttpServletRequest httpRequest = (HttpServletRequest) ServletActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		HttpSession httpSession = httpRequest.getSession();
		Date currentDate = new Date();
		Transaction transaction = null;
		try (Session session = DatabaseUtils.getSessionFactory().openSession()) {
			logger.info("Inside rejectUser method for user id: " + userId
					+ " :::::::::::::::::::::::::::::::::::::::::::::::::");
			User authorUser = (User) httpSession.getAttribute("user");
			transaction = session.getTransaction();
			transaction.begin();
			String hql = "UPDATE User SET isActive = :isActive , credentialBlocked = :credentialBlocked , accountLocked = :accountLocked,  authorCd = :authorCd , authorDt = :authorDt WHERE userId = :userId";
			Query<?> query = session.createQuery(hql);
			query.setParameter("isActive", false);
			query.setParameter("credentialBlocked", true);
			query.setParameter("accountLocked", true);
			query.setParameter("authorCd", authorUser.getUserId());
			query.setParameter("authorDt", currentDate);
			query.setParameter("userId", userId);
			int res = query.executeUpdate();
			logger.info("Rows updated: "+res+" :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			logger.info("Exiting rejectUser method::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			transaction.commit();
			return res > 0 ? true : false;
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			return false;
		}
	}

	@Override
	public List<User> getAllActiveUsers() {
		List<User> userList = new ArrayList<User>();
		try (Session session = DatabaseUtils.getSessionFactory().openSession()){
			logger.info("Inside getAllActiveUsers method::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			String hql = "FROM User WHERE isActive = :isActive";
			Query<User> query = session.createQuery(hql,User.class);
			query.setParameter("isActive", true);
			userList = query.getResultList();
			logger.info("Found "+userList.size()+" active users:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			logger.info("Exiting getAllActiveUsers method:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.info("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return userList;
	}

}
