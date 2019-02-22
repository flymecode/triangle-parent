package com.xupt.user;

import com.xupt.common.untils.IdWorker;
import com.xupt.common.untils.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author maxu
 */
@SpringBootApplication
public class UserApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class);
	}

	@Bean
	public JwtUtil jwtUtil() {
		return new JwtUtil();
	}

	@Bean
	public IdWorker idWorker() {
		return new IdWorker();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
