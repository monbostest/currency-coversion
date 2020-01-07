package com.demo.microservices.currencyexchangeservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.microservices.currencyexchangeservice.beans.ExchangeValue;

@Repository
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
	
	public Optional<ExchangeValue> findByFromAndTo(String from, String to); /**using Optional class is not necessary we could have 
	simply returned ExchangeValue type object*/
	
}
