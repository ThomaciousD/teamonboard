package com.tda.forge.team;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.camel.CamelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidateInput {

	private Logger logger = LoggerFactory.getLogger(ValidateInput.class);

	private static String validHostnameRegex = "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$";

	private static final Pattern pattern = Pattern.compile(validHostnameRegex);

	public String validate(String input) throws Exception {
		if (input != null && !input.isEmpty()) {
			Matcher matcher = pattern.matcher(input);
			if (!matcher.find()) {
				logger.info("Host name {} is an invalid DNS name.", input);
				throw new CamelException("Host name provided is an invalid DNS name");
			}
			logger.info("Host name {} is valid.", input);
			// copy the input into a new String and pass it to the next step
			return new String(input);
		} else {
			throw new CamelException("ValidateInput: String is null or empty");
		}
	}
}
