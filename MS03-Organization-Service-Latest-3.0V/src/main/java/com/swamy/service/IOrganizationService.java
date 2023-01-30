package com.swamy.service;

import java.util.List;

import com.swamy.dto.OrganizationDTO;

public interface IOrganizationService {

	public OrganizationDTO saveOrganization(OrganizationDTO organizationDTO);

	public OrganizationDTO getOrganizationByCode(String organizationCode);

	public List<OrganizationDTO> getAllOrganizations();
}
