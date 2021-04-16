package com.currency.converter.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.currency.converter.Service.CurrencyConverterService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class ConvertedCurrencyController {

	@Autowired
	CurrencyConverterService curConvService;

	@CircuitBreaker(name = "convertretry", fallbackMethod = "calculateDiscountFallback")
	@GetMapping(path = "/getConversion/{countryCode}/{amtToBeCnvrtd}")
	ResponseEntity<Double> getConversion(@PathVariable String countryCode, @PathVariable Double amtToBeCnvrtd) {
		log.info("Inside Converted Conversion MS---- Calling Conversion Method");
		Double convertedAmount = curConvService.getConvertedAmt(countryCode, amtToBeCnvrtd);
		return new ResponseEntity<Double>(convertedAmount, HttpStatus.FOUND);

	}

	public ResponseEntity<Double> calculateDiscountFallback(String countryCode, Double amtToBeCnvrtd, Throwable t) {
		log.info("Inside Converted Conversion MS---- Calling FallBack Method");
		Double defaultConvFactor = 10.0;
		Double convertedAmount = Double.sum(defaultConvFactor, amtToBeCnvrtd);
		if (countryCode != null)
			return new ResponseEntity<Double>(convertedAmount, HttpStatus.FOUND);
		else
			return new ResponseEntity<Double>(HttpStatus.NOT_FOUND);

	}

}
