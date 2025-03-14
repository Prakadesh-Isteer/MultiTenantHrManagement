package com.isteer.exception;

import com.isteer.enums.HrManagementEnum;

public class TenantIdNullException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		 private final HrManagementEnum error;
			public TenantIdNullException(HrManagementEnum tickectIdException) {
				super(tickectIdException.getStatusMessage());
				   this.error = tickectIdException;
			}
			
			public HrManagementEnum getError() {
				return error;
			}

}
