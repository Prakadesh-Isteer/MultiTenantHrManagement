package com.isteer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isteer.dto.LeaveRequestDto;
import com.isteer.dto.LeaveResponseDto;
import com.isteer.exception.EmployeeNotFoundException;
import com.isteer.repository.LeaveRepoDaoImpl;

@Service
public class HrManagementLeaveService {
	
	@Autowired
	LeaveRepoDaoImpl repo;
	


	 public int applyLeave(LeaveRequestDto leaveRequestDto) {
	        // Apply for leave by calling the repository and return the result (number of rows affected)
	        try {
	            return repo.applyLeave(leaveRequestDto);  // Now returns an int instead of LeaveResponseDto
	        } catch (EmployeeNotFoundException ex) {
	            // Handle exception or let it propagate
	            throw ex;  // Re-throw exception if necessary
	        }
	    }
	    }
	



