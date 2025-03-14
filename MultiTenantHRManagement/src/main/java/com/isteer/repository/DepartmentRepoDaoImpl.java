package com.isteer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.isteer.entity.Departments;
import com.isteer.repository.dao.DeapartmentRepoDao;
import com.isteer.util.DepartmentRowMapper;

@Component
public class DepartmentRepoDaoImpl implements DeapartmentRepoDao{
	
	@Autowired
	NamedParameterJdbcTemplate template;

			
	 // Method to check if the tenant exists by explicitly selecting columns
	@Override
    public boolean isTenantExist(String tenantId) {
        String checkTenantExistsQuery = "SELECT tenant_uuid FROM tenants WHERE tenant_uuid = :tenantId AND tenant_status = :status";
        
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("tenantId", tenantId);
        param.addValue("status", 1);  // Assuming '1' indicates active tenants

        List<String> tenantUuids = template.queryForList(checkTenantExistsQuery, param, String.class);

        return !tenantUuids.isEmpty(); // Return true if tenant exists
    }
    
 // Method to check if the department exists
    @Override
    public boolean isDepartmentExist(String departmentName, String tenantId) {
        String checkDepartmentExistsQuery = "SELECT department_name FROM departments "
                + "WHERE department_name = :departmentName AND tenant_id = :tenantId AND department_status = :status";
        
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("departmentName", departmentName);
        param.addValue("tenantId", tenantId);
        param.addValue("status", 1);  // Assuming '1' indicates active departments
        
        List<String> departmentNames = template.queryForList(checkDepartmentExistsQuery, param, String.class);
        return !departmentNames.isEmpty();  // Returns true if the department exists
    }

   
    // Method to add the department (also not using * in SQL)
    @Override
    public int addDepartment(Departments department) {
    	
    	String addDepartmentQuery = "INSERT INTO departments (department_uuid, department_head_uuid, tenant_id, department_name, contact_email, contact_phone, description) "
                + "VALUES (CONCAT('Department-', UUID()), CONCAT('HOD-', UUID()), :tenantId, :departmentName, :email, :phone, :description)";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("tenantId", department.getTenantId())
                .addValue("departmentName", department.getDepartmentName())
                .addValue("email", department.getEmail())
                .addValue("phone", department.getPhoneNumber())
                .addValue("description", department.getDescription());

        return template.update(addDepartmentQuery, params);
    }
   @Transactional
    public Optional<Departments> findbyId(String departmentId){
    	String sql = "SELECT department_uuid, department_head_uuid, tenant_id, department_name, contact_email, contact_phone, description FROM departments where department_uuid = :departmentId";
    	SqlParameterSource param = new MapSqlParameterSource()
    			.addValue("departmentId", departmentId);
    	 try {
             Departments WrkDepartments = template.queryForObject(sql, param, new DepartmentRowMapper());
             return Optional.ofNullable(WrkDepartments);
         } catch (EmptyResultDataAccessException e) {
             // Return an empty Optional if no tenant found
             return Optional.empty();
         }
    	
    }

	@Override
	public int updateDepartment(Departments department) {
		
		Optional<Departments> existingDepartment = findbyId(department.getDepartmentId());
		
		if(!existingDepartment.isPresent()) {
			return -1;
		}
		
		String sql = "UPDATE departments SET department_name = :departmentName, contact_email = :email, contact_phone = :phoneNumber, description = :description WHERE department_uuid = :departmentId";
		
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("departmentId", department.getDepartmentId())
				.addValue("departmentName", department.getDepartmentName())
				.addValue("email", department.getEmail())
				.addValue("phoneNumber", department.getPhoneNumber())
				.addValue("description", department.getDescription());
		  try {
	            // Execute the update query
	            return template.update(sql, param);
	        } catch (DataAccessException e) {
	                 e.printStackTrace();
	            // Log any database access errors and return 0 or throw a custom exception
//	            log.error("Error updating tenant in the database: {}", e.getMessage());
	        	System.out.println("tenant update");
	            return 0;  // Indicating failure to update
	        }
	    }
	
	@Override
	public List<Departments> getAllDepartments(){
		String sql = "SELECT department_uuid,department_head_uuid,tenant_id,department_name,contact_email,contact_phone,description FROM departments WHERE department_status= :status";
	 SqlParameterSource param = new MapSqlParameterSource()
			 .addValue("status", 1);
	 return template.query(sql, param , new DepartmentRowMapper());
	}
	
	}
    
  
	


