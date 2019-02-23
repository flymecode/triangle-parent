package com.xupt.friend.interceptor;

import com.xupt.common.untils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author maxu
 */
@Slf4j
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("经过拦截器");
		final String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			final String token = authHeader.substring(7); // The part after "Bearer "
			try {
				Claims claims = jwtUtil.parseJWT(token);
				if (claims != null) {
					if ("admin".equals(claims.get("roles"))) {//如果是管理员
						request.setAttribute("admin_claims", claims);
					}
					if ("user".equals(claims.get("roles"))) {//如果是用户
						request.setAttribute("user_claims", claims);
					}
				}
			} catch (Exception e) {
				log.info("Jwt解析失败{}",e.getMessage());
			}
		}
		return true;
	}

}
