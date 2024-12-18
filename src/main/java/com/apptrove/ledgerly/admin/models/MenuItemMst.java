package com.apptrove.ledgerly.admin.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "com_ldgr_dbrd_menu_items_mst")
public class MenuItemMst implements Serializable{

	private static final long serialVersionUID = 3715113576170086062L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "menu_id")
	private Integer menuId;
	
	@Column(name = "menu_item_name")
	private String menuItemName;

	@Column(name = "menu_item_name_id")
	private String menuItemNameId;
	
	@Column(name = "maker_cd")
	private Integer makerCd;
	
	@Column(name = "maker_dt")
	private Date makerDt;
	
	@Column(name = "author_cd")
	private Integer authorCd;
	
	@Column(name = "author_dt")
	private Date authorDt;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	public MenuItemMst() {
		// TODO Auto-generated constructor stub
	}

	public MenuItemMst(Integer id, Integer menuId, String menuItemName, String menuItemNameId, Integer makerCd,
			Date makerDt, Integer authorCd, Date authorDt, Boolean isActive) {
		this.id = id;
		this.menuId = menuId;
		this.menuItemName = menuItemName;
		this.menuItemNameId = menuItemNameId;
		this.makerCd = makerCd;
		this.makerDt = makerDt;
		this.authorCd = authorCd;
		this.authorDt = authorDt;
		this.isActive = isActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuItemName() {
		return menuItemName;
	}

	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
	}

	public Integer getMakerCd() {
		return makerCd;
	}

	public void setMakerCd(Integer makerCd) {
		this.makerCd = makerCd;
	}

	public Date getMakerDt() {
		return makerDt;
	}

	public void setMakerDt(Date makerDt) {
		this.makerDt = makerDt;
	}

	public Integer getAuthorCd() {
		return authorCd;
	}

	public void setAuthorCd(Integer authorCd) {
		this.authorCd = authorCd;
	}

	public Date getAuthorDt() {
		return authorDt;
	}

	public void setAuthorDt(Date authorDt) {
		this.authorDt = authorDt;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getMenuItemNameId() {
		return menuItemNameId;
	}

	public void setMenuItemNameId(String menuItemNameId) {
		this.menuItemNameId = menuItemNameId;
	}

	@Override
	public String toString() {
		return "MenuItemMst [id=" + id + ", menuId=" + menuId + ", menuItemName=" + menuItemName + ", menuItemNameId="
				+ menuItemNameId + ", makerCd=" + makerCd + ", makerDt=" + makerDt + ", authorCd=" + authorCd
				+ ", authorDt=" + authorDt + ", isActive=" + isActive + "]";
	}
	
}
