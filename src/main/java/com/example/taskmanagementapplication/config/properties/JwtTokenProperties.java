package com.example.taskmanagementapplication.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("jwt.token")
public class JwtTokenProperties {

  private String issuer;
  private String secretKey;

  private long expiredTimeSecAuthToken;
  private long expiredTimeSecOtherToken;
  private long timeoutSec;

}
