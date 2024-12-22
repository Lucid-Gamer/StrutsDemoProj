package com.apptrove.ledgerly.user.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.apptrove.ledgerly.admin.models.User;
import com.apptrove.ledgerly.database.utils.DatabaseUtils;
import com.apptrove.ledgerly.user.dao.UserDao;
import com.opensymphony.xwork2.ActionContext;

public class UserDaoImpl implements UserDao {

	private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
	
	@Override
	public User makerMethod(User user,Integer roleId) {
		HttpServletRequest httpRequest = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpSession httpSession = httpRequest.getSession();
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.YEAR, 4);
		Date validTill = calendar.getTime();
		try (Session session = DatabaseUtils.getSessionFactory().openSession()){
			session.beginTransaction();
			logger.info("Inside makerMethod for username: "+user.getUsername()+" :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			String hql1 = "SELECT COUNT(r.roleId) FROM Role r WHERE r.roleId = :roleId";
			Query<Long> query1 = session.createQuery(hql1, Long.class);
			query1.setParameter("roleId", roleId);
			Long count = query1.getSingleResult();
			
			if (count == null || count == 0) {
				logger.info("Role with role id: "+roleId+" does not exist!");
				httpSession.removeAttribute("user");
				logger.info("User logged out for Parameter Manipulation");
				throw new IllegalArgumentException("Invalid Role Id: "+roleId);
			}
			
			user.setAccountLocked(true);
			user.setIsActive(false);
			user.setCredentialBlocked(true);
			user.setValidTill(validTill);
			
			User makerUser = (User) httpSession.getAttribute("user");
			user.setMakerCd(makerUser.getUserId());
			user.setMakerDt(now);
			session.save(user);
			
			
			
			
			session.getTransaction().commit();
			
			
			logger.info("Exiting makerMethod for username: "+user.getUsername()+" :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.info("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> unauthorizedUserList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User authorizeUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
