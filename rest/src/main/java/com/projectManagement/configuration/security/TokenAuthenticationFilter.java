package com.projectManagement.configuration.security;

import io.jsonwebtoken.JwtException;

import com.projectManagement.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

	private final TokenComponent tokenComponent;
	private final UserDetailsService userDetailsService;

	TokenAuthenticationFilter(TokenComponent tokenComponent, UserDetailsService userDetailsService) {

		this.tokenComponent = tokenComponent;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

		String token = tokenComponent.getToken(httpServletRequest);

		if (StringUtils.nonNullNonEmpty(token)) {

			try {

				String username = tokenComponent.getUsername(token);
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				TokenAuthentication tokenAuthentication = new TokenAuthentication(userDetails, token);
				SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);

				if (tokenComponent.isExpirationNear(token)) {

					String newToken = tokenComponent.generateToken(username);

					tokenComponent.applyToken(newToken, httpServletResponse);
				} else {
					tokenComponent.applyToken(token, httpServletResponse);
				}

			} catch (JwtException e) {

				LOGGER.debug("Invalid token", e);
			}

		}

		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

}
