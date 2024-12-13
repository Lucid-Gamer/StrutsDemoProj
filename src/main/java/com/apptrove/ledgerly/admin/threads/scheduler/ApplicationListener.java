package com.apptrove.ledgerly.admin.threads.scheduler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.apptrove.ledgerly.login.dao.LoginDaoImpl;

@WebListener
public class ApplicationListener implements ServletContextListener {

    private AccountUnlockerThreadService unlockerThreadService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Initialize the unlocker service when the application starts
        LoginDaoImpl loginDaoImpl = new LoginDaoImpl(); // your DAO initialization
        unlockerThreadService = new AccountUnlockerThreadService(loginDaoImpl);
        unlockerThreadService.startScheduler();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Stop the scheduler when the application is destroyed
        if (unlockerThreadService != null) {
            unlockerThreadService.stopScheduler();
        }
    }
}
