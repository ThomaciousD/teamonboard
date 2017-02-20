package com.tda.forge.team;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class EmptyMessageProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		// empty the body
		// exchange.getIn().setBody(new String(), String.class);
		// send http response
		exchange.getOut().setBody("<html><body>Project under creation</body></html>");
	}
}