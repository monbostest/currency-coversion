package com.demo.microservices.currencyconversionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import brave.sampler.Sampler;


/*currency-conversion-service is consumer of currency-exchange-service*/

@SpringBootApplication
@EnableFeignClients(basePackages = { "com.demo.microservices.currencyconversionservice",
		"com.demo.microservices.controllers" })
@EnableDiscoveryClient
@ComponentScan(basePackages = { "com.demo.microservices.controllers" })
public class CurrencyConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	} 
	
/**IF we want to trace all the request via Spring Cloud Sleuth then we need to create something called 
AlwaysSampler*/
	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

}
