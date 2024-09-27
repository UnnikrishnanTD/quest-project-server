package com.quest.app.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user_info")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String ppsNumer;
	private Date dob;
	private String mobile;
	
	public User() {
		
	}
	public User(Long id, String name, String ppsNumer, Date dob, String mobile) {
		super();
		this.id = id;
		this.name = name;
		this.ppsNumer = ppsNumer;
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
	public String getPpsNumer() {
		return ppsNumer;
	}
	public void setPpsNumer(String ppsNumer) {
		this.ppsNumer = ppsNumer;
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
