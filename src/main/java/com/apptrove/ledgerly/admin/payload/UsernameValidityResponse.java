package com.apptrove.ledgerly.admin.payload;

import java.io.Serializable;

public class UsernameValidityResponse implements Serializable{
	
	private static final long serialVersionUID = 6496568682870399814L;

	private String message;
	
	private boolean flag;
	
	public UsernameValidityResponse() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public UsernameValidityResponse(String message, boolean flag) {
		super();
		this.message = message;
		this.flag = flag;
	}

}
