package com.swamy.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.swamy.dto.OrganizationDTO;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface ApiOrganizationClient {

	@GetMapping("/api/v3/organization/code/{org-code}")
	public OrganizationDTO getOrganizationByCode(@PathVariable(value = "org-code") String organizationCode);
}
