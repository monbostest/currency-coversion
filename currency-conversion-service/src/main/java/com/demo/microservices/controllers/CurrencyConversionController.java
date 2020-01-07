package com.demo.microservices.controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.microservices.currencyconversionservice.beans.CurrencyConversionBean;

@RestController
public class CurrencyConversionController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CurrencyExchangeServiceProxy proxy;

	@RequestMapping(value = "currency-converter/from/{from}/to/{to}/quantity/{quantity}", method = RequestMethod.GET)
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		Map<String, String> uriVariables = new HashMap<String, String>();

		uriVariables.put("from", from);
		uriVariables.put("to", to);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<CurrencyConversionBean> responseEntity = restTemplate.getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
				uriVariables);

		CurrencyConversionBean response = responseEntity.getBody();
		
		logger.info("{}", response);

		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());
	}

	/**
	 * Feign is a Rest Service client. Feign helps in invoking other microservices
	 * which we are otherwise doing using RestTemplate. Feign is one of the
	 * components that Spring cloud inherits from Netflix. What Is a Feign Client?
	 * Netflix provides Feign as an abstraction over REST-based calls, by which
	 * microservices can communicate with each other, but developers don't have to
	 * bother about REST internal details..
	 */

	@RequestMapping(value = "currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);
		

		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());
	}

	/**
	 * Ribbon provides client side load balancing facility. In this case it enables 
	 * currency-conversion-service to distribute the load between multiple instances of 
	 * currency-exchange-service
	 *  
	 * When using Ribbon with Eureka naming server, Ribbon asks the naming
	 * server(eureka) what are the instances of the currency-exchange-service that
	 * are running? And it gets a list back and then it would invoke the appropriate
	 * currency-exchange-service
	 */
}
