package com.springboot.service.restservice.resource;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.BasicLinkBuilder;

/**
 * Wrapper around {@link Company} entity that provides various HATEOS links.
 * 
 * @author Tausif Farooqi
 *
 */
public class CompanyResource extends ResourceSupport {
	private static final String PATH_STOCK_INFOS = "stockInfos";
	private static final String PATH_COMPANIES = "companies";
	private static final String REL_STOCK_INFO = "stockInfo";
	private final Company company;

	/**
	 * Constructor that takes in a company and decorates it with HATEOAS links.
	 * 
	 * @param company
	 */
	public CompanyResource(final Company company) {
		this.company = company;
		final String symbol = company.getSymbol();
		
		// add HATEOS links
		// self
		add(BasicLinkBuilder.linkToCurrentMapping().slash(PATH_COMPANIES).slash(symbol).withSelfRel());
		// stock info
		add(BasicLinkBuilder.linkToCurrentMapping().slash(PATH_COMPANIES).slash(symbol).slash(PATH_STOCK_INFOS).withRel(REL_STOCK_INFO));
	}

	/**
	 * @return
	 */
	public Company getCompany() {
		return company;
	}
}
