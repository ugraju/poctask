/**
 * 
 */
package com.ibm.upskill.microcervices.currencyexchangeservice.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author GangaRajuUdimudi
 *
 */
public class FallBackBean {

	private Map<String, BigDecimal> rates;
	private String base;
	private Date date;
	
	public FallBackBean() {
	}

	public String getBase() {
		return base;
	}
	
	public void setBase(String base) {
		this.base = base;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Map<String, BigDecimal> getRates() {
		return rates;
	}

	public void setRates(Map<String, BigDecimal> rates) {
		this.rates = rates;
	}

	@Override
	public String toString() {
		return "FallBackBean [rates=" + rates + ", base=" + base + ", date=" + date + "]";
	}
}