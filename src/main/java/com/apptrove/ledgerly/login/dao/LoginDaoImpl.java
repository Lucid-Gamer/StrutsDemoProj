package com.apptrove.ledgerly.login.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.apptrove.ledgerly.admin.models.User;
import com.apptrove.ledgerly.database.utils.DatabaseUtils;

public class LoginDaoImpl implements LoginDao{

    private static final Logger logger = LogManager.getLogger(LoginDaoImpl.class); 

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User loginUser(String username, String password) {
        User user = new User();
        try (Session session = DatabaseUtils.getSessionFactory().openSession()){
            logger.info("Inside loginUser method::::::::::::::::::::::::::::::::::::::::::");
                String hql2 = "FROM User WHERE username=:username";
                Query<User> query2 = session.createQuery(hql2,User.class);
                query2.setParameter("username", username);
                user = query2.uniqueResult();
                if (passwordEncoder.matches(password, user.getPassword())) {
                    logger.info("Succesfull Login for username: "+username+" ::::::::::::::::::::::::::::::::::::::::::");
                    logger.info("Exiting loginUser method::::::::::::::::::::::::::::::::::::::::::");
                    return user;
                } else {
                    logger.info("Incorrect Password for username: "+username+" ::::::::::::::::::::::::::::::::::::::::::");
                    logger.info("Exiting loginUser method::::::::::::::::::::::::::::::::::::::::::");
                    return null;
                }
        } catch (Exception e) {
            logger.error("An error occurred: "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

	@Override
	public void updateLoginDate(User user) {
		Date now = new Date();
		try (Session session = DatabaseUtils.getSessionFactory().openSession()){
			logger.info("Inside method updateLoginDate for username: "+user.getUsername()+" :::::::::::::::::::::::::::::::::::::::::::::::");
			session.beginTransaction();
			String hql = "UPDATE User SET lastLoginDate = :lastLoginDate, loginTries = :loginTries WHERE username = :username AND userId = :userId";
			Query<Integer> query = session.createQuery(hql);
			query.setParameter("lastLoginDate", now);
			query.setParameter("username", user.getUsername());
			query.setParameter("userId", user.getUserId());
			query.setParameter("loginTries", 0);
			query.executeUpdate();
			session.getTransaction().commit();
			logger.info("Exiting method updateLoginDate for username: "+user.getUsername()+"and lastLoginDate: "+now.toString()+" :::::::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void updateRetryCountFail(String username,Integer retryCountUpdated) {
		Date now = new Date();
		try (Session session = DatabaseUtils.getSessionFactory().openSession()){
			logger.info("Inside updateRetryCountFail method for username: "+username+" :::::::::::::::::::::::::::::::::::::::::::::::");
			session.beginTransaction();
			String hql = "UPDATE User SET loginTries = :loginTries, lastLoginDate = :lastLoginDate WHERE username = :username";
			Query<?> query = session.createQuery(hql);
			query.setParameter("username", username);
			query.setParameter("loginTries", retryCountUpdated);
			query.setParameter("lastLoginDate", now);
			query.executeUpdate();
			session.getTransaction().commit();
			logger.info("Exiting method updateRetryCountFail for username: "+username+"and lastLoginDate: "+now.toString()+" :::::::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void updateRetryCountSuccess(User user,Session session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateUsername(String username) {
		boolean flag = false; 
		try (Session session = DatabaseUtils.getSessionFactory().openSession()){
			logger.info("Inside validateUsername method for username: "+username+" :::::::::::::::::::::::::::::::::::::::::::::::");
			String hql = "SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM User WHERE username = :username";
			Query<?> query = session.createQuery(hql);
			query.setParameter("username", username);
			flag = (Boolean) query.getSingleResult();
			logger.info("Exiting validateUsername method for username: "+username+" :::::::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public void lockUserAccount(String username) {
		Date now = new Date();
		try (Session session = DatabaseUtils.getSessionFactory().openSession()){
			logger.info("Inside lockUserAccount method for username: "+username+" :::::::::::::::::::::::::::::::::::::::::::::::");
			session.beginTransaction();
			String hql = "UPDATE User SET accountLocked = :accountLocked,loginTries = :loginTries, lastLoginDate = :lastLoginDate WHERE username = :username";
			Query<?> query = session.createQuery(hql);
			query.setParameter("username", username);
			query.setParameter("accountLocked", true);
			query.setParameter("loginTries", 3);
			query.setParameter("lastLoginDate", now);
			query.executeUpdate();
			session.getTransaction().commit();
			logger.info("Exiting lockUserAccount method for username: "+username+" :::::::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public boolean unlockUserAccount(String username) {
		boolean flag = false;
		try (Session session = DatabaseUtils.getSessionFactory().openSession()){
			logger.info("Inside unlockUserAccount method for username: "+username+" :::::::::::::::::::::::::::::::::::::::::::::::");
			session.beginTransaction();
			String hql = "Update User set accountLocked = :accountLocked , loginTries = :loginTries WHERE username = :username";
			Query<?> query = session.createQuery(hql);
			query.setParameter("username", username);
			query.setParameter("accountLocked",false);
			query.setParameter("loginTries",0);
			Integer res = query.executeUpdate();
			flag = res > 0 ? true : false;
			if (flag) {
				logger.info("Successfully unlocked User account with username: "+username+" :::::::::::::::::::::::::::::::::::::::::::::::");
			}
			session.getTransaction().commit();
			logger.info("Exiting unlockUserAccount method:::::::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return flag ;
	}

	@Override
	public List<User> getLockedAccounts() {
		List<User> lockedAccountList = new ArrayList<User>();
		try (Session session = DatabaseUtils.getSessionFactory().openSession()){
			logger.info("Inside getLockedAccounts method:::::::::::::::::::::::::::::::::::::::::::::::");
			String hql = "FROM User WHERE accountLocked = :accountLocked";
			Query<?> query = session.createQuery(hql);
			query.setParameter("accountLocked", true);
			lockedAccountList = (List<User>) query.getResultList();
			logger.info("Found "+lockedAccountList.size()+" locked Accounts:::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			logger.info("Exiting getLockedAccounts method:::::::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();		}
		return lockedAccountList;
	}

	@Override
	public Integer getLoginTries(String username) {
		try (Session session = DatabaseUtils.getSessionFactory().openSession()){
			logger.info("Inside getLoginTries method for username: "+username+" :::::::::::::::::::::::::::::::::::::::::::::::");
			String hql = "SELECT loginTries FROM User WHERE username = :username";
			Query<?> query = session.createQuery(hql);
			query.setParameter("username", username);
			Integer count = (Integer) query.uniqueResult();
			logger.info("Exiting getLoginTries method for username: "+username+" :::::::::::::::::::::::::::::::::::::::::::::::");
			return count;
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
    
}
