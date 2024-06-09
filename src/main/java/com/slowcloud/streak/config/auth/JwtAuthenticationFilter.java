package com.slowcloud.streak.config.auth;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter implements Filter {

	private static final String JWT_HEADER = "jwt";

	private final JwtUtil jwtUtil;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		String token = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (
					cookie.getName()
							.equals("jwt")
				) {
					token = cookie.getValue();
					break;
				}
			}
		}

		if (StringUtils.hasText(token)) {
			Authentication authentication;
			try {
				authentication = jwtUtil.getAuthentication(token);
				
				// 원래는 아래 메소드를 실행해서 Authentication 등록을 해야 하지만,
				// 맞닥뜨린 문제가 너무 많아서 그냥 바로 시큐리티 컨텍스트에 등록하기로 함.
//				authenticationManager.authenticate(authentication);
				SecurityContextHolder.getContext().setAuthentication(authentication);

			} catch (ParseException e) {
				e.printStackTrace();
				log.error("JWT 토큰 파싱 중 문제 발생");
			}
		}

		chain.doFilter(request, response);

	}

}
