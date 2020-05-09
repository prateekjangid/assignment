package com.amdocs.media.assignement.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Profile("local")
public class CreateTopicConfig {

	
	@Bean 
	public NewTopic profileupdate() {
	return	TopicBuilder.name("profileupdate").
		partitions(1)
		.replicas(1)
		.build();
		
		
	}
	
}
