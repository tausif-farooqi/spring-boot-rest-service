package com.springboot.service.restservice.support;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.support.BaseUriLinkBuilder;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.BasicLinkBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.service.restservice.resource.StockInfo;

/**
 * Utility class to add HATEOAS links to the {@link StockInfo} entity.
 * 
 * @author Tausif Farooqi
 *
 */
public class StockInfoResourceSupport extends ResourceSupport {
	private static final String PAGE_NUM = "page";
	private static final String REL_NEXT = "next";
	private static final String REL_PREVIOUS = "prev";
	private static final String PATH_COMPANIES = "companies";
	private static final String PATH_STOCK_INFOS = "stockInfos";

	/**
	 * Static method that takes in a stock info item, converts it to a {@link Resource} and decorates it with HATEOAS links.
	 * 
	 * @param stockInfo
	 */
	public static Resource<StockInfo> buildResource(StockInfo stockInfo) {
		Resource<StockInfo> resource = new Resource<StockInfo>(stockInfo);
		final Integer id = stockInfo.getId();
		
		// add HATEOS links
		// self
		resource.add(BasicLinkBuilder.linkToCurrentMapping().slash(PATH_STOCK_INFOS).slash(id).withSelfRel());
		
		return resource;
	}
	
	/**
	 * Static method that takes in a collection of stock infos, converts them to resources and decorates each item 
	 * with HATEOAS links. It also adds paging information to the result set.
	 * 
	 * @param page
	 */
	public static PagedResources<Resource<StockInfo>> buildResources(Page<StockInfo> page) {
		int numberOfElements = page.getNumberOfElements();
		int currentPageNumber = page.getNumber();
		long totalElements = page.getTotalElements();
		int totalPages = page.getTotalPages();
		
		PagedResources.PageMetadata pageData = new PagedResources.PageMetadata(numberOfElements, currentPageNumber, totalElements, totalPages);
		List<StockInfo> stockInfos = page.getContent();
		
		List<Resource<StockInfo>> stockInfoResources = new ArrayList<Resource<StockInfo>>();
		for (StockInfo stockInfo : stockInfos) {
			stockInfoResources.add(buildResource(stockInfo));
		}
		
		PagedResources<Resource<StockInfo>> pagedResources = new PagedResources<Resource<StockInfo>>(stockInfoResources, pageData);
		
		if (stockInfos.isEmpty()) {
			// no need to add links if there are no resources
			return pagedResources;
		}
		
		// add HATEOS links
		
		// self
		BasicLinkBuilder linkBuilder = BasicLinkBuilder.linkToCurrentMapping().slash(PATH_COMPANIES).slash(stockInfos.get(0).getSymbol()).slash(PATH_STOCK_INFOS);
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
