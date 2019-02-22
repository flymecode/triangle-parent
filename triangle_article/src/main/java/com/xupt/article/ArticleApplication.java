package com.xupt.article;

import com.xupt.common.untils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * @author maxu
 */
@SpringBootApplication
@EnableCaching
@EnableDiscoveryClient
public class ArticleApplication {
	public static void main(String[] args) {
		SpringApplication.run(ArticleApplication.class);
	}

	@Bean
	public IdWorker idWorker() {
		return new IdWorker();
	}
}
