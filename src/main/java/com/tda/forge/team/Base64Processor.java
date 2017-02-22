package com.tda.forge.team;

import java.util.Base64;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Base64Processor implements Processor {
	private Logger logger = LoggerFactory.getLogger(Base64Processor.class);

	public void process(Exchange exchange) throws Exception {
		ProjectDAO project = exchange.getIn().getBody(ProjectDAO.class);
		exchange.setProperty("projectId", project.getId());
		exchange.getOut().setBody(new String(
				Base64.getEncoder().encode(new String(project.getId() + ":" + project.getPassword()).getBytes())),
				String.class);
	}

}