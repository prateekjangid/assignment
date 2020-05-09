package com.amdocs.Assignment;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProfileApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProfileApplication.class, args);
	
		
	}
	
	
	@Value("${h2.port}")
	private String h2port;
	
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server inMemoryH2DatabaseaServer() throws SQLException {
	    return Server.createTcpServer(
	      "-tcp", "-tcpAllowOthers", "-tcpPort", h2port);
	}
	

}
