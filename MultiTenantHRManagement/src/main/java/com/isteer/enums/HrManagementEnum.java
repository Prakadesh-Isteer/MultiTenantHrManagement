package com.isteer.enums;

public enum HrManagementEnum {

	Tenant_created_message(1111, "TENANT ADDED SUCCESSFULLY"),
	Tenant_creation_failed(9999,"TENANT ADDED FAILED"),
	No_list_of_tenansts(1121,"NO TENANT FOUND, PLEASE REGISTER TEANTS"),
	Tenant_updation_successfull(1232,"TENANT UPDATED SUCCESSFULLY"), 
	Tenant_not_found(9321,"KINDLY PROVIDE THE VALID TENANT ID FOR UPDATION");

	

	int statusCode;
	String statusMessage;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	private HrManagementEnum(int statusCode, String statusMessage) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}


}