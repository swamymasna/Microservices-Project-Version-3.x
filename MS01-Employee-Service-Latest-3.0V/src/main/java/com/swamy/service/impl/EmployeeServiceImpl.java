package com.swamy.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swamy.client.ApiDepartmentClient;
import com.swamy.client.ApiOrganizationClient;
import com.swamy.dto.ApiResponseDTO;
import com.swamy.dto.DepartmentDTO;
import com.swamy.dto.EmployeeDTO;
import com.swamy.dto.OrganizationDTO;
import com.swamy.entity.Employee;
import com.swamy.exception.ResourceNotFoundException;
import com.swamy.repository.EmployeeRepository;
import com.swamy.service.IEmployeeService;
import com.swamy.utils.AppConstants;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ApiDepartmentClient apiDepartmentClient;

	@Autowired
	private ApiOrganizationClient apiOrganizationClient;

//	@Autowired
//	private WebClient.Builder webClientBuilder;

//	@Autowired
//	private RestTemplate restTemplate;

	@Override
	public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {

		Employee employee = modelMapper.map(employeeDTO, Employee.class);
		Employee savedEmployee = employeeRepository.save(employee);
		EmployeeDTO employeeResponse = modelMapper.map(savedEmployee, EmployeeDTO.class);
		return employeeResponse;
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {

		List<Employee> employeesList = employeeRepository.findAll();

		return employeesList.stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class))
				.collect(Collectors.toList());

	}

//	@Retry(name = "EMPLOYEE-SERVICE", fallbackMethod = "defaultGetOneEmployeeById")
	@CircuitBreaker(name = "EMPLOYEE-SERVICE", fallbackMethod = "defaultGetOneEmployeeById")
	@Override
	public ApiResponseDTO getOneEmployeeById(Integer employeeId) {

		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.EMPLOYEE_NOT_FOUND + employeeId));

		EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);

		DepartmentDTO departmentDTO = apiDepartmentClient.getOneDepartmentByCode(employeeDTO.getDepartmentCode());

		OrganizationDTO organizationDTO = apiOrganizationClient.getOrganizationByCode(employee.getOrganizationCode());

//		DepartmentDTO departmentDTO2 = webClientBuilder.build().get()
//				.uri("http://DEPARTMENT-SERVICE/api/v2/department/dpmt/" + employee.getDepartmentCode())
//				.retrieve()
//				.bodyToMono(DepartmentDTO.class).block();

//		DepartmentDTO departmentDTO3 = restTemplate
//				.getForEntity("http://DEPARTMENT-SERVICE/api/v2/department/dpmt/" + employee.getDepartmentCode(),
//						DepartmentDTO.class)
//				.getBody();

		ApiResponseDTO apiResponse = new ApiResponseDTO();
		apiResponse.setEmployeeDTO(employeeDTO);
		apiResponse.setDepartmentDTO(departmentDTO);
		apiResponse.setOrganizationDTO(organizationDTO);
//		apiResponse.setDepartmentDTO(departmentDTO2);

		return apiResponse;
	}

	public ApiResponseDTO defaultGetOneEmployeeById(Integer employeeId, Exception exception) {

		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.EMPLOYEE_NOT_FOUND + employeeId));

		EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);

		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setId(0);
		departmentDTO.setDepartmentName("NO-DEPARTMENT-NAME-IS-AVAILABLE");
		departmentDTO.setDepartmentCode("NO-DEPARTMENT-CODE-IS-AVAILABLE");

		OrganizationDTO organizationDTO = new OrganizationDTO();
		organizationDTO.setId(0);
		organizationDTO.setOrganizationName("NO-ORGANIZATION-NAME-IS-AVAILABLE");
		organizationDTO.setOrganizationCode("NO-ORGANIZATION-CODE-IS-AVAILABLE");

		ApiResponseDTO apiResponse = new ApiResponseDTO();
		apiResponse.setEmployeeDTO(employeeDTO);
		apiResponse.setDepartmentDTO(departmentDTO);
		apiResponse.setOrganizationDTO(organizationDTO);

		return apiResponse;
	}

	@Override
	public EmployeeDTO updateEmployeeById(Integer employeeId, EmployeeDTO employeeDTO) {

		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.EMPLOYEE_NOT_FOUND + employeeId));

		employee.setEmployeeName(employeeDTO.getEmployeeName());
		employee.setEmployeeEmail(employeeDTO.getEmployeeEmail());
		employee.setEmployeeSalary(employeeDTO.getEmployeeSalary());
		Employee updatedEmployee = employeeRepository.save(employee);
		EmployeeDTO updatedEmployeeResponse = modelMapper.map(updatedEmployee, EmployeeDTO.class);

		return updatedEmployeeResponse;
	}

	@Override
	public String deleteEmployeeById(Integer employeeId) {

		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.EMPLOYEE_NOT_FOUND + employeeId));

		employeeRepository.deleteById(employee.getId());

		return AppConstants.EMPLOYEE_DELETE_SUCCESS + employeeId;
	}

}
