package com.springboot.service.restservice.company;

/**
 * A simple class depicting a company whose stocks are traded in stock markets.
 * 
 * @author Tausif Farooqi
 *
 */
public class Company {
	private String symbol;
	private String name;
	
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
