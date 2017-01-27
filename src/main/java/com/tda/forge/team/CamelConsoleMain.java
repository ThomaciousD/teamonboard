package com.tda.forge.team;

import org.apache.camel.spring.Main;

public final class CamelConsoleMain {

	private CamelConsoleMain() {
	}

	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.setApplicationContextUri("spring/camel-context.xml");
		main.run();
	}
}
