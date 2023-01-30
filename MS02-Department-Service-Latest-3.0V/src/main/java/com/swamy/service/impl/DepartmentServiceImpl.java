package com.swamy.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.swamy.dto.DepartmentDTO;
import com.swamy.dto.DepartmentResponseDTO;
import com.swamy.entity.Department;
import com.swamy.exception.ResourceNotFoundException;
import com.swamy.repository.DepartmentRepository;
import com.swamy.service.IDepartmentService;
import com.swamy.utils.AppConstants;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {

		Department department = modelMapper.map(departmentDTO, Department.class);
		Department savedDepartment = departmentRepository.save(department);
		DepartmentDTO departmentResponse = modelMapper.map(savedDepartment, DepartmentDTO.class);
		return departmentResponse;
	}

	@Override
	public DepartmentDTO getDepartmentByCode(String departmentCode) {

		Department department = departmentRepository.findByDepartmentCode(departmentCode).orElseThrow(
				() -> new ResourceNotFoundException(AppConstants.DEPT_NOT_FOUND_BY_CODE_EXCEPTION + departmentCode));

		return modelMapper.map(department, DepartmentDTO.class);
	}

	@Override
	public DepartmentResponseDTO getAllDepartments(Integer pageNo, Integer pageSize, String sortBy) {

		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Department> pages = departmentRepository.findAll(pageable);
		List<Department> departmentsList = pages.getContent();
		List<DepartmentDTO> content = departmentsList.stream()
				.map(department -> modelMapper.map(department, DepartmentDTO.class)).collect(Collectors.toList());

		DepartmentResponseDTO departmentResponse = new DepartmentResponseDTO();
		departmentResponse.setContent(content);
		departmentResponse.setPageNo(pageNo);
		departmentResponse.setPageSize(pageSize);
		departmentResponse.setSortBy(sortBy);
		departmentResponse.setTotalElements(pages.getTotalElements());
		departmentResponse.setTotalPages(pages.getTotalPages());
		departmentResponse.setLast(pages.isLast());

		return departmentResponse;
	}

	@Override
	public DepartmentDTO getOneDepartmentById(Integer departmentId) {

		Department department = departmentRepository.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException(AppConstants.DEPARTMENT_NOT_FOUND_EXCEPTION + departmentId));
		return modelMapper.map(department, DepartmentDTO.class);
	}

	@Override
	public DepartmentDTO updateDepartmentById(Integer departmentId, DepartmentDTO departmentDTO) {

		Department department = departmentRepository.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException(AppConstants.DEPARTMENT_NOT_FOUND_EXCEPTION + departmentId));
		department.setDepartmentName(departmentDTO.getDepartmentName());
		department.setDepartmentCode(departmentDTO.getDepartmentCode());
		Department updatedDepartment = departmentRepository.save(department);
		DepartmentDTO departmentResponse = modelMapper.map(updatedDepartment, DepartmentDTO.class);
		return departmentResponse;
	}

	@Override
	public String deleteDepartment(Integer departmentId) {

		Department department = departmentRepository.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException(AppConstants.DEPARTMENT_NOT_FOUND_EXCEPTION + departmentId));

		departmentRepository.delete(department);

		return AppConstants.DEPARTMENT_DELETE_SUCCESS + departmentId;
	}

}
