package com.springboot.service.restservice.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.springboot.service.restservice.repository.CompanyRepository;
import com.springboot.service.restservice.resource.CompaniesResource;
import com.springboot.service.restservice.resource.Company;
import com.springboot.service.restservice.resource.CompanyResource;

/**
 * Controller class that allows operations on the Company resource. We can get/add/update/delete 
 * company information here.
 * 
 * @author Tausif Farooqi
 *
 */
@RestController
public class CompanyController {
	private static FilterProvider COMPANY_FILTER_PROVIDER = getFilterProvider();
	
	@Autowired
	private CompanyRepository companyRepository;
	
	/**
	 * @return
	 */
	@GetMapping("/companies")
	public CompaniesResource getAllCompanies(Pageable pageable) {
		Page<Company> companies = companyRepository.findAll(pageable);
		return new CompaniesResource(companies.getContent());
	}
	
	/**
	 * @param symbol
	 * @return
	 */
	@GetMapping("/companies/{symbol}")
	public CompanyResource getCompany(@PathVariable String symbol) {
		Optional<Company> company = companyRepository.findById(symbol);
		
		if (!company.isPresent()) {
			// throw exception
		}
		
		return new CompanyResource(company.get());
	}
	
	/**
	 * @param company
	 */
	@PostMapping("/companies")
	public ResponseEntity<Company> addCompany(@RequestBody Company company) {
		Company createdCompany = companyRepository.save(company);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{symbol}")
				.buildAndExpand(createdCompany.getSymbol())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * @param company
	 */
	@PutMapping("/companies/{symbol}")
	public void updateCompany(@RequestBody Company company) {
		
	}
	
	/**
	 * @param symbol
	 */
	@DeleteMapping("/companies/{symbol}")
	public void deleteCompany(@PathVariable String symbol) {
		companyRepository.deleteById(symbol);
	}
	
	/**
	 * Dynamically filters out fields that are not required.
	 * 
	 * @param company
	 * @return
	 */
	@SuppressWarnings("unused")
	private MappingJacksonValue filter(Company company) {
		MappingJacksonValue mapping = new MappingJacksonValue(company);
		mapping.setFilters(COMPANY_FILTER_PROVIDER);
		return mapping;
	}
	
	/**
	 * FilterProvider for filtering out Company fields.
	 * 
	 * @return
	 */
	private static FilterProvider getFilterProvider() {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("CompanyFilter", filter);
		return filterProvider;
	}
}
