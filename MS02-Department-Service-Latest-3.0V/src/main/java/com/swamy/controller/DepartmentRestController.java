package com.swamy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swamy.dto.DepartmentDTO;
import com.swamy.dto.DepartmentResponseDTO;
import com.swamy.service.IDepartmentService;
import com.swamy.utils.AppConstants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "DEPARTMENT RESOURCE")
@RestController
@RequestMapping("/api/v2")
public class DepartmentRestController {

	@Autowired
	private IDepartmentService departmentService;

	@Operation(summary = "SAVE DEPARTMENT", description = "This Is To Save Department Into Database")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Department Saved", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Department Not Saved", content = @Content) })
	@PostMapping("/department/save")
	public ResponseEntity<DepartmentDTO> saveDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
		return new ResponseEntity<DepartmentDTO>(departmentService.saveDepartment(departmentDTO), HttpStatus.CREATED);
	}

	@Operation(summary = "GET ALL DEPARTMENTS", description = "This Is To Fetch Get All Departments From Database")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Found Departments", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Departments Not Found", content = @Content) })
	@GetMapping("/department/list")
	public ResponseEntity<DepartmentResponseDTO> getAllDepartments(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NO, required = true) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = true) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = true) String sortBy) {

		return new ResponseEntity<>(departmentService.getAllDepartments(pageNo, pageSize, sortBy), HttpStatus.OK);
	}

	@Operation(summary = "GET ONE DEPARTMENT", description = "This Is To Fetch Get One Department From Database")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Found Department", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Department Not Found", content = @Content) })
	@GetMapping("/department/dept/{department-id}")
	public ResponseEntity<DepartmentDTO> getOneDepartment(@PathVariable(value = "department-id") Integer departmentId) {
		return new ResponseEntity<>(departmentService.getOneDepartmentById(departmentId), HttpStatus.OK);
	}

	@Operation(summary = "GET DEPARTMENT BY CODE", description = "This Is To Fetch Get One Department By Code From Database")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Found Department", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Department Not Found", content = @Content) })
	@GetMapping("/department/dpmt/{department-code}")
	public ResponseEntity<DepartmentDTO> getOneDepartmentByCode(
			@PathVariable(value = "department-code") String departmentCode) {
		return new ResponseEntity<>(departmentService.getDepartmentByCode(departmentCode), HttpStatus.OK);
	}

	@Operation(summary = "UPDATE DEPARTMENT BY ID", description = "This Is To Update Department By Id into Database")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Updated Department", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Department Not Updated", content = @Content) })
	@PutMapping("/department/update/{department-id}")
	public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable(value = "department-id") Integer departmentId,
			@Valid @RequestBody DepartmentDTO departmentDTO) {
		return new ResponseEntity<>(departmentService.updateDepartmentById(departmentId, departmentDTO), HttpStatus.OK);
	}

	@Operation(summary = "DELETE DEPARTMENT BY ID", description = "This Is To Delete Department By Id From Database")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Deleted Department", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Department Not Deleted", content = @Content) })
	@DeleteMapping("/department/delete/{department-id}")
	public ResponseEntity<String> deleteDepartment(@PathVariable(value = "department-id") Integer departmentId) {
		return new ResponseEntity<>(departmentService.deleteDepartment(departmentId), HttpStatus.OK);
	}

}
