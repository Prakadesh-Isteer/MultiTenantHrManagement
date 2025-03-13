package com.isteer.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.isteer.entity.Tenants;

public class TenantRowMapper implements RowMapper<Tenants> {
	@Override
	public Tenants mapRow(ResultSet rs, int rowNum) throws SQLException {
	  Tenants tenant = new Tenants();
	  tenant.setTenantId(rs.getString("tenant_uuid"));
	  tenant.setTenantName(rs.getString("tenant_name"));
	  tenant.setAddress(rs.getString("address"));
	  tenant.setEmail(rs.getString("contact_email"));
	  tenant.setPhoneNumber(rs.getString("contact_phone"));
	  tenant.setTenantCountry(rs.getString("tenant_country"));
	  tenant.setTenantState(rs.getString("tenant_state"));
	  tenant.setTenantCity(rs.getString("tenant_city"));
		return tenant;
	}

}
