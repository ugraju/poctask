/**
 * 
 */
package com.ibm.upskill.microcervices.currencyexchangeservice.bo.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ibm.upskill.microcervices.currencyexchangeservice.dao.ExchangeValueDao;
import com.ibm.upskill.microcervices.currencyexchangeservice.bo.CurrencyExchangeValueModel;
import com.ibm.upskill.microcervices.currencyexchangeservice.repository.ExchangeValueRepository;
import com.ibm.upskill.microcervices.currencyexchangeservice.request.CurrencyExchangeRequest;
import com.ibm.upskill.microcervices.currencyexchangeservice.response.FallBackBean;
import com.ibm.upskill.microcervices.currencyexchangeservice.response.transformer.ResponseTransformer;
import com.ibm.upskill.microcervices.currencyexchangeservice.util.CurrencyExchangeConstants;
import com.ibm.upskill.microcervices.currencyexchangeservice.util.CurrencyExchangeUtils;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author GangaRajuUdimudi
 *
 */
@Service
public class CurrencyExchangeValueModelImpl implements CurrencyExchangeValueModel{

	/**
	 * 
	 */
	@Autowired
	private ExchangeValueRepository repository;
	
	private RestTemplate restTemplate;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public CurrencyExchangeValueModelImpl() {
	}

	@Override
	public String addConversionFactor(CurrencyExchangeRequest request) {
		BigDecimal conversionFactor = request.getConversionFactor() != null ? request.getConversionFactor() : null;
		if (conversionFactor == null) {
			return ResponseTransformer.transformCurrencyExchangeResponse(
					CurrencyExchangeConstants.HTTP_BAD_REQ_STATUS_CODE, null);
		}
		ExchangeValueDao exchangeValueDao = validateCurrencyExchangeRequest(request);
		String currencyExchangeResponse = null;
		if(exchangeValueDao != null) {
			Long id = exchangeValueDao.getId();
			Optional<ExchangeValueDao> exchangeValueDaoChk = repository.findById(id);
			if(exchangeValueDaoChk.isPresent()) {
				ExchangeValueDao saveConversionFactor = repository.save(exchangeValueDao);
				logger.info("Save Response => {}", saveConversionFactor);
				currencyExchangeResponse = ResponseTransformer.transformCurrencyExchangeResponse(
													CurrencyExchangeConstants.HTTP_CREATED_STATUS_CODE,
													saveConversionFactor);
			} else {
				currencyExchangeResponse = ResponseTransformer.transformCurrencyExchangeResponse(
						CurrencyExchangeConstants.HTTP_DUPLICATE_STATUS_CODE, exchangeValueDao);
			}
		} else {
			currencyExchangeResponse = ResponseTransformer.transformCurrencyExchangeResponse(
					CurrencyExchangeConstants.HTTP_DUPLICATE_STATUS_CODE, exchangeValueDao);
		}
		
		return currencyExchangeResponse;				
	}
	
	@SuppressWarnings("null")
	@Override
	@HystrixCommand(fallbackMethod = "getConversionFactor_Fallback")
	public String getConversionFactor(CurrencyExchangeRequest request) {
		String currencyExchangeResponse = null;
		ExchangeValueDao exchangeValueDao = validateCurrencyExchangeRequest(request);
		if(exchangeValueDao != null) {
			currencyExchangeResponse = ResponseTransformer.transformCurrencyExchangeResponse(
															CurrencyExchangeConstants.HTTP_SUCCESS_STATUS_CODE,
															exchangeValueDao);
		} else {
//			currencyExchangeResponse = ResponseTransformer.transformCurrencyExchangeResponse(
//															CurrencyExchangeConstants.HTTP_NO_DATA_FOUND_STATUS_CODE,
//															exchangeValueDao);		
			exchangeValueDao.getConversionMultiple();
		}
		logger.info("{}", currencyExchangeResponse);
		return currencyExchangeResponse;
	}

	public String getConversionFactor_Fallback(CurrencyExchangeRequest request) {
		String currencyExchangeResponse = null;
		String from = request.getFrom();
		String to = request.getTo();
		String url = "https://api.exchangeratesapi.io/latest?base="+from+"&symbols="+to;
		logger.info("url ===>"+url);
		Map<String, String> responseType = new HashMap<String, String>();
		responseType.put("from", from);
		responseType.put("to", to);
		restTemplate = new RestTemplate();
		FallBackBean latestCurrencyObj = restTemplate.getForObject(url, FallBackBean.class);
		logger.info("latestCurrencyObj : {}"+latestCurrencyObj);
		currencyExchangeResponse = ResponseTransformer.transformToResponse(to,
											CurrencyExchangeConstants.HTTP_SUCCESS_STATUS_CODE,
											latestCurrencyObj);
		return currencyExchangeResponse;		
	}
	
	@Override
	public String updateConversionFactor(Long id, CurrencyExchangeRequest request) {
		String currencyExchangeResponse = null;
		Optional<ExchangeValueDao> exchangeValueDaoChk = repository.findById(id);
		if(exchangeValueDaoChk.isPresent()) {
			BigDecimal conversionFactor = request.getConversionFactor() != null ? request.getConversionFactor() : null;
			if (conversionFactor == null) {
				return ResponseTransformer.transformCurrencyExchangeResponse(
						CurrencyExchangeConstants.HTTP_BAD_REQ_STATUS_CODE, null);
			}
			ExchangeValueDao exchangeValueDao = validateCurrencyExchangeRequest(request);
			if(exchangeValueDao != null) {
				exchangeValueDao.setConversionMultiple(request.getConversionFactor());
				ExchangeValueDao updateConversionFactor = repository.save(exchangeValueDao);
				logger.info("Update Response => {}", updateConversionFactor);
				currencyExchangeResponse = ResponseTransformer.transformCurrencyExchangeResponse(
													CurrencyExchangeConstants.HTTP_SUCCESS_STATUS_CODE,
													updateConversionFactor);
			} else {
				currencyExchangeResponse = ResponseTransformer.transformCurrencyExchangeResponse(
						CurrencyExchangeConstants.HTTP_BAD_REQ_STATUS_CODE, exchangeValueDao);
			}
		} else {
			currencyExchangeResponse = ResponseTransformer.transformCurrencyExchangeResponse(
					CurrencyExchangeConstants.HTTP_NO_DATA_FOUND_STATUS_CODE, null);
		}
		return currencyExchangeResponse;
	}
	
	private ExchangeValueDao validateCurrencyExchangeRequest(CurrencyExchangeRequest request) {
		String fromCuntryCode  = request.getFrom() != null ? request.getFrom() : "";
		String toCuntryCode  = request.getTo() != null ? request.getTo() : "";
		BigDecimal conversionFactor = request.getConversionFactor() != null ? request.getConversionFactor() : null;
		ExchangeValueDao exchangeValue = null;
		if(CurrencyExchangeUtils.NullEliminator(fromCuntryCode) != "" &&
				CurrencyExchangeUtils.NullEliminator(toCuntryCode) != "") {
			exchangeValue = repository.findByFromAndTo(fromCuntryCode, toCuntryCode);
			logger.info("exchangeValue ==>"+exchangeValue);
			if(exchangeValue == null && conversionFactor != null)
				return new ExchangeValueDao(fromCuntryCode, toCuntryCode, conversionFactor);
			else
				return exchangeValue;
		}
		return exchangeValue;
	}
}
