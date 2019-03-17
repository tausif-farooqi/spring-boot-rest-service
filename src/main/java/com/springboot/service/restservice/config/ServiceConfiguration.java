package com.springboot.service.restservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration parameter values for our service.
 * 
 * @author Tausif Farooqi
 *
 */
@Component
@ConfigurationProperties("company-stock-history")
public class ServiceConfiguration {
	private int maxRecordsPerPage;

	/**
	 * Gets the maximum number of items to return in an API response.
	 * 
	 * @return
	 */
	public int getMaxRecordsPerPage() {
		return maxRecordsPerPage;
	}

	/**
	 * 
	 * Sets the maximum number of items to return in an API response.
	 * 
	 * @param maxRecordsPerPage
	 */
	public void setMaxRecordsPerPage(int maxRecordsPerPage) {
		this.maxRecordsPerPage = maxRecordsPerPage;
	}
}
