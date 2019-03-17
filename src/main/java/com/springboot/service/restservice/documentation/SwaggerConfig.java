package com.springboot.service.restservice.documentation;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration for auto-generating Swagger documentation for our REST APIs.
 * 
 * @author Tausif Farooqi
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	private static final HashSet<String> CONSUMES_PRODUCES = new HashSet<String>(Arrays.asList("application/json"));
	
	private static ApiInfo API_INFO = new ApiInfoBuilder()
			.description("API for getting company names that are traded in NASDAQ, and also their stock history")
			.title("NASDAQ Historical Stock Information")
			.version("1.0")
			.build();
	
	@Bean
	public Docket getAPIDocBuilder() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(API_INFO)
				.produces(CONSUMES_PRODUCES)
				.consumes(CONSUMES_PRODUCES);
	}
}
