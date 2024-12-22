package com.apptrove.ledgerly.admin.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 */
@Entity
@Table(name = "com_ldgr_user_mst")
public class User implements Serializable{

	private static final long serialVersionUID = 1347875974666731395L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email_id")
	private String emailId;
	
	@Column(name = "contact_num")
	private String contactNum;
	
	@DateTimeFormat
	@Column(name = "created_on")
	private Date createdOn;
	
	@Column(name = "valid_till")
	private Date validTill;
	
	@Column(name = "login_tries")
	private Integer loginTries;
	
	@Column(name = "last_login_date")
	private Date lastLoginDate;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	@Column(name = "credential_blocked")
	private Boolean credentialBlocked;
	
	@Column(name = "account_locked")
	private Boolean accountLocked;
	
	@Column(name = "aptmnt_id")
	private Integer apartmentId;
	
	@Column(name = "maker_cd")
	private Integer makerCd;

	@DateTimeFormat
	@Column(name = "maker_dt")
	private Date makerDt;
	
	@Column(name = "author_cd")
	private Integer authorCd;
	
	@DateTimeFormat
	@Column(name = "authorDt")
	private Date authorDt;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Integer userId, String firstName, String lastName, String username, String password, String emailId,
			String contactNum, Date createdOn, Date validTill, Integer loginTries, Date lastLoginDate, Boolean isActive,
			Boolean credentialBlocked, Boolean accountLocked, Integer apartmentId) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.emailId = emailId;
		this.contactNum = contactNum;
		this.createdOn = createdOn;
		this.validTill = validTill;
		this.loginTries = loginTries;
		this.lastLoginDate = lastLoginDate;
		this.isActive = isActive;
		this.credentialBlocked = credentialBlocked;
		this.accountLocked = accountLocked;
		this.apartmentId = apartmentId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getValidTill() {
		return validTill;
	}

	public void setValidTill(Date validTill) {
		this.validTill = validTill;
	}

	public Integer getLoginTries() {
		return loginTries;
	}

	public void setLoginTries(Integer loginTries) {
		this.loginTries = loginTries;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getCredentialBlocked() {
		return credentialBlocked;
	}

	public void setCredentialBlocked(Boolean credentialBlocked) {
		this.credentialBlocked = credentialBlocked;
	}

	public Boolean getAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(Boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public Integer getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(Integer apartmentId) {
		this.apartmentId = apartmentId;
	}

	public Integer getMakerCd() {
		return makerCd;
	}

	public void setMakerCd(Integer makerCd) {
		this.makerCd = makerCd;
	}

	public Integer getAuthorCd() {
		return authorCd;
	}

	public void setAuthorCd(Integer authorCd) {
		this.authorCd = authorCd;
	}
	
	public Date getMakerDt() {
		return makerDt;
	}

	public void setMakerDt(Date makerDt) {
		this.makerDt = makerDt;
	}

	public Date getAuthorDt() {
		return authorDt;
	}

	public void setAuthorDt(Date authorDt) {
		this.authorDt = authorDt;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", password=" + password + ", emailId=" + emailId + ", contactNum=" + contactNum
				+ ", createdOn=" + createdOn + ", validTill=" + validTill + ", loginTries=" + loginTries
				+ ", lastLoginDate=" + lastLoginDate + ", isActive=" + isActive + ", credentialBlocked="
				+ credentialBlocked + ", accountLocked=" + accountLocked + ", apartmentId="
				+ apartmentId + ", makerCd=" + makerCd + ", authorCd=" + authorCd + ", makerDt="
				+ makerDt + ", authorDt=" + authorDt + "]";
	}
	
	
	
}
