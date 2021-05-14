package com.library.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class WsServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsServiceRegistryApplication.class, args);
	}

}
