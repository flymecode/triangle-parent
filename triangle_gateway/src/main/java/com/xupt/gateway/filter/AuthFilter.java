package com.xupt.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.xupt.common.untils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author maxu
 */
@Slf4j
@Component
public class AuthFilter extends ZuulFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public String filterType() {
		return PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		log.info("后台过滤器");
		RequestContext requestContext = RequestContext.getCurrentContext();

		HttpServletRequest request = requestContext.getRequest();
		if (request.getMethod().equals("OPTIONS")) {
			return null;
		}
		String url = request.getRequestURL().toString();
		if (url.indexOf("/admin/login") > 0) {
			System.out.println("登陆页面" + url);
			return null;
		}

		String authorization = request.getHeader("Authorization");
		if (StringUtils.isEmpty(authorization)) {
			if (authorization.startsWith("Bearer ")) {
				String token = authorization.substring(7);
				try {
					Claims claims = jwtUtil.parseJWT(token);
					String roles = (String) claims.get("roles");
					if (roles.equals("admin")) {
						requestContext.addZuulRequestHeader("Authorization", authorization);
						return null;
					}
				} catch (Exception e) {
					requestContext.setSendZuulResponse(false);
					requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
					requestContext.setResponseBody("权限不足");
					requestContext.getResponse().setContentType("text/html;charset=UTF-8");
				}
			}
		}
		return null;
	}
}
