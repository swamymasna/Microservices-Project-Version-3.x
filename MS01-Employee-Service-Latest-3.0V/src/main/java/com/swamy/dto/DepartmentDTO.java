package com.swamy.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DepartmentDTO {

	private Integer id;

	@NotEmpty(message = "Department Name Must Not be Empty or Null")
	@Size(min = 2, message = "Department Name Should have atleast 2 charecters")
	private String departmentName;

	@NotEmpty(message = "Department Code Must Not be Empty or Null")
	@Size(min = 2, message = "Department Code Should have atleast 2 charecters")
	private String departmentCode;

}
