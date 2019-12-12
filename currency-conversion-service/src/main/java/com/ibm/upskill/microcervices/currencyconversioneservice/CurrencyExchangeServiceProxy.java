/**
 * 
 */
package com.ibm.upskill.microcervices.currencyconversioneservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author GangaRajuUdimudi
 *
 */
@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
	@GetMapping("currency-exchange-service/currency-exchange/v1/from/{from}/to/{to}")
	public String getConversionFactor(
			@PathVariable("from") String from, 
			@PathVariable("to") String to);
	
	@PostMapping("currency-exchange-service/currency-exchange/v1/conversionFactor")
	public String saveConversionFactor(
			ConversionFactorRequest conversionFactorRequest);
	
	@PutMapping("currency-exchange-service/currency-exchange/v1/conversionFactor/{id}")
	public String updateConversionFactor(@PathVariable("id") Long id,
			ConversionFactorRequest conversionFactorRequest);
	
}
