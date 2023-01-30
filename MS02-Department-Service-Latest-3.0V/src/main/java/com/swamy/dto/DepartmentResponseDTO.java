package com.swamy.dto;

import java.util.List;

import lombok.Data;

@Data
public class DepartmentResponseDTO {

	private List<DepartmentDTO>content;
	private Integer pageNo;
	private Integer pageSize;
	private String sortBy;
	private Long totalElements;
	private Integer totalPages;
	private boolean isLast;
}
