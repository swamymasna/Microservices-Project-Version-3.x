package com.swamy.service;

import java.util.List;

import com.swamy.dto.ApiResponseDTO;
import com.swamy.dto.EmployeeDTO;

public interface IEmployeeService {

	public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

	public List<EmployeeDTO> getAllEmployees();

	public ApiResponseDTO getOneEmployeeById(Integer employeeId);

	public EmployeeDTO updateEmployeeById(Integer employeeId, EmployeeDTO employeeDTO);

	public String deleteEmployeeById(Integer employeeId);

}
