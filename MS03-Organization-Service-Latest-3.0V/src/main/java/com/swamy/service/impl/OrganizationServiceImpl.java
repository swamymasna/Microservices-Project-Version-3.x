package com.swamy.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swamy.dto.OrganizationDTO;
import com.swamy.entity.Organization;
import com.swamy.exception.ResourceNotFoundException;
import com.swamy.repository.OrganizationRepository;
import com.swamy.service.IOrganizationService;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

	@Autowired
	private OrganizationRepository organizationRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public OrganizationDTO saveOrganization(OrganizationDTO organizationDTO) {

		Organization organization = modelMapper.map(organizationDTO, Organization.class);
		Organization savedOrganization = organizationRepository.save(organization);
		OrganizationDTO organizationResponse = modelMapper.map(savedOrganization, OrganizationDTO.class);
		return organizationResponse;
	}

	@Override
	public OrganizationDTO getOrganizationByCode(String organizationCode) {
		
		Organization organization = organizationRepository.findByOrganizationCode(organizationCode).orElseThrow(() -> new ResourceNotFoundException("Organization Not Found With Code : " + organizationCode));
		return modelMapper.map(organization, OrganizationDTO.class);
	}

	@Override
	public List<OrganizationDTO> getAllOrganizations() {
		List<Organization> organizationsList = organizationRepository.findAll();
		return organizationsList.stream().map(organization -> modelMapper.map(organization, OrganizationDTO.class)).collect(Collectors.toList());
	}

}

