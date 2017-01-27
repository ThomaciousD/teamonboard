package com.tda.forge.team;

import java.util.HashMap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * curl -k -vvvv -X POST -u admin:admin123 --header "Content-Type: text/plain"
 * "http://localhost:8081/service/siesta/rest/v1/script/user/run" -d '{ "id":
 * "userTest5", "firstName" :"John", "lastName":"Doe", "password":"PWD" }'
 */
@Component(value = "NexusPrepareRequestTransformer")
public class NexusPrepareRequestTransformer implements Processor {

	private Logger logger = LoggerFactory.getLogger(NexusPrepareRequestTransformer.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		String jsonBody = exchange.getIn().getBody(String.class);
		logger.info("Preparing Nexus account provisioning for payload {}", jsonBody);
		// clear headers
		exchange.getIn().setHeaders(new HashMap<>());
		// mapHttpMessageHeaders is set to true by default, Camel 2.18: If this
		// option is true then IN exchange Headers will be mapped to HTTP
		// headers. Setting this to false will avoid the HTTP Headers mapping.
		// https://camel.apache.org/http4.html
		exchange.getIn().setHeader("Content-Type", "text/plain");
		// body is alredy set
	}

}
