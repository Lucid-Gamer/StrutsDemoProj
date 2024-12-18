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
@Table(name = "com_ldgr_dbrd_menu_mst")
public class MenuMst implements Serializable{

	private static final long serialVersionUID = -6839914656022962178L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "menu_id")
	private Integer menuId;
	
	@Column(name = "menu_name")
	private String menuName;

	@Column(name = "menu_tab_id")
	private String menuTabId;
	
	@Column(name = "maker_cd")
	private Integer makerCd;
	
	@Column(name = "maker_dt")
	private Date makerDt;
	
	@Column(name = "author_cd")
	private Integer authorCd;
	
	@Column(name = "author_dt")
	private Date authorDt;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	public MenuMst() {
		// TODO Auto-generated constructor stub
	}

	public MenuMst(Integer id, Integer menuId, String menuName, String menuTabId, Integer makerCd, Date makerDt,
			Integer authorCd, Date authorDt, boolean isActive) {
		this.id = id;
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuTabId = menuTabId;
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

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getMenuTabId() {
		return menuTabId;
	}

	public void setMenuTabId(String menuTabId) {
		this.menuTabId = menuTabId;
	}

	@Override
	public String toString() {
		return "MenuMst [id=" + id + ", menuId=" + menuId + ", menuName=" + menuName + ", menuTabId=" + menuTabId
				+ ", makerCd=" + makerCd + ", makerDt=" + makerDt + ", authorCd=" + authorCd + ", authorDt=" + authorDt
				+ ", isActive=" + isActive + "]";
	}

	
	
	

}
