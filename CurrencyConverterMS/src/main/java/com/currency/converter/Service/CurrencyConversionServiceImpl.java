package com.currency.converter.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.currency.converter.Entites.CurrencyCoversionEntity;
import com.currency.converter.Repo.CurrencyConversionRepo;

@Service
public class CurrencyConversionServiceImpl implements CurrencyConversionService {

	@Autowired
	CurrencyConversionRepo currencyConversionRepo;

	@Override
	public Double getCountryRate(String countryCode) {
		List<CurrencyCoversionEntity> entity = new ArrayList<CurrencyCoversionEntity>();
		entity = currencyConversionRepo.findAll();
		entity.stream().filter(code -> code.equals(countryCode)).collect(Collectors.toList());
		return entity.get(0).getConversionFactor();
	}

	@Override
	public void addConversion(CurrencyCoversionEntity request) {
		CurrencyCoversionEntity response = currencyConversionRepo.findByCountryCode(request.getCountryCode());
		if (Objects.isNull(response) || Objects.isNull(response.getCountryCode()))
			currencyConversionRepo.save(request);

	}

	@Override
	public void updateConversion(CurrencyCoversionEntity request) {
		CurrencyCoversionEntity response = currencyConversionRepo.findByCountryCode(request.getCountryCode());
		if (!Objects.isNull(response) && !Objects.isNull(response.getCountryCode())) {
			response.setConversionFactor(request.getConversionFactor());
			currencyConversionRepo.save(response);
		} else {
			currencyConversionRepo.save(request);
		}

	}

}
