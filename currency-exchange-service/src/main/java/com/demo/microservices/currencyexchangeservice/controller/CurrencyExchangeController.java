package com.demo.microservices.currencyexchangeservice.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.microservices.currencyexchangeservice.beans.ExchangeValue;
import com.demo.microservices.currencyexchangeservice.repository.ExchangeValueRepository;

@RestController
public class CurrencyExchangeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Environment enviroment;
	
	@Autowired
	private ExchangeValueRepository exchangeValueRepository;	

	@RequestMapping(value = "currency-exchange/from/{from}/to/{to}", method = RequestMethod.GET)
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

		/*
		 * return new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65), Integer
		 * .parseInt(enviroment.getProperty("local.server.port")));
		 */

		/**
		 * either set port value in the constructor as shown above or set it later as
		 * shown below Both are same.
		 */
		
		/*ExchangeValue exchangeValue = new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65));
		exchangeValue.setPort(Integer.parseInt(enviroment.getProperty("local.server.port")));*/
		
		
		//retrieving values from DB on the basis of from and to
		Optional<ExchangeValue> exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);
		ExchangeValue ex = exchangeValue.get();
		
		logger.info("{}", ex );
		
		
		ex.setPort(Integer.parseInt(enviroment.getProperty("local.server.port")));
		return ex;
	}

}
