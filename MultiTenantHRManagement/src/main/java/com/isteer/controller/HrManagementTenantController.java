package com.isteer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isteer.dto.ErrorMessageDto;
import com.isteer.dto.StatusMessageDto;
import com.isteer.entity.Tenants;
import com.isteer.enums.HrManagementEnum;
import com.isteer.service.HrManagementTenantService;

import jakarta.validation.Valid;

@RequestMapping("/hrManagement")
@RestController
public class HrManagementTenantController {

	@Autowired
	HrManagementTenantService service;

	@PostMapping("tenant")
	public ResponseEntity<?> createTenant(@Valid @RequestBody Tenants tenant) {
		int status = service.createTenant(tenant);
		if (status > 0) {
			StatusMessageDto message = new StatusMessageDto(HrManagementEnum.Tenant_created_message.getStatusCode(),
					HrManagementEnum.Tenant_created_message.getStatusMessage());
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		ErrorMessageDto error = new ErrorMessageDto(HrManagementEnum.Tenant_creation_failed.getStatusCode(),
				HrManagementEnum.Tenant_creation_failed.getStatusMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}


	@GetMapping("tenant")
	public ResponseEntity<?> getAllTenants() {
		List<?> list = service.getAllTenants();

		if (list.isEmpty()) {
			ErrorMessageDto error = new ErrorMessageDto(HrManagementEnum.No_list_of_tenansts.getStatusCode(),
					HrManagementEnum.No_list_of_tenansts.getStatusMessage());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(error);
		}

		return ResponseEntity.ok(list);
	}

	@PutMapping("tenant")
	public ResponseEntity<?> updateTenant(@Valid @RequestBody Tenants tenant) {
		// Ensure tenant object has the tenantId before calling the service method
		int status = service.updateTenant(tenant);

		if (status > 0) {
			StatusMessageDto message = new StatusMessageDto(
					HrManagementEnum.Tenant_updation_successfull.getStatusCode(),
					HrManagementEnum.Tenant_updation_successfull.getStatusMessage());
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} else if (status == -1) {
			// Tenant not found, invalid tenantId
			ErrorMessageDto error = new ErrorMessageDto(HrManagementEnum.Tenant_not_found.getStatusCode(),
					HrManagementEnum.Tenant_not_found.getStatusMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}

		ErrorMessageDto error = new ErrorMessageDto(HrManagementEnum.Tenant_creation_failed.getStatusCode(),
				HrManagementEnum.Tenant_creation_failed.getStatusMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
