/**
 * 
 */
package com.ibm.upskill.microcervices.currencyconversioneservice;

import java.math.BigDecimal;

/**
 * @author GangaRajuUdimudi
 *
 */
public class CurrencyConversionBean {
	
	private Long id;
	private String from;
	private String to;
	private BigDecimal conversionMultiple;
	private BigDecimal quantity;
	private BigDecimal totalCalaculatedAmount;
	
	public CurrencyConversionBean() {
		
	}
	
	public CurrencyConversionBean(Long id, String from, String to, BigDecimal conversionMultiple) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
	}
	
	public CurrencyConversionBean(Long id, String from, String to, BigDecimal conversionMultiple, BigDecimal quantity,
			BigDecimal totalCalaculatedAmount) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
		this.quantity = quantity;
		this.totalCalaculatedAmount = totalCalaculatedAmount;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}

	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}

	public BigDecimal getquantity() {
		return quantity;
	}

	public void setquantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalCalaculatedAmount() {
		return totalCalaculatedAmount;
	}

	public void setTotalCalaculatedAmount(BigDecimal totalCalaculatedAmount) {
		this.totalCalaculatedAmount = totalCalaculatedAmount;
	}

	@Override
	public String toString() {
		return "CurrencyConversionBean [id=" + id + ", from=" + from + ", to=" + to + ", conversionMultiple="
				+ conversionMultiple + ", quantity=" + quantity + ", totalCalaculatedAmount=" + totalCalaculatedAmount
				+ "]";
	}
	
}
