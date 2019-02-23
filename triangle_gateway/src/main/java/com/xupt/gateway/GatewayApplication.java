package com.xupt.gateway;

import com.xupt.common.untils.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @author maxu
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class GatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class);
	}

	@Bean
	public JwtUtil jwtUtil() {
		return new JwtUtil();
	}
}
