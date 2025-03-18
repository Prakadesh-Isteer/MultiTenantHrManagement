package com.isteer.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.isteer.entity.Employee;
import com.isteer.entity.Roles;
import com.isteer.enums.HrManagementEnum;
import com.isteer.exception.EmployeeIdNullException;
import com.isteer.exception.IllegalArgumentException;
import com.isteer.exception.TenantIdNullException;
import com.isteer.repository.dao.EmployeeRepoDao;
import com.isteer.util.EmployeeRowMapper;
import com.isteer.util.RolesRowmapper;

@Component
public class EmployeeRepoDaoImpl implements EmployeeRepoDao {

	@Autowired
	NamedParameterJdbcTemplate template;

	@Override
	public int registerEmployee(Employee employee) {
		UUID uuid = UUID.randomUUID();
		String employeeUuid = "Employee-" + uuid.toString();

		// Fetch the role_uuid for the 'Employee' role
		String getRoleIdQuery = "SELECT role_uuid, role_name, description FROM roles WHERE role_name = 'Employee' LIMIT 1";

		// Ensure roleId query returns a single value
		List<Roles> roleList = template.query(getRoleIdQuery, new RolesRowmapper());

		// Ensure roleList is not empty, then extract the roleId
		String roleId = null;
		if (roleList != null && !roleList.isEmpty()) {
			roleId = roleList.get(0).getRoleId(); // Assuming getRoleUuid() returns the UUID of the role
		} else {
			// Handle the case when role is not found (you can throw an exception or return
			// a specific error code)
			throw new IllegalArgumentException(HrManagementEnum.ILLEGAL_AGRUMENT);
		}

		String registerUser = "INSERT INTO employee (employee_uuid, role_id, tenant_id, department_id, userName, password, first_name, last_name, email, phone, address, date_of_joining, job_title) VALUES (:employeeId, :roleId, :tenantId, :departmentId, :userName, :password, :firstName, :lastName,:email,:phone, :address,:joiningDate, :jobTitle)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("employeeId", employeeUuid)
				.addValue("roleId", roleId).addValue("tenantId", employee.getTenantId())
				.addValue("departmentId", employee.getDepartmentId()).addValue("userName", employee.getUserName())
				.addValue("password", employee.getPassword()).addValue("firstName", employee.getFirstName())
				.addValue("lastName", employee.getLastName()).addValue("email", employee.getEmail())
				.addValue("phone", employee.getPhoneNumber()).addValue("address", employee.getAddress())
				.addValue("joiningDate", employee.getDateOfJoining()).addValue("jobTitle", employee.getJobTitle());
		return template.update(registerUser, param);
	}

	@Override
	public int addRole(Roles role) {
		UUID uuid = UUID.randomUUID();
		String roleUuid = "Role-".concat(uuid.toString());
		String addRole = "INSERT INTO roles(role_uuid, role_name, description) VALUES (:roleId, :roleName, :description)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("roleId", roleUuid)
				.addValue("roleName", role.getRoleName()).addValue("description", role.getRoleDescription());
		return template.update(addRole, param);

	}

	@Override
	public List<Employee> getAllUsers() {
		String sql = "SELECT employee_uuid, role_id, tenant_id, department_id, userName, password, first_name, last_name, email, phone, address, date_of_joining, job_title FROM employee WHERE employee_status = :status";
		SqlParameterSource param = new MapSqlParameterSource().addValue("status", 1);
		return template.query(sql, param, new EmployeeRowMapper());
	}

	@Override
	public List<Employee> getUsersById(String employeeId) {
		if (employeeId == null || employeeId.trim().isEmpty()) {
			throw new EmployeeIdNullException(HrManagementEnum.Employee_id_null);
		}
		String sql = "SELECT employee_uuid, role_id, tenant_id, department_id, userName, password, first_name, last_name, email, phone, address, date_of_joining, job_title FROM employee WHERE employee_status = :status AND employee_uuid = :employeeId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("status", 1).addValue("employeeId", employeeId);
		return template.query(sql, param, new EmployeeRowMapper());

	}

	// Method to check if a tenant exists by tenantId
	public Optional<Employee> findById(String userId) {
		String sql = "SELECT employee_uuid, role_id, tenant_id, department_id, userName, password, first_name, last_name, email, phone, address, date_of_joining, job_title FROM employee WHERE employee_status = :status AND employee_uuid = :employeeId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("employeeId", userId).addValue("status", 1);

		try {
			Employee employee = template.queryForObject(sql, param, new EmployeeRowMapper());
			return Optional.ofNullable(employee);
		} catch (EmptyResultDataAccessException e) {
			// Return an empty Optional if no tenant found
			return Optional.empty();
		}
	}

	@Override
	public int updateUser(Employee employee) {
		Optional<Employee> existingUser = findById(employee.getEmployeeId());
		if (!existingUser.isPresent()) {
			return -1;
		}

		String sql = "UPDATE employee SET  department_id = :departmentId, userName = :userName, password = :password, first_name = :firstName, last_name = :lastName, email = :email, phone = :phone, address = :address, date_of_joining = :dateOfJoining, job_title = :jobTitle WHERE employee_status = :status AND employee_uuid = :employeeId";

		SqlParameterSource param = new MapSqlParameterSource().addValue("employeeId", employee.getEmployeeId())
				.addValue("departmentId", employee.getDepartmentId()).addValue("userName", employee.getUserName())
				.addValue("password", employee.getPassword()).addValue("firstName", employee.getFirstName())
				.addValue("lastName", employee.getLastName()).addValue("email", employee.getEmail())
				.addValue("phone", employee.getPhoneNumber()).addValue("address", employee.getAddress())
				.addValue("dateOfJoining", employee.getDateOfJoining()).addValue("jobTitle", employee.getJobTitle())
				.addValue("status", 1);
		try {
			// Execute the update query
			return template.update(sql, param);
		} catch (DataAccessException e) {
			e.printStackTrace();
			// Log any database access errors and return 0 or throw a custom exception
//	            log.error("Error updating tenant in the database: {}", e.getMessage());
			System.out.println("employee update");
			return 0; // Indicating failure to update
		}
	}

	@Override
	public int deleteEmployee(String employeeId) {

		if (employeeId == null || employeeId.trim().isEmpty()) {
			throw new EmployeeIdNullException(HrManagementEnum.Employee_id_null);
		}

		try {
			String checkEmployeeExistsQuery = "SELECT employee_uuid FROM employee WHERE employee_uuid = :employeeId AND employee_status = :status ";
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("status", 1);
			param.addValue("employeeId", employeeId);

			List<String> employeeUuids = template.queryForList(checkEmployeeExistsQuery, param, String.class);
			if (employeeUuids.isEmpty()) {
				return -1; // employee not found
			}
			String softDelete = "UPDATE employee SET employee_status = :status WHERE employee_uuid = :employeeId";
			param.addValue("status", 0); // Assuming 0 represents deleted status

			// Perform the update (soft delete)
			return template.update(softDelete, param);

		} catch (Exception e) {
			// Log exception here (optional), rethrow or handle the exception
			return 0; // Indicates failure
		}
	}

}
