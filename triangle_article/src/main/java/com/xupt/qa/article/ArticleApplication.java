package com.xupt.qa.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author maxu
 */
@SpringBootApplication
@EnableCaching
public class ArticleApplication {
	public static void main(String[] args) {
		SpringApplication.run(ArticleApplication.class);
	}
}
