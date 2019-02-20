package com.xupt.qa.recruit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author maxu
 */
@EnableCaching
@SpringBootApplication
public class RecruitApplication {
	public static void main(String[] args) {
		SpringApplication.run(RecruitApplication.class);
	}
}
