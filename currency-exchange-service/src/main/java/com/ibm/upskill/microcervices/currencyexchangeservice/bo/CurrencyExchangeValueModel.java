/**
 * 
 */
package com.ibm.upskill.microcervices.currencyexchangeservice.bo;

import org.springframework.stereotype.Component;

import com.ibm.upskill.microcervices.currencyexchangeservice.request.CurrencyExchangeRequest;

/**
 * @author GangaRajuUdimudi
 *
 */
@Component
public interface CurrencyExchangeValueModel {

	public String addConversionFactor(CurrencyExchangeRequest request);
//	public String getConversionFactor(String from, String to);
	public String updateConversionFactor(Long id, CurrencyExchangeRequest request);
	public String getConversionFactor(CurrencyExchangeRequest currencyExchangeRequest);
}
