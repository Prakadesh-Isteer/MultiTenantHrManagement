package com.isteer.service;

import java.util.List;

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
	
	public List<Employee> getAllUsers(){
		return repo.getAllUsers();
	}
	
	public List<Employee> getuserById(String employeeId){
		return repo.getUsersById(employeeId);
	}
	
	public int addRole(Roles role) {
		return repo.addRole(role);
	}
	
	public int updateUser(Employee employee) {
		return repo.updateUser(employee);
	}
	
	public int deleteEmployee(String employeeId) {
		return repo.deleteEmployee(employeeId);
	}
		
	
}
