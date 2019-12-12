/**
 * 
 */
package com.ibm.upskill.microcervices.currencyconversioneservice;

import java.math.BigDecimal;

/**
 * @author GangaRajuUdimudi
 *
 */
public class ConversionFactorRequest {

	private Long id;
	private String from;
	private String to;
	private BigDecimal conversionFactor;
	
	public ConversionFactorRequest() {
	}

	public ConversionFactorRequest(String fromCountryCode, String toCountryCode, BigDecimal conversionFactor) {
		super();
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

	public void setFrom(String fromCountryCode) {
		this.from = fromCountryCode;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String toCountryCode) {
		this.to = toCountryCode;
	}

	public BigDecimal getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(BigDecimal conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	@Override
	public String toString() {
		return "ConversionFactorRequest [fromCountryCode=" + from + ", toCountryCode=" + to
				+ ", conversionFactor=" + conversionFactor + "]";
	}

}
