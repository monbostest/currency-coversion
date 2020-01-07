package com.demo.microservices.netflixzuulapigatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class NetflixZuulApiGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixZuulApiGatewayServerApplication.class, args);
	}			
	
	
/**IF we want to trace all the request via Spring Cloud Sleuth then we need to create something called 
AlwaysSampler*/
	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
	

}
