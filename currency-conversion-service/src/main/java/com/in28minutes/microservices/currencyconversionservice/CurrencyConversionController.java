package com.in28minutes.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			) {
		
		
		
//		Rest templetde URL'e variables set edir mesele form{from} dursa
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from",from);
		uriVariables.put("to",to);
		
		
//		Bu url ile from ve to gonderilir ve geriye bir JSON qaytarir ve biz onu CurrencyConversion obyektine ceviririk
//	GetForEntity get sorgusu gondermeye lazim olur.
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity
		("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				CurrencyConversion.class, uriVariables);
//		CurrencyConversion classinin deyisenleri gelen data fieldleri ile eynidi deye avtomatik eynilesdirme gedir
		
		System.out.println(responseEntity+" ++++++++++++++++++++++++++");
		
		
		
//		Gelen responsun header ve bodysi olur.Ve biz datani body ile gotururuk
		CurrencyConversion currencyConversion = responseEntity.getBody();

		
				
		return new CurrencyConversion(currencyConversion.getId(), 
				from, to, quantity, 
				currencyConversion.getConversionMultiple(), 
				quantity.multiply(currencyConversion.getConversionMultiple()), 
				currencyConversion.getEnvironment()+ " " + "rest template");
		
		
	}

}