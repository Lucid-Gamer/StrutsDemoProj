package com.apptrove.ledgerly.menu.dao;

import java.awt.MenuItem;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.apptrove.ledgerly.admin.models.MenuItemMst;
import com.apptrove.ledgerly.admin.models.MenuMst;
import com.apptrove.ledgerly.database.utils.DatabaseUtils;

public class MenuDaoImpl implements MenuDao{

	private static final Logger logger = LogManager.getLogger(MenuDaoImpl.class);
	
	@Override
	public List<MenuMst> getMenuHeader() {
		List<MenuMst> menuHeaderList = new ArrayList<MenuMst>();
		try (Session session = DatabaseUtils.getSessionFactory().openSession()){
			logger.info("Inside getMenuHeader method:::::::::::::::::::::::::::::::::::::::::::::::::::::");
			String hql = "FROM MenuMst";
			Query<MenuMst> query = session.createQuery(hql);
			menuHeaderList = query.getResultList();
			logger.info("Acquired "+menuHeaderList.size()+" menu headers:::::::::::::::::::::::::::::::::");
			logger.info("Exiting getMenuHeader method:::::::::::::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return menuHeaderList;
	}

	@Override
	public List<MenuItemMst> getMenuOptions() {
		List<MenuItemMst> menuItemList = new ArrayList<MenuItemMst>();
		try (Session session = DatabaseUtils.getSessionFactory().openSession()){
			logger.info("Inside getMenuOptions method:::::::::::::::::::::::::::::::::::::::::::::::::::::");
			String hql = "FROM MenuItemMst";
			Query<MenuItemMst> query = session.createQuery(hql);
			menuItemList = query.getResultList();
			logger.info("Acquired "+menuItemList.size()+" menu options:::::::::::::::::::::::::::::::::::::");
			logger.info("Exiting getMenuOptions method:::::::::::::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return menuItemList;
	}

}
