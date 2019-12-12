/**
 * 
 */
package com.ibm.upskill.microcervices.currencyexchangeservice.request;

import java.math.BigDecimal;

/**
 * @author GangaRajuUdimudi
 *
 */
public class CurrencyExchangeRequest{
	
	private Long id;
	private String from;
	private String to;
	private BigDecimal conversionFactor;
	
	public CurrencyExchangeRequest() {
		super();
	}

	public CurrencyExchangeRequest(String fromCountryCode, String toCountryCode) {
		super();
		this.from = fromCountryCode;
		this.to = toCountryCode;
	}
	
	public CurrencyExchangeRequest(Long id, String fromCountryCode, String toCountryCode, BigDecimal conversionFactor) {
		super();
		this.id = id;
		this.from = fromCountryCode;
		this.to = toCountryCode;
		this.conversionFactor = conversionFactor;
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

	public void setToCountryCode(String to) {
		this.to = to;
	}

	public BigDecimal getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(BigDecimal conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	@Override
	public String toString() {
		return "CurrencyExchangeRequest [id=" + id + ", from=" + from + ", to=" + to + ", conversionFactor="
				+ conversionFactor + "]";
	}

}
