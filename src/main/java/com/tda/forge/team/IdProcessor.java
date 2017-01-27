package com.tda.forge.team;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdProcessor implements Processor {
	private Logger logger = LoggerFactory.getLogger(ValidateInput.class);

	public void process(Exchange exchange) throws Exception {
		logger.info("Setting exchange-in header id {}", exchange.getIn().getBody());
		exchange.getIn().setHeader("id", exchange.getIn().getBody());
	}

}