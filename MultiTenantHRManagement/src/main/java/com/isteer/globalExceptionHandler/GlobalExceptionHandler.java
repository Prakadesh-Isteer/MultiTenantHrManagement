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
	@ExceptionHandler(com.isteer.exception.EmployeeIdNullException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessageDto EmployeeIdNullException(com.isteer.exception.EmployeeIdNullException e) {
     ErrorMessageDto invaildOperation = new ErrorMessageDto();
		invaildOperation.setErrorCode(HrManagementEnum.Employee_id_null.getStatusCode());
		invaildOperation.setErrorMessage(HrManagementEnum.Employee_id_null.getStatusMessage());
		return invaildOperation;
	}
	
	
	@ExceptionHandler(org.springframework.dao.DuplicateKeyException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessageDto DuplicateKeyException(org.springframework.dao.DuplicateKeyException e) {
     ErrorMessageDto invaildOperation = new ErrorMessageDto();
		invaildOperation.setErrorCode(HrManagementEnum.DUPLICATE_KEY_EXCEPTION.getStatusCode());
		invaildOperation.setErrorMessage(HrManagementEnum.DUPLICATE_KEY_EXCEPTION.getStatusMessage());
		return invaildOperation;
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessageDto IllegalArgumentException(IllegalArgumentException e) {
     ErrorMessageDto invaildOperation = new ErrorMessageDto();
		invaildOperation.setErrorCode(HrManagementEnum.ILLEGAL_AGRUMENT.getStatusCode());
		invaildOperation.setErrorMessage(HrManagementEnum.ILLEGAL_AGRUMENT.getStatusMessage());
		return invaildOperation;
	}
	
	@ExceptionHandler(org.springframework.web.bind.MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessageDto MissingServletRequestParameterException(org.springframework.web.bind.MissingServletRequestParameterException e) {
     ErrorMessageDto invaildOperation = new ErrorMessageDto();
		invaildOperation.setErrorCode(HrManagementEnum.MissserveletException.getStatusCode());
		invaildOperation.setErrorMessage(HrManagementEnum.MissserveletException.getStatusMessage());
		return invaildOperation;
	}
	
	
}
