package com.in28minutes.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class CircuitBreakerController {

	
	private Logger logger = 
			LoggerFactory.getLogger(CircuitBreakerController.class);
	
	
	
	
	@GetMapping("/sample-api")
//	@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")//method xeta verende 3 defe yeniden sorgu gonderir sampleApi methoduna.3 defe xeta verenden sponra geriye xeta qaytarir.fallbackMethod ile ise xeta bas verende qaytarilamli olan deyer qaytarilir
	@CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
//	@RateLimiter(name="default")//Yeni biz annotation ile deyirik ki misal 10 saniyede 10000 sorguya icaze verirem
//	@Bulkhead(name="sample-api")
	public String sampleApi() {
		logger.info("Sample api call received");

		
//		Meselen basqa MS'e muraciet edirem ve hemin ms islemir ve ya xeta verir onda @Retry vasitesile yeniden
//		sorgu gonderilir
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", 
				String.class);
	
		
		return forEntity.getBody().toString();
//		return "Sample API";
	}
	
	
	public String hardcodedResponse(Exception ex) {
		return "fallback-response";
	}
	
	
	
}
