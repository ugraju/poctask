/**
 * 
 */
package com.ibm.upskill.microcervices.currencyconversioneservice;

/**
 * @author GangaRajuUdimudi
 *
 */
public class ConversionFactorResponse {
	
	private String statusCode;
	private String statusMessage;
	private CurrencyConversionBean exchangeValue;

	public ConversionFactorResponse() {
	}

	public ConversionFactorResponse(String statusCode, String statusMessage,
										CurrencyConversionBean currencyConversionBean) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.exchangeValue = currencyConversionBean;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public CurrencyConversionBean getExchangeValue() {
		return exchangeValue;
	}

	public void setExchangeValue(CurrencyConversionBean currencyConversionBean) {
		this.exchangeValue = currencyConversionBean;
	}

	@Override
	public String toString() {
		return "ConversionFactorResponse [statusCode=" + statusCode + ", statusMessage=" + statusMessage
				+ ", currencyConversionBean=" + exchangeValue + "]";
	}

	

}
