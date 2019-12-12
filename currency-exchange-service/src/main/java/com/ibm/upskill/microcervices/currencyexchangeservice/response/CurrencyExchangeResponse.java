/**
 * 
 */
package com.ibm.upskill.microcervices.currencyexchangeservice.response;

import com.ibm.upskill.microcervices.currencyexchangeservice.dao.ExchangeValueDao;

/**
 * @author GangaRajuUdimudi
 *
 */
public class CurrencyExchangeResponse {
	private String statusCode;
	private String statusMessage;
	private ExchangeValueDao exchangeValue;
		
	public CurrencyExchangeResponse() {
		super();
	}

	public CurrencyExchangeResponse(String statusCode, String statusMessage, ExchangeValueDao exchangeValue) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.exchangeValue = exchangeValue;
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

	public ExchangeValueDao getExchangeValue() {
		return exchangeValue;
	}

	public void setExchangeValue(ExchangeValueDao exchangeValue) {
		this.exchangeValue = exchangeValue;
	}

	@Override
	public String toString() {
		return "CurrencyExchangeResponse [statusCode=" + statusCode + ", statusMessage=" + statusMessage
				+ ", exchangeValue=" + exchangeValue + "]";
	}

	
}
