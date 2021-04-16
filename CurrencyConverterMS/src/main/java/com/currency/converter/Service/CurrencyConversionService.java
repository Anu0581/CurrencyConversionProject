package com.currency.converter.Service;

import com.currency.converter.Entites.CurrencyCoversionEntity;

public interface CurrencyConversionService {

	Double getCountryRate(String countryCode);

	void addConversion(CurrencyCoversionEntity request);

	void updateConversion(CurrencyCoversionEntity request);


}
