package com.isteer.repository.dao;

import com.isteer.entity.Employee;
import com.isteer.entity.Roles;

public interface EmployeeRepoDao {

	public int registerEmployee(Employee employee);
	
	public int addRole(Roles role);
}
