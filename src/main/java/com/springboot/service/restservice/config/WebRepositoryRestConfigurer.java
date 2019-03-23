package com.springboot.service.restservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.springboot.service.restservice.resource.Company;
import com.springboot.service.restservice.resource.StockInfo;

/**
 * Configurer that exposes the Ids of {@link Company} and {@link StockInfo} entities in REST responses.
 * 
 * @author Tausif Farooqi
 *
 */
@Configuration
public class WebRepositoryRestConfigurer implements RepositoryRestConfigurer {
	
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Company.class);
        config.exposeIdsFor(StockInfo.class);
    }
}
