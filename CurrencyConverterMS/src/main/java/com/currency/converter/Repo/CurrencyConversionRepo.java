package com.currency.converter.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.currency.converter.Entites.CurrencyCoversionEntity;

public interface CurrencyConversionRepo  extends JpaRepository<CurrencyCoversionEntity, Double>{

	CurrencyCoversionEntity findByCountryCode(String countryCode);

}
