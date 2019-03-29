package com.springboot.service.restservice.support;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.support.BaseUriLinkBuilder;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.BasicLinkBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.service.restservice.resource.Company;

/**
 * Utility class to add HATEOAS links to the {@link Company} entity.
 * 
 * @author Tausif Farooqi
 *
 */
public class CompanyResourceSupport extends ResourceSupport {
	private static final String PAGE_NUM = "page";
	private static final String REL_NEXT = "next";
	private static final String REL_PREVIOUS = "prev";
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
	 * with HATEOAS links. It also adds paging information to the result set.
	 * 
	 * @param page
	 */
	public static PagedResources<Resource<Company>> buildResources(Page<Company> page) {
		int numberOfElements = page.getNumberOfElements();
		int currentPageNumber = page.getNumber();
		long totalElements = page.getTotalElements();
		int totalPages = page.getTotalPages();
		
		PagedResources.PageMetadata pageData = new PagedResources.PageMetadata(numberOfElements, currentPageNumber, totalElements, totalPages);
		Collection<Company> companies = page.getContent();
		
		List<Resource<Company>> companyResources = new ArrayList<Resource<Company>>();
		for (Company company : companies) {
			companyResources.add(buildResource(company));
		}
		
		PagedResources<Resource<Company>> pagedResources = new PagedResources<Resource<Company>>(companyResources, pageData);
		// add HATEOS links
		
		// self
		BasicLinkBuilder linkBuilder = BasicLinkBuilder.linkToCurrentMapping().slash(PATH_COMPANIES);
		pagedResources.add(linkBuilder.withSelfRel());
		
		// previous and next pages
		UriComponents component = null;
		URI uri = linkBuilder.toUri();
		
		// show only prev page link if previous page index is greater than or equal to zero
		int prevPageNumber = currentPageNumber - 1;
		if (prevPageNumber >= 0) {
			component = UriComponentsBuilder.fromUri(uri).queryParam(PAGE_NUM, prevPageNumber).build();
			pagedResources.add(BaseUriLinkBuilder.create(component.toUri()).withRel(REL_PREVIOUS));
		}
		
		// show only next page link if next page index is less than the total number of available pages
		int nextPageNumber = currentPageNumber + 1;
		if (nextPageNumber < totalPages) {
			component = UriComponentsBuilder.fromUri(uri).queryParam(PAGE_NUM, nextPageNumber).build();
			pagedResources.add(BaseUriLinkBuilder.create(component.toUri()).withRel(REL_NEXT));
		}
		
		return pagedResources;
	}
}
