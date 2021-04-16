package com.currency.converter.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConverterServiceImpl implements CurrencyConverterService{
	
	@Autowired
	DiscoveryServiceProxyInterface proxyInterface;

	@Override
	public Double getConvertedAmt(String countryCode, Double amtToBeCnvrtd) {
		Double conversionRate = proxyInterface.getRate(countryCode);
		Double convertedAmount = Double.sum(conversionRate, amtToBeCnvrtd);
		return convertedAmount;
	}
	

}
