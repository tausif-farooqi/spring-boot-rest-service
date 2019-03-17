package com.springboot.service.restservice.resource;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Class that depicts stock information for a symbol on a specified date.
 * 
 * @author Tausif Farooqi
 *
 */
@Entity
public class StockInfo {
	@Id
	@GeneratedValue
	Integer id;
	private Date date;
	private int volume;
	private double openValue;
	private double closeValue;
	private double highValue;
	private double lowValue;
	
	/**
	 * @param date Stock trade date
	 * @param volume Stock's trade volume on the given date
	 * @param openValue Stock's opening value on the given date
	 * @param closeValue Stock's closing value on the given date
	 * @param highValue Stock's highest value on the given date 
	 * @param lowValue Stock's lowest value on the given date
	 */
	public StockInfo(
			Date date,
			int volume, 
			double openValue, 
			double closeValue, 
			double highValue,
			double lowValue) {
		super();
		this.date = date;
		this.volume = volume;
		this.openValue = openValue;
		this.closeValue = closeValue;
		this.highValue = highValue;
		this.lowValue = lowValue;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
