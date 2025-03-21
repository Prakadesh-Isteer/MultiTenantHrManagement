package com.isteer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isteer.dto.ErrorMessageDto;
import com.isteer.dto.LeaveRequestDto;
import com.isteer.dto.LeaveResponseDto;
import com.isteer.dto.StatusMessageDto;
import com.isteer.enums.HrManagementEnum;
import com.isteer.service.HrManagementLeaveService;

import jakarta.validation.Valid;

@RequestMapping("/hrManagement")
@RestController
public class HrManagementLeaveRequestController {

	@Autowired
	HrManagementLeaveService service;
		
	 @PostMapping("leave")
	    public ResponseEntity<?> applyLeave(@RequestParam String employeeUuid, @Valid @RequestBody LeaveRequestDto leaveRequestDto) {
	       
	        // Get the result from service (this is now an int)
	        int result = service.applyLeave(leaveRequestDto);
	        
	        if (result > 0) {  // If rows were affected, leave application was successful
	            StatusMessageDto message = new StatusMessageDto(HrManagementEnum.Leave_application_success.getStatusCode(),
	                    HrManagementEnum.Leave_application_success.getStatusMessage());
	            return ResponseEntity.status(HttpStatus.OK).body(message);
	        } else {
	            // If result is 0 or less, something went wrong
	            ErrorMessageDto error = new ErrorMessageDto(
	                HrManagementEnum.LEAVE_APPLICATION_FAILED.getStatusCode(),
	                HrManagementEnum.LEAVE_APPLICATION_FAILED.getStatusMessage()
	            );
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	        }
	    }
}
