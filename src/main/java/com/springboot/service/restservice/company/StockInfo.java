package com.springboot.service.restservice.company;

import java.util.Date;

/**
 * Class that depicts stock information for a symbol on a specified date.
 * 
 * @author Tausif Farooqi
 *
 */
public class StockInfo {
	private String symbol;
	private Date date;
	private int volume;
	private double openValue;
	private double closeValue;
	private double highValue;
	private double lowValue;
	
	/**
	 * @param symbol Stock ticker symbol
	 * @param date Stock trade date
	 * @param volume Stock's trade volume on the given date
	 * @param openValue Stock's opening value on the given date
	 * @param closeValue Stock's closing value on the given date
	 * @param highValue Stock's highest value on the given date 
	 * @param lowValue Stock's lowest value on the given date
	 */
	public StockInfo(String symbol, 
			Date date, 
			int volume, 
			double openValue, 
			double closeValue, 
			double highValue,
			double lowValue) {
		super();
		this.symbol = symbol;
		this.date = date;
		this.volume = volume;
		this.openValue = openValue;
		this.closeValue = closeValue;
		this.highValue = highValue;
		this.lowValue = lowValue;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public double getOpenValue() {
		return openValue;
	}

	public void setOpenValue(double openValue) {
		this.openValue = openValue;
	}

	public double getCloseValue() {
		return closeValue;
	}

	public void setCloseValue(double closeValue) {
		this.closeValue = closeValue;
	}

	public double getHighValue() {
		return highValue;
	}

	public void setHighValue(double highValue) {
		this.highValue = highValue;
	}

	public double getLowValue() {
		return lowValue;
	}

	public void setLowValue(double lowValue) {
		this.lowValue = lowValue;
	}
}
