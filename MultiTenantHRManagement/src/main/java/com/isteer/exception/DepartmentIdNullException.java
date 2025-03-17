package com.isteer.exception;

import com.isteer.enums.HrManagementEnum;

public class DepartmentIdNullException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 private final HrManagementEnum error;
		public DepartmentIdNullException(HrManagementEnum tickectIdException) {
			super(tickectIdException.getStatusMessage());
			   this.error = tickectIdException;
		}
		
		public HrManagementEnum getError() {
			return error;
		}

}
