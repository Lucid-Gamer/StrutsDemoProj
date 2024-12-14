package com.apptrove.ledgerly.admin.threads.scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.apptrove.ledgerly.admin.models.User;
import com.apptrove.ledgerly.login.dao.LoginDaoImpl;

public class AccountUnlockerThreadService implements Runnable {

	private final LoginDaoImpl loginDaoImpl;

	private static final Logger logger = LogManager.getLogger(AccountUnlockerThreadService.class);

	public AccountUnlockerThreadService(LoginDaoImpl loginDaoImpl) {
		super();
		this.loginDaoImpl = loginDaoImpl;
	}

	private ScheduledExecutorService accountUnlockerThread;

	public void startScheduler() {
		accountUnlockerThread = Executors.newScheduledThreadPool(1);
		AccountUnlockerThreadService accountUnlockerThreadService = new AccountUnlockerThreadService(loginDaoImpl);
		
		
		accountUnlockerThread.scheduleAtFixedRate(accountUnlockerThreadService, 0, 1, TimeUnit.MINUTES);
	}

	public void stopScheduler() {
		if (accountUnlockerThread != null) {
			accountUnlockerThread.shutdown();
			try {
				if (!accountUnlockerThread.awaitTermination(60, TimeUnit.SECONDS)) {
					accountUnlockerThread.shutdownNow();
															
				}
			} catch (InterruptedException e) {
				accountUnlockerThread.shutdownNow();
			}
		}
	}

	@Override
	public void run() {
		logger.info("Starting thread AccountUnlocker to start unlock service:::::::::::::::::::::::::::::::::::::::");
		List<User> userList = new ArrayList<User>();
		try {
			userList = loginDaoImpl.getLockedAccounts();
			logger.info("Found " + userList.size() + " locked account(s):::::::::::::::::::::::::::::::::::::::::");
			for (User user : userList) {
				Date now = new Date();
				long timeDiff = now.getTime() - user.getLastLoginDate().getTime();
				long oneHourDiff = 60 * 60 * 1000;

				if (timeDiff > oneHourDiff) {
					logger.info("Attempting to unlock account with username: " + user.getUsername()+ " :::::::::::::::::::::::::::::::::::::::::::::::");
					boolean flag = loginDaoImpl.unlockUserAccount(user.getUsername());
					if (flag) {
						logger.info("Account with username: "+user.getUsername()+" successfully unlocked!");
					} else {
						logger.error("Something went wrong. Try Again later.");
					}
				} else {
					logger.info("Will unlock account with username: "+user.getUsername()+" in "+timeDiff/(1000*60)+" minutes");
				}
			}
		} catch (Exception e) {
			logger.error("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}

	}

}
