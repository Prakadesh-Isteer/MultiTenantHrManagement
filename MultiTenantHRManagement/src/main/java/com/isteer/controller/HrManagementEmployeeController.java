package com.isteer.controller;

import java.util.List;

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

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




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
	
	@GetMapping("users")
	public ResponseEntity<?> getAllUsers(){
		List<?> list = service.getAllUsers();
		if(list.isEmpty()) {
			ErrorMessageDto error = new ErrorMessageDto(HrManagementEnum.NO_USERS_FOUND_LIST.getStatusCode(),
					HrManagementEnum.NO_USERS_FOUND_LIST.getStatusMessage());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(error);
		
		}
		return ResponseEntity.ok(list) ;
		
	}
	
	@GetMapping("user")
	public ResponseEntity<?> getMethodName(@RequestParam String employeeId) {
		List<?> single = service.getuserById(employeeId);
		if(single.isEmpty()) {
			ErrorMessageDto error = new ErrorMessageDto(HrManagementEnum.NO_USERS_FOUND_LIST.getStatusCode(),
					HrManagementEnum.NO_USERS_FOUND_LIST.getStatusMessage());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(error);
		
		}
		return ResponseEntity.ok(single);
	}
	
	@PutMapping("users")
	public ResponseEntity<?> updateUser(@RequestParam String employeeId, @RequestBody Employee employee) {
		employee.setEmployeeId(employeeId);
		int status = service.updateUser(employee);
	
		if (status > 0) {
			StatusMessageDto message = new StatusMessageDto(
					HrManagementEnum.USER_UPDATED_SUCCESS.getStatusCode(),
					HrManagementEnum.USER_UPDATED_SUCCESS.getStatusMessage());
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} else if (status == -1) {
			// User not found, invalid tenantId
			ErrorMessageDto error = new ErrorMessageDto(HrManagementEnum.USER_VALID_NOT_FOUND.getStatusCode(),
					HrManagementEnum.USER_VALID_NOT_FOUND.getStatusMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}

		ErrorMessageDto error = new ErrorMessageDto(HrManagementEnum.USER_UPDATE_FAIL.getStatusCode(),
				HrManagementEnum.USER_UPDATE_FAIL.getStatusMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	
	}
	
	@DeleteMapping("users")
	public ResponseEntity<?> deleteEmployee(@RequestParam String employeeId){
		
		int status = service.deleteEmployee(employeeId);
		
		if (status > 0) {
			StatusMessageDto message = new StatusMessageDto(
					HrManagementEnum.User_deleted_success.getStatusCode(),
					HrManagementEnum.User_deleted_success.getStatusMessage());
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} 
		else if (status == -1) {
			// Tenant not found, invalid tenantId
			ErrorMessageDto error = new ErrorMessageDto(HrManagementEnum.USER_VALID_NOT_FOUND.getStatusCode(),
					HrManagementEnum.USER_VALID_NOT_FOUND.getStatusMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}

		ErrorMessageDto error = new ErrorMessageDto(HrManagementEnum.User_deleted_fail.getStatusCode(),
				HrManagementEnum.User_deleted_fail.getStatusMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	
}
	
	
	

}
