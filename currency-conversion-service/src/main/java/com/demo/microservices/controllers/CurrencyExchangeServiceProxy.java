package com.demo.microservices.controllers;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.microservices.currencyconversionservice.beans.CurrencyConversionBean;



/*@FeignClient(name="currency-exchange-service", url="http://localhost:8000")*/ /**This is feign client.
this is going to use Feign to talk to external microservice. We are providing name of the service which we are going
to call i.e. currency-exchange-service. We will also give the URL i.e the  port where this service is deployed*/

/*@FeignClient(name="currency-exchange-service")*/ /*when using Ribbon we don't need to specify url
 *  to feign client)*/

@FeignClient(name="netflix-zuul-api-gateway-server") /**changed the name of the service as we do not want
 feign to directly talk to currency-exchange-service but connect to zuul-api-gateway-server first. It
 talks to the naming server and get the URI for the zuul server. */
/**So whenever we are calling the currency-conversion-service through feign what's happening is that it is being
routed through the API gateway, the service call is not going directly to the currency-exchange-service.
It is being routed through the gateway (gateway is executing the filter which is the logging filter)
and invoking the currency-exchange-service.
What we're doing now is intercepting the call between the currency-conversion-service and the 
currency-exchange-service.*/
@RibbonClient(name="currency-exchange-service") /*Ribbon is a client-side load balancer. It distributes
the load between the different instances of a service*/
public interface CurrencyExchangeServiceProxy {
	
	//@RequestMapping(value = "currency-exchange/from/{from}/to/{to}", method = RequestMethod.GET)
	@RequestMapping(value = "currency-exchange-service/currency-exchange/from/{from}/to/{to}", method = RequestMethod.GET)
	/**when we are using zuul-api-gateway-server then we will use above mapping*/
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable ("from") String from, @PathVariable ("to") String to);

}


/*What we are doing in here is we're trying to make every call to the micro-services go through the Zuul
API Gateway.
The URL for invoking a request through API gateway is:
http://localhost:8765/{application-name}/{uri of the service}. What API Gateway does is that it will log the
request because we have implemented a logging filter and it would send the request out to the microservice.*/ 


