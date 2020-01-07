package com.demo.microservices.limitsservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "limits-service") /**if you are using ConfigurationProperties 
it's not sufficient to generate the getters only you need to create the setters as well*/
public class Configuration {
	
	private int minimum;
	private int maximum;
	 
	public int getMinimum() {
		return minimum;
	}
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

}
