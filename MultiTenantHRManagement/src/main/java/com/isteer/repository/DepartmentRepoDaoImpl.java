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
import org.springframework.transaction.annotation.Transactional;

import com.isteer.entity.Departments;
import com.isteer.enums.HrManagementEnum;
import com.isteer.exception.DepartmentIdNullException;
import com.isteer.exception.TenantIdNullException;
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
    	UUID uuid = UUID.randomUUID();
        String departmentUuid = "Department-".concat(uuid.toString()) ;
        String hodId = "HOD-".concat(uuid.toString());
    	
    	String addDepartmentQuery = "INSERT INTO departments (department_uuid, department_head_uuid, tenant_id, department_name, contact_email, contact_phone, description) "
                + "VALUES (:departmentUuid, :headId, :tenantId, :departmentName, :email, :phone, :description)";

        SqlParameterSource params = new MapSqlParameterSource()
        		.addValue("departmentUuid", departmentUuid)
        		.addValue("headId", hodId)
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

	@Override
	public int deleteDepartment(String departmentId) {
		 if (departmentId == null || departmentId.trim().isEmpty()) {
	            throw new DepartmentIdNullException(HrManagementEnum.Department_id_null);
	        }
		 
		 try {
			 // Check if tenant exists by selecting the tenant_uuid column
	            String checkDepartmentExistsQuery = "SELECT department_uuid FROM departments WHERE department_uuid = :departmentId  AND department_status = :status";
	            MapSqlParameterSource param = new MapSqlParameterSource();
	            param.addValue("status", 1);
	            param.addValue("departmentId", departmentId);

	            // Execute query to check if tenant exists
	            List<String> departmentUuids = template.queryForList(checkDepartmentExistsQuery, param, String.class);

	            // If the tenant UUID doesn't exist in the result list, return -1 (tenant not found)
	            if (departmentUuids.isEmpty()) {
	                return -1; // Tenant not found
	            }

	            // Soft delete query
	            String softDelete = "UPDATE departments SET department_status = :status WHERE department_uuid = :departmentId";
	            param.addValue("status", 0);  // Assuming 0 represents deleted status
	            
	            // Perform the update (soft delete)
	            return template.update(softDelete, param);
	            
			 
		 }catch(Exception e){
				return 0;
		 }

		

	}
	
	
	
	}
    
  
	


