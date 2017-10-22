package com.MEDIADEMO.demo;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.message.MessageProperties;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.MEDIADEMO.demo.endpoint.UserEndPoint;

@Component
@Configuration
@ApplicationPath("/mediademo")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		// packages("com.MEDIADEMO.demo.endpoint");
		property(MessageProperties.XML_FORMAT_OUTPUT, true);
		register(MultiPartFeature.class);
		register(UserEndPoint.class);
	}
}
