package com.example.taskmanagementapplication.security.token;

import com.example.taskmanagementapplication.enumeration.TokenSubjectEnum;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain)
      throws ServletException, IOException {
    try {
      doTokenFilter(request);
    } catch (JwtException e) {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
    chain.doFilter(request, response);
  }

  private void doTokenFilter(HttpServletRequest request) {
    String token = jwtTokenProvider.getToken(request);
    if (token != null) {
      //jwtTokenProvider.validateToken(TokenSubjectEnum.AUTH.name(), token);
      UsernamePasswordAuthenticationToken authentication
          = jwtTokenProvider.getAuthentication(token);
      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      log.debug("User '{}' authenticated by token {}", jwtTokenProvider.getUsername(token), token);
      MDC.put("user", jwtTokenProvider.getUsername(token));
    }
  }

}
