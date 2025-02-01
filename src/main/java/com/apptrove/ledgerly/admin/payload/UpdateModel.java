package com.apptrove.ledgerly.admin.payload;

import java.util.Date;

public class UpdateModel {

	private Integer userId;
    private String username;
    private String emailId;
    private String contactNum;
    private Date validTill;

    public UpdateModel() {
        // Default constructor
    }

    public UpdateModel(String username, String emailId, String contactNum, Date validTill,Integer userId) {
        this.username = username;
        this.emailId = emailId;
        this.contactNum = contactNum;
        this.validTill = validTill;
        this.userId = userId;
    }

    // Standard Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getValidTill() {
        return validTill;
    }

    public void setValidTill(Date validTill) {
        this.validTill = validTill;
    }

    public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
    public String toString() {
        return "UpdateModel [username=" + username + ", emailId=" + emailId + 
               ", contactNum=" + contactNum + ", validTill=" + validTill + ", userId=" + userId + "]";
    }

    public boolean isEmpty() {
        return (username == null || username.isEmpty()) &&
               (emailId == null || emailId.isEmpty()) &&
               (contactNum == null || contactNum.isEmpty()) &&
               (validTill == null) && (userId != null);
    }
}
