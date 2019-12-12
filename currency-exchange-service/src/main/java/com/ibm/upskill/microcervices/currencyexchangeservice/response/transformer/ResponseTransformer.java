/**
 * 
 */
package com.ibm.upskill.microcervices.currencyexchangeservice.response.transformer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.upskill.microcervices.currencyexchangeservice.dao.ExchangeValueDao;
import com.ibm.upskill.microcervices.currencyexchangeservice.response.CurrencyExchangeResponse;
import com.ibm.upskill.microcervices.currencyexchangeservice.response.FallBackBean;
import com.ibm.upskill.microcervices.currencyexchangeservice.util.CurrencyExchangeConstants;

/**
 * @author GangaRajuUdimudi
 *
 */

public class ResponseTransformer {
	
	private static final Logger logger = LoggerFactory.getLogger(ResponseTransformer.class);

	private static final Map<String, String> statusCodesMap;
	
	private static JsonGenerator jsonGenerator = null;
	private static ObjectMapper objectMapper = null;
	
	static {
		statusCodesMap = new HashMap<String, String>();
		statusCodesMap.put(CurrencyExchangeConstants.HTTP_SUCCESS_STATUS_CODE, CurrencyExchangeConstants.HTTP_SUCCESS_STATUS_MSG);
		statusCodesMap.put(CurrencyExchangeConstants.HTTP_BAD_REQ_STATUS_CODE, CurrencyExchangeConstants.HTTP_BAD_REQ_STATUS_MSG);
		statusCodesMap.put(CurrencyExchangeConstants.HTTP_CREATED_STATUS_CODE, CurrencyExchangeConstants.HTTP_CREATED_STATUS_MSG);
		statusCodesMap.put(CurrencyExchangeConstants.HTTP_NO_DATA_FOUND_STATUS_CODE, CurrencyExchangeConstants.HTTP_NO_DATA_FOUND_STATUS_MSG);
		statusCodesMap.put(CurrencyExchangeConstants.HTTP_DUPLICATE_STATUS_CODE, CurrencyExchangeConstants.HTTP_DUPLICATE_ENTRY_MSG);
		statusCodesMap.put(CurrencyExchangeConstants.HTTP_INTERNAL_SERVER_ERROR_STATUS_CODE, CurrencyExchangeConstants.HTTP_INTERNAL_SERVER_ERROR_STATUS_MSG);
	}
	
	public static String transformCurrencyExchangeResponse(String statusCode, ExchangeValueDao exchangeValueDao) {
		String objJackson = null;
		try {
			logger.info("transformCurrencyExchangeResponse => {}", exchangeValueDao);
			CurrencyExchangeResponse responseObj = new CurrencyExchangeResponse(statusCode, statusCodesMap.get(statusCode), exchangeValueDao);
			logger.info(": responseObj ===>"+responseObj);
			objectMapper = new ObjectMapper();
			jsonGenerator = objectMapper.getFactory().createGenerator(System.out, JsonEncoding.UTF8);
		    jsonGenerator.writeObject(responseObj); 
			objJackson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseObj);
			logger.info("objJackson =>"+objJackson);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (jsonGenerator != null || !jsonGenerator.isClosed()) {
				try {
					jsonGenerator.flush();
					jsonGenerator.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return objJackson;
	}

	public static String transformToResponse(String to, String statusCode, FallBackBean latestCurrencyObj) {
		String objJackson = null;
		try {
			logger.info("transformToResponse => {}", latestCurrencyObj);
			CurrencyExchangeResponse responseObj = new CurrencyExchangeResponse(statusCode, statusCodesMap.get(statusCode), 
					new ExchangeValueDao(latestCurrencyObj.getBase(), to,latestCurrencyObj.getRates().get(to)));
			logger.info(": responseObj ===>"+responseObj);
			objectMapper = new ObjectMapper();
			jsonGenerator = objectMapper.getFactory().createGenerator(System.out, JsonEncoding.UTF8);
		    jsonGenerator.writeObject(responseObj); 
			objJackson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseObj);
			logger.info("objJackson =>"+objJackson);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (jsonGenerator != null || !jsonGenerator.isClosed()) {
				try {
					jsonGenerator.flush();
					jsonGenerator.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return objJackson;
	}
}
