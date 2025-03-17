package com.isteer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.isteer.dto.ErrorMessageDto;
import com.isteer.dto.StatusMessageDto;
import com.isteer.entity.Employee;
import com.isteer.entity.Roles;
import com.isteer.enums.HrManagementEnum;
import com.isteer.service.HrManagementEmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/hrManagement")
public class HrManagementEmployeeController {
	
	@Autowired
	HrManagementEmployeeService service;
	
	@PostMapping("user")
	public ResponseEntity<?> registerUser(@RequestBody Employee employee) {
	
		int status = service.registerUser(employee);
		if(status > 0) {
			 StatusMessageDto message = new StatusMessageDto(
		                HrManagementEnum.USER_CREATED_SUCCESS.getStatusCode(),
		                HrManagementEnum.USER_CREATED_SUCCESS.getStatusMessage());
		        return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		  ErrorMessageDto error = new ErrorMessageDto(
		            HrManagementEnum.USER_FAILED_CREATION.getStatusCode(),
		            HrManagementEnum.USER_FAILED_CREATION.getStatusMessage());
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@PostMapping("addRole")
	public ResponseEntity<?> addRole(@RequestBody Roles role) {
		int status = service.addRole(role);
		if(status > 0) {
			 StatusMessageDto message = new StatusMessageDto(
		                HrManagementEnum.Role_added.getStatusCode(),
		                HrManagementEnum.Role_added.getStatusMessage());
		        return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		  ErrorMessageDto error = new ErrorMessageDto(
		            HrManagementEnum.Role_not_added.getStatusCode(),
		            HrManagementEnum.Role_not_added.getStatusMessage());
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
	
	

}
