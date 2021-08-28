package com.example.taskmanagementapplication.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

  public CustomLogoutSuccessHandler() {
  }

  @Override
  public void onLogoutSuccess(
      final HttpServletRequest request,
      final HttpServletResponse response,
      final Authentication authentication)
      throws IOException, ServletException {

    final String refererUrl = request.getHeader("Referer");
    log.debug("CustomLogoutSuccessHandler.onLogoutSuccess refererUrl : " + refererUrl);

    if (authentication != null) {
      log.debug("User logout: " + authentication.getName());
    }

    request.getSession(false);
    response.getOutputStream().print("Logout");
    response.setStatus(HttpStatus.OK.value());
    super.onLogoutSuccess(request, response, authentication);
  }

}
