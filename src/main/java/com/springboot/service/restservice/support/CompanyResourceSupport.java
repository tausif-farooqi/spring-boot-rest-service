package com.springboot.service.restservice.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.BasicLinkBuilder;

import com.springboot.service.restservice.resource.Company;

/**
 * Utility class to add HATEOAS links to the {@link Company} entity.
 * 
 * @author Tausif Farooqi
 *
 */
public class CompanyResourceSupport extends ResourceSupport {
	private static final String PATH_STOCK_INFOS = "stockInfos";
	private static final String PATH_COMPANIES = "companies";
	private static final String REL_STOCK_INFO = "stockInfo";

	/**
	 * Static method that takes in a company, converts it to a {@link Resource} and decorates it with HATEOAS links.
	 * 
	 * @param company
	 */
	public static Resource<Company> buildResource(Company company) {
		Resource<Company> resource = new Resource<Company>(company);
		final String symbol = company.getSymbol();
		
		// add HATEOS links
		// self
		resource.add(BasicLinkBuilder.linkToCurrentMapping().slash(PATH_COMPANIES).slash(symbol).withSelfRel());
		// stock info
		resource.add(BasicLinkBuilder.linkToCurrentMapping().slash(PATH_COMPANIES).slash(symbol).slash(PATH_STOCK_INFOS).withRel(REL_STOCK_INFO));
		
		return resource;
	}
	
	/**
	 * Static method that takes in a collection of companies, converts them to resources and decorates each company 
	 * with HATEOAS links.
	 * 
	 * @param companies
	 */
	public static List<Resource<Company>> buildResources(Collection<Company> companies) {
		List<Resource<Company>> companyResources = new ArrayList<Resource<Company>>();
		for (Company company : companies) {
			companyResources.add(buildResource(company));
		}
		
		return companyResources;
	}
}
