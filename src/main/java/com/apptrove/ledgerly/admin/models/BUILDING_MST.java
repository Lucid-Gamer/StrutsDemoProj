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
@Table(name = "com_ldgr_bldng_mst")
public class BUILDING_MST implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bldng_id")
	private Integer bldngId;
	
	@Column(name = "bldng_name")
	private String bldngName;
	
	@Column(name = "bldng_block")
	private String bldngBlock;
	
	@Column(name = "no_of_apartments")
	private Integer noOfApartments;
	
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
	
	public BUILDING_MST() {
		// TODO Auto-generated constructor stub
	}

	public BUILDING_MST(Integer bldngId, String bldngName, String bldngBlock, Integer noOfApartments, Integer makerId,
			Date makerDate, Integer authorId, Date authorDate, boolean isActive) {
		super();
		this.bldngId = bldngId;
		this.bldngName = bldngName;
		this.bldngBlock = bldngBlock;
		this.noOfApartments = noOfApartments;
		this.makerId = makerId;
		this.makerDate = makerDate;
		this.authorId = authorId;
		this.authorDate = authorDate;
		this.isActive = isActive;
	}

	public Integer getBldngId() {
		return bldngId;
	}

	public void setBldngId(Integer bldngId) {
		this.bldngId = bldngId;
	}

	public String getBldngName() {
		return bldngName;
	}

	public void setBldngName(String bldngName) {
		this.bldngName = bldngName;
	}

	public String getBldngBlock() {
		return bldngBlock;
	}

	public void setBldngBlock(String bldngBlock) {
		this.bldngBlock = bldngBlock;
	}

	public Integer getNoOfApartments() {
		return noOfApartments;
	}

	public void setNoOfApartments(Integer noOfApartments) {
		this.noOfApartments = noOfApartments;
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
		return "BUILDING_MST [bldngId=" + bldngId + ", bldngName=" + bldngName + ", bldngBlock=" + bldngBlock
				+ ", noOfApartments=" + noOfApartments + ", makerId=" + makerId + ", makerDate=" + makerDate
				+ ", authorId=" + authorId + ", authorDate=" + authorDate + ", isActive=" + isActive + "]";
	}
	
	
	
	
}
