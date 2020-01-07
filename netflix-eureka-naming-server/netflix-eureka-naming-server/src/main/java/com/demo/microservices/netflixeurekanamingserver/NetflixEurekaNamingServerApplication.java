package com.demo.microservices.netflixeurekanamingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer /**
					 * setup a registry that other applications can talk to. This is a regular
					 * Spring Boot application with one annotation added to enable the service
					 * registry.
					 * Naming server is the one which offers registration of the services
					 *  i.e service registration and service discovery
					 */
public class NetflixEurekaNamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixEurekaNamingServerApplication.class, args);
	}

}
