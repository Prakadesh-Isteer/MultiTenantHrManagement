package com.isteer.entity;

import org.springframework.stereotype.Component;

@Component
public class Departments {
	private String tenantId;
	private String departmentId;
	private String departmentHeadId;
	private String departmentName;
	private String email;
	private String phoneNumber;
	private String description;
	
	public Departments() {
		// TODO Auto-generated constructor stub
	}
	
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentHeadId() {
		return departmentHeadId;
	}
	public void setDepartmentHeadId(String departmentHeadId) {
		this.departmentHeadId = departmentHeadId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Departments(String departmentId, String departmentHeadId, String departmentName, String email,
			String phoneNumber, String description) {
		super();
		this.departmentId = departmentId;
		this.departmentHeadId = departmentHeadId;
		this.departmentName = departmentName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.description = description;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	 

}
