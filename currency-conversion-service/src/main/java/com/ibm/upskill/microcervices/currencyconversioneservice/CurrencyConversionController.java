package com.ibm.upskill.microcervices.currencyconversioneservice;

import java.io.IOException;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author GangaRajuUdimudi
 *
 */
@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	@Autowired
	private ObjectMapper objMapper;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping("currency-converter-feign/v1/conversionFactor")
	public ConversionFactorResponse saveConversionFactor(@RequestBody ConversionFactorRequest conversionFactorRequest) {
		logger.info("saveConversionFactor =>"+conversionFactorRequest);
		String responseJSONStr = proxy.saveConversionFactor(conversionFactorRequest);
		ConversionFactorResponse response = null;
		try{
			response = objMapper.readValue(responseJSONStr, ConversionFactorResponse.class);
			logger.info("{}", response);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		logger.info("Response : {}"+response);
		return response;		
	}
	
	@PutMapping("currency-converter-feign/v1/conversionFactor/{id}")
	public ConversionFactorResponse updateConversionFactor(@PathVariable Long id, @RequestBody ConversionFactorRequest conversionFactorRequest) {
		ConversionFactorResponse response = null;
		try {
			conversionFactorRequest.setId(id);
			String responseJSONStr = proxy.updateConversionFactor(id, conversionFactorRequest);
			logger.info("updateConversionFactor =>"+conversionFactorRequest);
			response = objMapper.readValue(responseJSONStr, ConversionFactorResponse.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		logger.info("Response : {}"+response);
		return response;		
	}
	
	@GetMapping("currency-converter-feign/v1/from/{from}/to/{to}")
	public ConversionFactorResponse getLatestExchangeRates(@PathVariable String from,
			@PathVariable String to) throws IOException {
		ConversionFactorResponse response = null;
		try {
			String responseJSONStr = proxy.getConversionFactor(from, to);
			response = objMapper.readValue(responseJSONStr, ConversionFactorResponse.class);
			logger.info("{}", response);
			if (Integer.parseInt(response.getStatusCode()) > 299)
				throw new IOException();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@GetMapping("currency-converter-feign/v1/from/{from}/to/{to}/quantity/{quantity}")
	public ConversionFactorResponse convertCurrencyFeign(@PathVariable String from,
			@PathVariable String to, @PathVariable BigDecimal quantity) {
		ConversionFactorResponse response = null;
		try {
			String responseJSONStr = proxy.getConversionFactor(from, to);
			response = objMapper.readValue(responseJSONStr, ConversionFactorResponse.class);
			logger.info("{}", response);
			CurrencyConversionBean responseObj = response.getExchangeValue();
			BigDecimal conversionFactor = responseObj.getConversionMultiple();
			responseObj.setquantity(quantity);
			responseObj.setTotalCalaculatedAmount(quantity.multiply(conversionFactor));
			logger.info("{}", responseObj);
			response.setExchangeValue(responseObj);
			logger.info("{}", response);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return response;
	}

}
