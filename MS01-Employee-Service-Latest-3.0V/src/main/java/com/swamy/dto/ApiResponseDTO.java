package com.swamy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApiResponseDTO {

	private EmployeeDTO employeeDTO;
	private DepartmentDTO departmentDTO;
	private OrganizationDTO organizationDTO;
}
