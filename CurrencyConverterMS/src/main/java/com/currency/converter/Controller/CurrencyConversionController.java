package com.currency.converter.Controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.currency.converter.Entites.CurrencyCoversionEntity;
import com.currency.converter.Service.CurrencyConversionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class CurrencyConversionController {

	@Autowired
	CurrencyConversionService currencyConversionService;

	@GetMapping(value = "/getRate/{countryCode}")
	Double getRate(@PathVariable String countryCode) {
		log.info("Inside Currency Converter MS---- Calling Get Rate Method");
		Double countryrate = currencyConversionService.getCountryRate(countryCode);
		return countryrate;
	}

	@PostMapping(path = "/addConversion", consumes = "application/json")
	ResponseEntity<HttpStatus> addConversion(@RequestBody CurrencyCoversionEntity request) {
		log.info("Inside Currency Converter MS---- Calling Add Method");
		boolean ifSuccesfull = Boolean.FALSE;
		if (!Objects.isNull(request) && !Objects.isNull(request.getCountryCode())
				&& !Objects.isNull(request.getConversionFactor())) {
			currencyConversionService.addConversion(request);
			ifSuccesfull = Boolean.TRUE;
		}
		if (ifSuccesfull)
			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@PutMapping(path = "/updateConversion", consumes = "application/json")
	ResponseEntity<HttpStatus> updateConversion(@RequestBody CurrencyCoversionEntity request) {
		log.info("Inside Currency Converter MS---- Calling Update Method");
		boolean ifSuccesfull = Boolean.FALSE;
		if (!Objects.isNull(request) && !Objects.isNull(request.getCountryCode())
				&& !Objects.isNull(request.getConversionFactor())) {
			currencyConversionService.updateConversion(request);
			ifSuccesfull = Boolean.TRUE;
		}
		if (ifSuccesfull)
			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
