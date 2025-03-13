package com.isteer.globalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.isteer.dto.ErrorMessageDto;

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
	
	
}
