package com.isteer.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.isteer.dto.LeaveRequestDto;
import com.isteer.entity.Employee;
import com.isteer.enums.HrManagementEnum;
import com.isteer.exception.EmployeeIdNullException;
import com.isteer.exception.EmployeeNotFoundException;
import com.isteer.exception.InsertionFailedException;
import com.isteer.repository.dao.LeaveRepoDao;
import com.isteer.util.EmployeeRowMapper;


@Component
public class LeaveRepoDaoImpl implements LeaveRepoDao{
	
	@Autowired
	NamedParameterJdbcTemplate template;
	
	@Transactional
	public Optional<Employee> findById(String userId) {
        String sql = "SELECT employee_uuid, role_id, tenant_id, department_id, userName, password, first_name, last_name, email, phone, address, date_of_joining, job_title FROM employee WHERE employee_status = :status AND employee_uuid = :employeeId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("employeeId", userId).addValue("status", 1);

        try {
            Employee employee = template.queryForObject(sql, param, new EmployeeRowMapper());
            return Optional.ofNullable(employee);
        } catch (EmptyResultDataAccessException e) {
            // Throw custom exception with error code and message
            throw new EmployeeNotFoundException(HrManagementEnum.EMPLOYEE_VALID_NOT_FOUND);
        }
    }
	
	@Transactional
	@Override
	public int applyLeave(LeaveRequestDto leaveRequestDto) {
	    // Check if the employee exists
	    Optional<Employee> existingUser = findById(leaveRequestDto.getEmployeeId());

	    if (!existingUser.isPresent()) {
	        // Employee not found, return 0 or handle as per your logic
	        return 0;  // No employee found, return 0 to indicate failure
	    }

	    // Validate if employeeId is not null or empty
	    if (leaveRequestDto.getEmployeeId() == null || leaveRequestDto.getEmployeeId().trim().isEmpty()) {
	        throw new EmployeeIdNullException(HrManagementEnum.Employee_id_null);
	    }

	    // Generate a unique leave UUID
	    UUID uuid = UUID.randomUUID();
	    String leaveUuid = uuid.toString();

	    try {
	        // Query to get employee's department_id
	        String getDepartmentIdQuery = "SELECT department_id FROM employee WHERE employee_uuid = :employeeId LIMIT 1";
	        String departmentId = template.queryForObject(getDepartmentIdQuery,
	                new MapSqlParameterSource().addValue("employeeId", leaveRequestDto.getEmployeeId()), String.class);

	        // Insert leave request into the leaves table with department_id from employee
	        String insertLeaveQuery = "INSERT INTO leaves (leave_uuid, employee_id, department_id, start_date, end_date, reason, status, applied_at) " +
	                "VALUES (:leaveUuid, :employeeId, :departmentId, :startDate, :endDate, :reason, 'PENDING', CURRENT_TIMESTAMP)";
	        
	        SqlParameterSource params = new MapSqlParameterSource()
	                .addValue("leaveUuid", leaveUuid)
	                .addValue("employeeId", leaveRequestDto.getEmployeeId())
	                .addValue("departmentId", departmentId)
	                .addValue("startDate", leaveRequestDto.getStartDate())
	                .addValue("endDate", leaveRequestDto.getEndDate())
	                .addValue("reason", leaveRequestDto.getReason());
	        
	        int result = template.update(insertLeaveQuery, params); // This will return the number of rows affected

	        return result;  // Return the result of the update operation (number of rows affected)
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    // If any error occurs during the insertion, throw custom exception
	    throw new InsertionFailedException(HrManagementEnum.Insertion_failed_Exception);
	}

//	public List<LeaveResponseDto> getAllLeaveRequests() {
//		
//		String sql = "SELECT leave_uuid, employee_id, department_id, start_date, end_date, reason  "
//		return null;
//		
//	}
	

	    }
	


