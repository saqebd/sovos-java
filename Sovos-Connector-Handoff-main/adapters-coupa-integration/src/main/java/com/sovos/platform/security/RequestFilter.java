/**
 * Copyright (c) 2021 by Sovos Compliance
 */
package com.sovos.platform.security;

import static org.apache.commons.text.StringEscapeUtils.escapeJava;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.sovos.coupa.domain.CoupaAccount;
import com.sovos.coupa.service.AccountCacheService;
import com.sovos.coupa.utility.Constants;
import com.sovos.coupa.utility.CoupaHandlerInterceptor;
import com.sovos.coupa.utility.CoupaRequestContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

public class RequestFilter extends OncePerRequestFilter {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  @Autowired
  private AccountCacheService accountCache;
  @Autowired
  private JwtToken jwtToken;
  @Value("${jwt.header}")
  private String tokenHeader;
  @Autowired
  CoupaRequestContext coupaRequestContext;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws ServletException, IOException {

    final String authToken = request.getHeader(this.tokenHeader);

    String correlationId = request.getHeader(CoupaHandlerInterceptor.X_CORRELATION_ID);
    coupaRequestContext.setCorrelationId(correlationId);

    if (LOGGER.isDebugEnabled()) {
      String message = String.format("Attempted Login, token: '%s'", authToken);
      LOGGER.debug(String.format(Constants.GENERAL_LOG_FORMAT, escapeJava(correlationId), escapeJava(message)));
    }

    if (SecurityContextHolder.getContext().getAuthentication() == null) {
      final String token =
          (authToken != null && authToken.startsWith("Bearer ")) ? authToken.substring(7) : "";
      if (jwtToken.validateToken(token)) {
        DecodedJWT decodeToken = jwtToken.decodeToken(token);
        String org = decodeToken.getClaim("org").asString();
        coupaRequestContext.addMetaData("token", authToken);
        coupaRequestContext.addMetaData("org", org);
        coupaRequestContext.addMetaData("sub", decodeToken.getClaim("sub").asString());
        CoupaAccount account = accountCache.getAccount(correlationId, org);
        UserDetails userDetails = jwtToken.getUserDetails(correlationId, token, account);
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
        authentication.setDetails(userDetails);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (LOGGER.isDebugEnabled()) {
          String message = String.format("authenticated user: '%s', setting security context",
              userDetails.getUsername());
          LOGGER.debug(String.format(Constants.GENERAL_LOG_FORMAT, escapeJava(correlationId), escapeJava(message)));
        }
      }
    }
    chain.doFilter(request, response);
  }

}
