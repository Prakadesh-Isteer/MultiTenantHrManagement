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

import com.isteer.entity.Tenants;
import com.isteer.repository.dao.TenantRepoDao;
import com.isteer.util.TenantRowMapper;

@Component
public class TenantRepoDaoImpl implements TenantRepoDao{
	
	@Autowired
	NamedParameterJdbcTemplate template;
	
	@Override
	public int addTenant(Tenants tenants) {
		String insertTenant = "INSERT INTO tenants (tenant_uuid, tenant_name, address, contact_email, contact_phone, tenant_country, tenant_state, tenant_city) VALUES (CONCAT('Tenant-', UUID()), :tenantName, :address, :email, :phone, :tenantCountry, :tenantState, :tenantCity)";
		SqlParameterSource param = new MapSqlParameterSource()
//				.addValue("(CONCAT('Tenant-', UUID()))", tenants.getTenantId())
				.addValue("tenantName", tenants.getTenantName())
				.addValue("address", tenants.getAddress() )
				.addValue("email", tenants.getEmail() )
				.addValue("phone", tenants.getPhoneNumber())
				.addValue("tenantCountry", tenants.getTenantCountry())
				.addValue("tenantState", tenants.getTenantState())
				.addValue("tenantCity", tenants.getTenantCity());
		
		return template.update(insertTenant, param);
		
	}

	@Override
	public List<Tenants> getAllTenants() {
		String sql = "SELECT tenant_uuid, tenant_name, address, contact_email, contact_phone, tenant_country, tenant_state, tenant_city FROM tenants";
		return template.query(sql, new TenantRowMapper());
	}
	
	
//	public int updateTenant(Tenants tenant) {
//	    String sql = "UPDATE tenants SET tenant_name = :tenantName, address = :address, contact_email = :email, contact_phone = :phone, tenant_country = :tenantCountry, tenant_state = :tenantState, tenant_city = :tenantCity WHERE tenant_uuid = :tenantId";
//	    
//	    SqlParameterSource param = new MapSqlParameterSource()
//	        .addValue("tenantId", tenant.getTenantId())
//	        .addValue("tenantName", tenant.getTenantName())
//	        .addValue("address", tenant.getAddress())
//	        .addValue("email", tenant.getEmail())
//	        .addValue("phone", tenant.getPhoneNumber())
//	        .addValue("tenantCountry", tenant.getTenantCountry())
//	        .addValue("tenantState", tenant.getTenantState())
//	        .addValue("tenantCity", tenant.getTenantCity());
//	    
//	    return template.update(sql, param);
//	}
	//-----------------------------------------------------------------------------------------------------------------
	
	// Method to check if a tenant exists by tenantId
    public Optional<Tenants> findById(String tenantId) {
        String sql = "SELECT * FROM tenants WHERE tenant_uuid = :tenantId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("tenantId", tenantId);

        try {
            Tenants tenant = template.queryForObject(sql, param, new TenantRowMapper());
            return Optional.ofNullable(tenant);
        } catch (EmptyResultDataAccessException e) {
            // Return an empty Optional if no tenant found
            return Optional.empty();
        }
    }

    // Method to update the tenant
    public int updateTenant(Tenants tenant) {
        // Check if tenant exists before trying to update
        Optional<Tenants> existingTenant = findById(tenant.getTenantId());

        if (!existingTenant.isPresent()) {
            // If the tenant does not exist, return -1 to indicate failure
            return -1;
        }

        // SQL query for updating the tenant details
        String sql = "UPDATE tenants SET tenant_name = :tenantName, address = :address, " +
                     "contact_email = :email, contact_phone = :phone, tenant_country = :tenantCountry, " +
                     "tenant_state = :tenantState, tenant_city = :tenantCity WHERE tenant_uuid = :tenantId";

        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("tenantId", tenant.getTenantId())
            .addValue("tenantName", tenant.getTenantName())
            .addValue("address", tenant.getAddress())
            .addValue("email", tenant.getEmail())
            .addValue("phone", tenant.getPhoneNumber())
            .addValue("tenantCountry", tenant.getTenantCountry())
            .addValue("tenantState", tenant.getTenantState())
            .addValue("tenantCity", tenant.getTenantCity());

        try {
            // Execute the update query
            return template.update(sql, param);
        } catch (DataAccessException e) {
                 e.printStackTrace();
            // Log any database access errors and return 0 or throw a custom exception
//            log.error("Error updating tenant in the database: {}", e.getMessage());
        	System.out.println("tenant update");
            return 0;  // Indicating failure to update
        }
    }

	
}
