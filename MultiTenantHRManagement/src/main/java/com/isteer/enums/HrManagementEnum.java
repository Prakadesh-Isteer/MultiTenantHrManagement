package com.isteer.enums;

public enum HrManagementEnum {

	Tenant_created_message(1111, "TENANT ADDED SUCCESSFULLY"),
	Tenant_creation_failed(9999,"TENANT ADDED FAILED"),
	Tenant_id_null(9067,"TENANT ID SHOULD BE PROVIDED"),
	No_list_of_tenansts(1121,"NO TENANT FOUND, PLEASE REGISTER TEANTS"),
	Tenant_updation_successfull(1232,"TENANT UPDATED SUCCESSFULLY"), 
	Tenant_valid_not_found(9321,"KINDLY PROVIDE THE VALID TENANT ID "),
	Tenant_deletion(1832, "REQUESTED TENANT HAVE BEEN DELETED SUCCESSFULLY"),
	TENANT_FAILED_DELETION(9023,"TENANT DELETION FAILED"),
	
     Department_created_message(1222,"DEPARTMENT CREATED SUCCESSFULLY"),
     Department_id_null(9822, "DEPARTMENT ID SHOULD BE PROVIDED"),
     DUPLICATE_KEY_EXCEPTION(9012,"ALREADY FOUND TRY ANOTHER"),
     DEPARTMENT_CREATION_FAILED(9888, "DEPARTMENT CREATION FAILED"),
     DEPARTMENT_UPDATION_SUCCESSFULL(1321, "DEPARTMENT UPDATED SUCCESSFULLY"),
     DEPARTMENT_VALID_NOT_FOUND(9082,"PROVIDED DEPARTMENT KEY IS NOT VAILD"),
     DEPARTMENT_DELETION(1238,"REQUESTED DEPARTMENT HAVE BEEN DELETED SUCCESSFULLY"),
     DEPARTMENT_DELETION_FAILED(9023,"DEPARTMENT DELETION FAILED"),

	USER_CREATED_SUCCESS(1231, "USER HAS BEEN CREATED SUCCESSFULLY"),
	USER_FAILED_CREATION(9032,"USER REGISTERATION FAILED"),
	Role_added(1232,"ROLE ADDED SUCCESSFULLY"),
	Role_not_added(9033,"ROLE NOT ADDED SOEMTHING WENT WRONG");

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