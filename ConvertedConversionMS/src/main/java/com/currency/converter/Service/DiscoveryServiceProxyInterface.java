package com.currency.converter.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="CURRENCYCONVERTERMS")
public interface DiscoveryServiceProxyInterface {
	
	@GetMapping(value = "api/getRate/{countryCode}")
	Double getRate(@PathVariable String countryCode);

}
