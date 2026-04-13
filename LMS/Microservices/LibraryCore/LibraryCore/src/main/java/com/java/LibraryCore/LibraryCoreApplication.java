package com.java.LibraryCore;

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class LibraryCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryCoreApplication.class, args);
	}
	
	 @Bean
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }

}
