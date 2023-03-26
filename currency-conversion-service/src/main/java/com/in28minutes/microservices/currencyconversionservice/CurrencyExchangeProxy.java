package com.in28minutes.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



/*
 *Feign CLient annotation icindeki name diger MS adi ile eyni olmalidir
 *Yeni Proxy application adi currency-exchange olan MS'i cagirir
 *Lakin Url verilmesi isinizi statik edir.Eger CurrencyExchange MS 3-4 instance'i varsa 
 *yalniz 1 portda isleyen instance'a muraciet edecek.Bun problemi
 * Url'i qaldirib Eureka elave etmekle qaldiracayiq
 */
//@FeignClient(name = "currency-exchange",url="localhost:8000")
@FeignClient(name="currency-exchange")
public interface CurrencyExchangeProxy {

	
	
	/*Sorgu gedende localhost:8080 e altdaki url baglanir meselen 
	 * localhost:8080/currency-exchange/from/{from}/to/{to} seklinde olur.
	 * Ve sen bu methodu cairib degerleri methoda set edende hemin degerler url'e oturulur
	 * Elbetdeki bunlarin hamisini auto eden Spring Bootdur
	 * 
	 * 
	 * */
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(
			@PathVariable("from") String from,
			@PathVariable("to") String to);
	
	
}
