package com.apptrove.ledgerly.login.dao;

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
            String hql1 = "SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM User WHERE username = :username";
            Query<?> query = session.createQuery(hql1);
            query.setParameter("username", username);
            Boolean flag = (Boolean) query.getSingleResult();
            if (flag) {
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
            }
            logger.info("Incorrect username: "+username+" ::::::::::::::::::::::::::::::::::::::::::");
            logger.info("Exiting loginUser method::::::::::::::::::::::::::::::::::::::::::"); 
            return null;
        } catch (Exception e) {
            logger.error("An error occurred: "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
}
