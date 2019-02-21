package com.xupt.search;

import com.xupt.common.untils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author maxu
 */
@SpringBootApplication
public class SearchApplication {
	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class);
	}

	@Bean
	public IdWorker idWorker() {
		return new IdWorker();
	}
}
