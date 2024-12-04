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
@Table(name = "com_ldgr_aptmnt_mst")
public class APARTMENT_MST implements Serializable{

	private static final long serialVersionUID = -8237973984857813979L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "aptmnt_id")
	private Integer aptmntId;
	
	@Column(name = "bldng_id")
	private Integer bldngId;
	
	@Column(name = "aptmnt_no")
	private String aptmntNo;
	
	@Column(name = "aptmnt_desc")
	private String aptmntDesc;
	
	@Column(name = "maker_id")
	private Integer makerId;
	
	@Column(name = "maker_date")
	private Date makerDate;
	
	@Column(name = "author_id")
	private Integer authorId;
	
	@Column(name = "author_date")
	private Date authorDate;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	public APARTMENT_MST() {
		// TODO Auto-generated constructor stub
	}

	public APARTMENT_MST(Integer aptmntId, Integer bldngId, String aptmntNo, String aptmntDesc, Integer makerId,
			Date makerDate, Integer authorId, Date authorDate, boolean isActive) {
		super();
		this.aptmntId = aptmntId;
		this.bldngId = bldngId;
		this.aptmntNo = aptmntNo;
		this.aptmntDesc = aptmntDesc;
		this.makerId = makerId;
		this.makerDate = makerDate;
		this.authorId = authorId;
		this.authorDate = authorDate;
		this.isActive = isActive;
	}

	public Integer getAptmntId() {
		return aptmntId;
	}

	public void setAptmntId(Integer aptmntId) {
		this.aptmntId = aptmntId;
	}

	public Integer getBldngId() {
		return bldngId;
	}

	public void setBldngId(Integer bldngId) {
		this.bldngId = bldngId;
	}

	public String getAptmntNo() {
		return aptmntNo;
	}

	public void setAptmntNo(String aptmntNo) {
		this.aptmntNo = aptmntNo;
	}

	public String getAptmntDesc() {
		return aptmntDesc;
	}

	public void setAptmntDesc(String aptmntDesc) {
		this.aptmntDesc = aptmntDesc;
	}

	public Integer getMakerId() {
		return makerId;
	}

	public void setMakerId(Integer makerId) {
		this.makerId = makerId;
	}

	public Date getMakerDate() {
		return makerDate;
	}

	public void setMakerDate(Date makerDate) {
		this.makerDate = makerDate;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Date getAuthorDate() {
		return authorDate;
	}

	public void setAuthorDate(Date authorDate) {
		this.authorDate = authorDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "APARTMENT_MST [aptmntId=" + aptmntId + ", bldngId=" + bldngId + ", aptmntNo=" + aptmntNo
				+ ", aptmntDesc=" + aptmntDesc + ", makerId=" + makerId + ", makerDate=" + makerDate + ", authorId="
				+ authorId + ", authorDate=" + authorDate + ", isActive=" + isActive + "]";
	}
	
	
	
}

