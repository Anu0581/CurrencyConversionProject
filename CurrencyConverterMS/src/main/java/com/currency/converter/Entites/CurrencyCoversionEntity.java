package com.currency.converter.Entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="ManageCurrency")
public class CurrencyCoversionEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String countryCode;
	
	private Double conversionFactor;


}
