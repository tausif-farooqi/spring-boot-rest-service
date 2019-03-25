package com.springboot.service.restservice.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;

/**
 * Wrapper around {@link Company} collection for providing HATEOAS links to each entry in the collection.
 * 
 * @author Tausif Farooqi
 *
 */
public class CompaniesResource extends ResourceSupport {
	private final Resources<CompanyResource> companies;
	
	/**
	 * @param companyList
	 */
	public CompaniesResource(List<Company> companyList) {
		List<CompanyResource> resources = new ArrayList<CompanyResource>();

		for (Company company : companyList) {
			resources.add(new CompanyResource(company));
		}
		
		this.companies = new Resources<CompanyResource>(resources);
	}

	/**
	 * @return
	 */
	public Resources<CompanyResource> getCompanies() {
		return companies;
	}
}
