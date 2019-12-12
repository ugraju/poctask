package com.ibm.upskill.microcervices.currencyexchangeservice.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.upskill.microcervices.currencyexchangeservice.bo.CurrencyExchangeValueModel;
import com.ibm.upskill.microcervices.currencyexchangeservice.request.CurrencyExchangeRequest;

/**
 * @author GangaRajuUdimudi
 *
 */

@RestController
@RequestMapping("currency-exchange/v1/")
public class CurrencyExchangeController {
	
	@Autowired
	private CurrencyExchangeValueModel currencyExchangeModel;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "conversionFactor", method = RequestMethod.POST)
	public String saveConversionFactor(@RequestBody CurrencyExchangeRequest request) {
		logger.info("saveConversionFactor =>"+request.toString());
		return currencyExchangeModel.addConversionFactor(request);
	}
	
	@RequestMapping(value = "from/{from}/to/{to}", method = RequestMethod.GET)
	public String getConversionFactor(@PathVariable String from, @PathVariable String to) {
		logger.info("getConversionFactor ==> from = "+from+" to = "+to);
		return currencyExchangeModel.getConversionFactor(new CurrencyExchangeRequest(from, to));
	}
	
	@RequestMapping(value = "conversionFactor/{id}", method = RequestMethod.PUT)
	public String updateConversionFactor(@PathVariable Long id, @RequestBody CurrencyExchangeRequest request) {
		request.setId(id);
		logger.info("updateConversionFactor ==>"+request.toString());
		return currencyExchangeModel.updateConversionFactor(id, request);
	}

}
