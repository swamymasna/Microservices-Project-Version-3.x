package com.swamy.service;

import com.swamy.dto.DepartmentDTO;
import com.swamy.dto.DepartmentResponseDTO;

public interface IDepartmentService {

	public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);

	public DepartmentResponseDTO getAllDepartments(Integer pageNo, Integer pageSize, String sortBy);

	public DepartmentDTO getOneDepartmentById(Integer departmentId);

	public DepartmentDTO updateDepartmentById(Integer departmentId, DepartmentDTO departmentDTO);

	public String deleteDepartment(Integer departmentId);
	
	DepartmentDTO getDepartmentByCode(String departmentCode);
}
