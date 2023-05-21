package com.in28minutes.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/get")
						.filters(f -> f.addRequestHeader("MyHeader", "MyURI").addRequestParameter("Param", "MyValue"))
						.uri("http://httpbin.org:80"))
				
				.route(p -> p.path("/currency-exchange/**").uri("lb://currency-exchange"))
				.route(p -> p.path("/currency-conversion/**").uri("lb://currency-conversion"))
				.route(p -> p.path("/currency-conversion-feign/**").uri("lb://currency-conversion")).route(
						p -> p.path("/currency-conversion-new/**")
								.filters(f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)",
										"/currency-conversion-feign/${segment}"))
								.uri("lb://currency-conversion"))
				.build();
		
//		uri icindeki ad eurekada registr olan ms adidir
		
//		lb ->load balancer demekdir...

		/*
		 * path senin isetiyin urldir. URI ise redirect olunacaq urldir.
		 * http://localhost:8765/currency-exchange/currency-exchange/from/USD/to/INR bu
		 * sekilde olan url'i http://localhost:8765/currency-exchange/from/USD/to/INR bu
		 * formaya getirir.Sanki currency-exchange/from/USD/to/INR url ile gelen sogunun
		 * qarsisina API GATEWAY elaveden currency-exchange elave edir ve Naming servere
		 * yonlendirir. 21 22 cu setrdeki kodlar ise basqa ad altinda url yaratmaq ve
		 * onu replace etmekdir. yeni 21ci setrdeki kod 22a convert olunur sonra basina
		 * currency-conversion elave edirlir... Belelikle url
		 * http://localhost:8765/currency-conversion/currency-conversion-feign/from/USD/
		 * to/INR/quantity/10 seklinde olur
		 */

	}

}
