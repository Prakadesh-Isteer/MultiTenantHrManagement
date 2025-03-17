package com.isteer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isteer.entity.Employee;
import com.isteer.entity.Roles;
import com.isteer.repository.EmployeeRepoDaoImpl;

@Service
public class HrManagementEmployeeService {

	
	@Autowired
	EmployeeRepoDaoImpl repo;
	
	public int registerUser(Employee employee) {
		return repo.registerEmployee(employee);
		
	}
	
	public int addRole(Roles role) {
		return repo.addRole(role);
	}
	
	
}
