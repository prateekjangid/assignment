package com.amdocs.media.assignement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.amdocs.media.assignement.config.AuthenticationPreFilter;

@EnableZuulProxy
@SpringBootApplication
@EntityScan("com.amdocs.media.assignement.dao")
public class AssignmentApplication {

		public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}
	
	@Bean
	public AuthenticationPreFilter getAuthenticationPreFilter() {
		return new AuthenticationPreFilter();
	}
}
