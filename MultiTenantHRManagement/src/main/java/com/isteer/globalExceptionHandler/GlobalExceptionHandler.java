package com.isteer.globalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.isteer.dto.ErrorMessageDto;
import com.isteer.enums.HrManagementEnum;
import com.isteer.exception.TenantIdNullException;

@ControllerAdvice
//i used this annotation to display the data in json form directly. 
@ResponseBody
public class GlobalExceptionHandler {
	@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessageDto MethodArgumentNotValidException(org.springframework.web.bind.MethodArgumentNotValidException e) {
     ErrorMessageDto invaildOperation = new ErrorMessageDto();
		invaildOperation.setErrorCode(9321);
		invaildOperation.setErrorMessage(e.getBindingResult().getFieldError().getDefaultMessage());
		return invaildOperation;
	}
	
	@ExceptionHandler(TenantIdNullException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessageDto TenantIdNullException(TenantIdNullException e) {
     ErrorMessageDto invaildOperation = new ErrorMessageDto();
		invaildOperation.setErrorCode(HrManagementEnum.Tenant_id_null.getStatusCode());
		invaildOperation.setErrorMessage(HrManagementEnum.Tenant_id_null.getStatusMessage());
		return invaildOperation;
	}
	
	@ExceptionHandler(com.isteer.exception.DepartmentIdNullException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessageDto DepartmentIdNullException(com.isteer.exception.DepartmentIdNullException e) {
     ErrorMessageDto invaildOperation = new ErrorMessageDto();
		invaildOperation.setErrorCode(HrManagementEnum.Department_id_null.getStatusCode());
		invaildOperation.setErrorMessage(HrManagementEnum.Department_id_null.getStatusMessage());
		return invaildOperation;
	}
	
	@ExceptionHandler(org.springframework.dao.DuplicateKeyException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessageDto DuplicateKeyException(org.springframework.dao.DuplicateKeyException e) {
     ErrorMessageDto invaildOperation = new ErrorMessageDto();
		invaildOperation.setErrorCode(HrManagementEnum.Department_already_found.getStatusCode());
		invaildOperation.setErrorMessage(HrManagementEnum.Department_already_found.getStatusMessage());
		return invaildOperation;
	}
	
}
