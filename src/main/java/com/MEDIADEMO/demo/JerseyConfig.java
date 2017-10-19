package com.MEDIADEMO.demo;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
@Component
@Configuration
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		packages("com.MEDIADEMO.demo");
	}
}
