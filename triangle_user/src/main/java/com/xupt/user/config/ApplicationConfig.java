package com.xupt.user.config;

import com.xupt.user.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author maxu
 */
@Configuration
public class ApplicationConfig extends WebMvcConfigurationSupport {
	@Autowired
	private JwtInterceptor jwtInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).
				addPathPatterns("/**").
				excludePathPatterns("/**/login");
	}

}
