package com.swamy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmployeeDTO {

	private Integer id;

	@NotEmpty(message = "Employee Name must not be Empty or Null")
	@Size(min = 2, message = "Employee Name Should have Atleast 2 charecters")
	private String employeeName;

	@NotEmpty(message = "Employee Email must not be Empty or Null")
	@Email(message = "Must be a well-formed Email Address")
	private String employeeEmail;

	@NotNull(message = "Employee Salary Should Not be Null")
	private Double employeeSalary;

	@NotEmpty(message = "Department Code must not be Empty or Null")
	@Size(min = 2, message = "Department Code Should have Atleast 2 charecters")
	private String departmentCode;

	@NotEmpty(message = "Organization Code must not be Empty or Null")
	@Size(min = 2, message = "Organization Code Should have Atleast 2 charecters")
	private String organizationCode;

}
