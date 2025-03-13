package com.isteer.entity;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;

@Component
public class Tenants {

	private String tenantId;
	@NotBlank(message = "TENANT NAME FIELD SHOULD NOT BE EMPTY")
	private String tenantName;
	@NotBlank(message = "TENANT ADDRESS FIELD SHOULD NOT BE EMPTY")
	private String address;
	@NotBlank(message = "TENANT EMAIL FIELD SHOULD NOT BE EMPTY")
	private String email;
	@NotBlank(message = "TENANT PHONENUMBER FIELD SHOULD NOT BE EMPTY")
	private String phoneNumber;
	@NotBlank(message = "TENANT COUNTRY FIELD SHOULD NOT BE EMPTY")
	private String tenantCountry;
	@NotBlank(message = "TENANT STATE FIELD SHOULD NOT BE EMPTY")
	private String tenantState;
	@NotBlank(message = "TENANT CITY FIELD SHOULD NOT BE EMPTY")
	private String tenantCity;
	
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getTenantName() {
		return tenantName;
	}
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getTenantCountry() {
		return tenantCountry;
	}
	public void setTenantCountry(String tenantCountry) {
		this.tenantCountry = tenantCountry;
	}
	public String getTenantState() {
		return tenantState;
	}
	public void setTenantState(String tenantState) {
		this.tenantState = tenantState;
	}
	public String getTenantCity() {
		return tenantCity;
	}
	public void setTenantCity(String tenantCity) {
		this.tenantCity = tenantCity;
	}
	


}
