package com.isteer.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDetailsDto {
	

	
	private String employeeId;
	private String roleId;
	private String tenantId;
	private String departmentId;
	@NotBlank(message = "PASSWORD FIELD CANNOT BE EMPTY")
    private String password;
	@NotBlank(message = "USER NAME FIELD CANNOT BE EMPTY")
	private String userName;
	@NotBlank(message = "FIRST FIELD CANNOT BE EMPTY")
	private String firstName;
	@NotBlank(message = "LAST NAME FIELD CANNOT BE EMPTY")
	private String lastName;
	@NotBlank(message = "EMAIL FIELD CANNOT BE EMPTY")
	private String email;
	@NotBlank(message = "PHONE NUMBER FIELD CANNOT BE EMPTY")
	private String phoneNumber;
	@NotBlank(message = "ADDRESS FIELD CANNOT BE EMPTY")
	private String address;
	@NotBlank(message = "DATE OF JOINING FIELD CANNOT BE EMPTY")
	private String dateOfJoining;
	@NotBlank(message = "JOB TITLE FIELD CANNOT BE EMPTY")
	private String jobTitle;

	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
