package com.swamy.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.swamy.dto.ApiResponseDTO;
import com.swamy.dto.EmployeeDTO;
import com.swamy.service.IEmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class EmployeeRestController {

	@Autowired
	private IEmployeeService employeeService;

	@PostMapping("/employee/save")
	public ResponseEntity<EmployeeDTO> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
		return new ResponseEntity<EmployeeDTO>(employeeService.saveEmployee(employeeDTO), HttpStatus.CREATED);
	}

	@GetMapping("/employee/list")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
		return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
	}

	@GetMapping("/employee/emp/{employee-id}")
	public ResponseEntity<ApiResponseDTO> getOneEmployeeById(@PathVariable(value = "employee-id") Integer employeeId) {
		return new ResponseEntity<>(employeeService.getOneEmployeeById(employeeId), HttpStatus.OK);
	}

	@PutMapping("/employee/update/{employee-id}")
	public ResponseEntity<EmployeeDTO> updateEmployeeById(@PathVariable(value = "employee-id") Integer employeeId,
			@Valid @RequestBody EmployeeDTO employeeDTO) {
		return new ResponseEntity<>(employeeService.updateEmployeeById(employeeId, employeeDTO), HttpStatus.OK);
	}

	@DeleteMapping("/employee/delete/{employee-id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable(value = "employee-id") Integer employeeId) {
		return new ResponseEntity<>(employeeService.deleteEmployeeById(employeeId), HttpStatus.OK);
	}
}
