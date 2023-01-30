package com.swamy.config;

import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@LoadBalanced
	@Bean
	public WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
