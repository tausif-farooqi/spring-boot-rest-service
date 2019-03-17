package com.springboot.service.restservice.resource;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * A simple class depicting a company whose stocks are traded in stock markets.
 * 
 * @author Tausif Farooqi
 *
 */
@ApiModel(description="The company whose stocks are traded at NASDAQ")
@Entity
public class Company {
	@Id
	@ApiModelProperty(required=true, notes="The stock ticker symbol of the company")
	private String symbol;
	
	@ApiModelProperty(required=true, notes="The name of the company")
	private String name;
	
	/**
	 * 
	 */
	public Company() {
		
	}
	
	/**
	 * @param symbol Company stock ticker symbol
	 * @param name Company name
	 */
	public Company(String symbol, String name) {
		super();
		this.symbol = symbol;
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
