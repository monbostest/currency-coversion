package com.demo.microservices.currencyexchangeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableDiscoveryClient /**
						 * @EnableEurekaClient makes the app into both a Eureka "instance" (i.e. it
						 *  registers itself) and a "client" (i.e. it can query the
						 *  registry to locate other services) 
						 *  
						 *  Client registers itself with the registry and uses the Spring Cloud
						 *  DiscoveryClient abstraction to interrogate the registry
						 *  for it’s own host and port. The @EnableDiscoveryClient
						 *  activates the Netflix Eureka DiscoveryClient implementation.
						 *                     
						 */
public class CurrencyExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
	}
	
/**IF you want to trace all the request via Spring Cloud Sleuth then we need to create something called 
AlwaysSampler*/
	
public Sampler defaultSampler() {
	return Sampler.ALWAYS_SAMPLE;
}

}

/**
 * To run Currency Exchange service on 2 ports simultaneously follow below
 * steps: 1. Go to "Run Configuration" 2. Under Java Application select your
 * application's name 3. Change the name of this instance of the application 4.
 * Create a duplicate instance and give it a different name 5. Click on
 * Arguments Tab 6. Inside VM Arguments area set the port of the server as
 * '-Dserver.port=8001'. This will override the application.properties
 * configuration 7. Apply and Run 8. Now this application will have 2 instances
 * running on ports 8000 and 8001
 */
