package com.isteer.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class LeaveManagement {
	
    private String leaveUuid;
    private String employeeId;
    private String departmentId;
    private Date startDate;
    private Date endDate;
    private String reason;
    private String status;
    private Date appliedAt;
    private String approvedBy;
    private Date approvedAt;
    private Date updatedAt;

}
