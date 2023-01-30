package com.swamy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swamy.dto.OrganizationDTO;
import com.swamy.service.IOrganizationService;

@RestController
@RequestMapping("/api/v3")
public class OrganizationRestController {

	@Autowired
	private IOrganizationService organizationService;

	@PostMapping("/organization/save")
	public ResponseEntity<OrganizationDTO> saveOrganization(@RequestBody OrganizationDTO organizationDTO) {
		return new ResponseEntity<OrganizationDTO>(organizationService.saveOrganization(organizationDTO),
				HttpStatus.CREATED);
	}

	@GetMapping("/organization/code/{org-code}")
	public ResponseEntity<OrganizationDTO> getOrganizationByCode(
			@PathVariable(value = "org-code") String organizationCode) {
		return new ResponseEntity<OrganizationDTO>(organizationService.getOrganizationByCode(organizationCode),
				HttpStatus.OK);
	}

	@GetMapping("/organization/list")
	public ResponseEntity<List<OrganizationDTO>> getAllOrganizations() {
		return new ResponseEntity<>(organizationService.getAllOrganizations(), HttpStatus.OK);
	}
}
