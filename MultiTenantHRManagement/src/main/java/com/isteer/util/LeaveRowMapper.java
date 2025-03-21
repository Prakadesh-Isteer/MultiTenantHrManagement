package com.isteer.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.isteer.dto.LeaveResponseDto;

public class LeaveRowMapper implements RowMapper<LeaveResponseDto> {

	@Override
	public LeaveResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		LeaveResponseDto leave = new LeaveResponseDto();
		leave.setLeaveUuid(rs.getString("leave_uuid"));
		leave.setEmployeeId(rs.getString("employee_id"));
		leave.setDepartmentName(rs.getString("department_id"));
		leave.setStartDate(rs.getDate("start_date"));
		leave.setEndDate(rs.getDate("end_date"));
		leave.setReason(rs.getString("reason"));
		leave.setStatus(rs.getString("status"));
		return leave;
	}

}
