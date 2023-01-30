package com.swamy.client;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.swamy.dto.DepartmentDTO;

@FeignClient(name = "DEPARTMENT-SERVICE")
//@LoadBalancerClient(name = "mail-service", configuration = LoadBalancerConfiguration.class)
public interface ApiDepartmentClient {

	@GetMapping("/api/v2/department/dpmt/{department-code}")
	public DepartmentDTO getOneDepartmentByCode(
			@PathVariable(value = "department-code") String departmentCode);
}
