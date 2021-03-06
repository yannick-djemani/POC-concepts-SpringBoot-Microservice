package com.codeimmig.yannick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringCloudDcEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudDcEurekaServerApplication.class, args);
	}

}
