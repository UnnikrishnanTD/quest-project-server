package com.quest.app.vo;

import java.util.Date;

public class UserVO {

private Long id;
	
	private String name;
	private String ppsNumber;
	private Date dob;
	private String mobile;
	
	public UserVO() {
	}
	public UserVO(Long id, String name, String ppsNumer, Date dob, String mobile) {
		super();
		this.id = id;
		this.name = name;
		this.ppsNumber = ppsNumer;
		this.dob = dob;
		this.mobile = mobile;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPpsNumber() {
		return ppsNumber;
	}
	public void setPpsNumber(String ppsNumber) {
		this.ppsNumber = ppsNumber;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
