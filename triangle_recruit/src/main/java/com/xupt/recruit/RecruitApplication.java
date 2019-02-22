package com.xupt.recruit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author maxu
 */
@EnableCaching
@SpringBootApplication
@EnableDiscoveryClient
public class RecruitApplication {
	public static void main(String[] args) {
		SpringApplication.run(RecruitApplication.class);
	}
}
