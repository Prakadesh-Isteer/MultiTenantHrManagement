package com.isteer.repository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.isteer.entity.Employee;
import com.isteer.entity.Roles;
import com.isteer.repository.dao.EmployeeRepoDao;

@Component
public class EmployeeRepoDaoImpl implements EmployeeRepoDao {
  
	@Autowired
	NamedParameterJdbcTemplate template;
	
	@Override
	public int registerEmployee(Employee employee) {
		UUID uuid = UUID.randomUUID();
        String employeeUuid = "Employee-" + uuid.toString();
		String registerUser = "INSERT INTO employee (employee_uuid, role_id, tenant_id, department_id, userName, password, first_name, last_name, email, phone, address, date_of_joining, job_title) VALUES (:employeeId, :roleId, :tenantId, :departmentId, :userName, :password, :firstName, :lastName,:email,:phone, :address,:joiningDate, :jobTitle)";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("employeeId", employeeUuid)
				.addValue("roleId", employee.getRoleId())
				.addValue("tenantId", employee.getTenantId())
				.addValue("departmentId", employee.getDepartmentId())
				.addValue("userName", employee.getUserName())
				.addValue("password", employee.getPassword())
				.addValue("firstName", employee.getFirstName())
				.addValue("lastName", employee.getLastName())
				.addValue("email", employee.getEmail())
				.addValue("phone", employee.getPhoneNumber())
				.addValue("address", employee.getAddress())
				.addValue("joiningDate", employee.getDateOfJoining())
				.addValue("jobTitle", employee.getJobTitle());
		return template.update(registerUser, param);
	}
	@Override
	public int addRole(Roles role) {
		UUID uuid= UUID.randomUUID();
		String roleUuid = "Role-".concat(uuid.toString());
		String addRole = "INSERT INTO roles(role_uuid, role-name, description) VALUES (:roleId, :roleName, :description)";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("roleId", roleUuid)
				.addValue("roleName", role.getRoleName())
				.addValue("description", role.getRoleDescription());
		return template.update(addRole, param);
		
	}

}
