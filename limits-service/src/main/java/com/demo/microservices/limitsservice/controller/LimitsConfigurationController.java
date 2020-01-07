package com.demo.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.microservices.limitsservice.Configuration;

import com.demo.microservices.limitsservice.bean.LimitConfiguration;

@RestController
public class LimitsConfigurationController {
	
	@Autowired
	Configuration configuration;

	@RequestMapping(value = "/limits", method = RequestMethod.GET)
	public LimitConfiguration retrieveLimitsFromConfiguration() {

		return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum()); 

	}

}
