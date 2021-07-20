package com.example.taskmanagementapplication.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(
      HttpServletRequest request,
      HttpServletResponse response,
      AccessDeniedException e)
      throws IOException, ServletException {

    response.setStatus(403);

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      log.warn("User '" + authentication.getName() + "' attempted to access the protected URL: "
          + request.getScheme() + "://" + request.getRemoteHost() + ":" + request.getServerPort() + request.getRequestURI());
    }

    response.getWriter().flush();
  }

}
