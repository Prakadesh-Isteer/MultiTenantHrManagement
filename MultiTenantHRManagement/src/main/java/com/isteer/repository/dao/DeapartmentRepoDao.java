package com.isteer.repository.dao;

import java.util.List;

import com.isteer.entity.Departments;

public interface DeapartmentRepoDao {
	
	public int addDepartment(Departments department);
	public boolean isTenantExist(String tenantId);
	public boolean isDepartmentExist(String departmentName, String tenantId);
	public int updateDepartment(Departments department);
	public List<Departments> getAllDepartments();
	public int deleteDepartment(String departmentId) ;
}
