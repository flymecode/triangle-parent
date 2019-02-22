package com.xupt.base;

import com.xupt.common.untils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * @author maxu
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class);
	}

	@Bean
	public IdWorker idWorker() {
		return new IdWorker(1, 1);
	}

}
