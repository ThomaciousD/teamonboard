package com.tda.forge.team;

import java.util.Properties;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DockerConfigProcessor implements Processor {
	private Logger logger = LoggerFactory.getLogger(DockerConfigProcessor.class);

	private String dockerRegistryHost;

	private String dockerRegistryPort;

	private Properties properties;

	private static String JSON_CONFIG = new String(
			"{" + "\"auths\": {" + "\"%1$s\": {" + "\"auth\": \"%2$s\"," + "\"email\": \"%3$s\"" + "}" + "}" + "}");

	public void process(Exchange exchange) throws Exception {
		exchange.getIn()
				.setBody(String.format(JSON_CONFIG,
						dockerRegistryHost + ((dockerRegistryHost != null && !dockerRegistryPort.isEmpty())
								? ":" + dockerRegistryPort : ""),
						exchange.getIn().getBody(String.class),
						properties.getProperty("tech.user.email", "docker@forge.tech"), String.class));
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public void setDockerRegistryHost(String dockerRegistryHost) {
		this.dockerRegistryHost = dockerRegistryHost;
	}

	public void setDockerRegistryPort(String dockerRegistryPort) {
		this.dockerRegistryPort = dockerRegistryPort;
	}
	
	

}