package com.springboot.service.restservice.resource;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.springboot.service.restservice.controller.CompanyController;
import com.springboot.service.restservice.controller.StockInfoController;

/**
 * Wrapper around {@link Company} entity that provides various HATEOS links.
 * 
 * @author Tausif Farooqi
 *
 */
public class CompanyResource extends ResourceSupport {
	private static final String STOCK_INFO = "stockInfo";
	private static final String SELF = "self";
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
		Object companyResource = ControllerLinkBuilder.methodOn(CompanyController.class, symbol).getCompany(symbol);
		add(ControllerLinkBuilder.linkTo(companyResource).withRel(SELF));
		
		Object stockInfoResource = ControllerLinkBuilder.methodOn(StockInfoController.class, symbol).getAllStockInfosForComany(symbol, null);
		add(ControllerLinkBuilder.linkTo(stockInfoResource).withRel(STOCK_INFO));
	}

	/**
	 * @return
	 */
	public Company getCompany() {
		return company;
	}
}
