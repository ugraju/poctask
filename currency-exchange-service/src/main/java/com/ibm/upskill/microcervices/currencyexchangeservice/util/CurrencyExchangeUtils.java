/**
 * 
 */
package com.ibm.upskill.microcervices.currencyexchangeservice.util;

import java.util.List;

import com.ibm.upskill.microcervices.currencyexchangeservice.dao.ExchangeValueDao;

/**
 * @author GangaRajuUdimudi
 *
 */
public class CurrencyExchangeUtils {

	public static String NullEliminator(String str) {
		return null == str ? CurrencyExchangeConstants.EMPTY : str.trim().toUpperCase();
	}

	public static boolean isEmpty(List<ExchangeValueDao> exchangeValue) {
		boolean exchangeValueFlag = false;
		if(exchangeValue != null && exchangeValue.isEmpty())
			exchangeValueFlag = true;
		return exchangeValueFlag;
	}
	
	
}
