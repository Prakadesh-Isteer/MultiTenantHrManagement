package com.isteer.repository.dao;

import java.util.List;

import com.isteer.entity.Employee;
import com.isteer.entity.Roles;

public interface EmployeeRepoDao {

	public int registerEmployee(Employee employee);
	
	public int addRole(Roles role);
	
	public List<Employee> getAllUsers();
	
	public List<Employee> getUsersById(String employeeId);
	
	public int updateUser(Employee employee);

	public int deleteEmployee(String employeeId);
	
	public List<Roles> getAllAvailableRoles();
	
//	public int updateUserRole(Employee employee);

	public int updateUserRole(String employeeId, String roleId);
	
}
