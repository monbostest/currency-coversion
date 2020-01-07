package com.demo.microservices.limitsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient /**
						 * @EnableEurekaClient makes the app into both a Eureka "instance" (i.e. it
						 * registers itself) and a "client" (i.e. it can query the
						 * registry to locate other services)
						 * 
						 * Client registers itself with the registry and uses the
						 * Spring Cloud DiscoveryClient abstraction to interrogate
						 * the registry for it’s own host and port.
						 * The @EnableDiscoveryClient activates the Netflix Eureka DiscoveryClient implementation.
						 * 
						 */
public class LimitsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimitsServiceApplication.class, args);
	}

}
