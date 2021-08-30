package com.example.taskmanagementapplication.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class JwtTokenProperties {

  @Value("jwt.token.issuer")
  String issuer = "Wwwwww";

  @Value("jwt.token.secretKey")
  String secretKey = "123456789";

  @Value("jwt.token.expiredTimeSecAuthToken")
  long expiredTimeSecAuthToken = 604800;
  @Value("jwt.token.expiredTimeSecOtherToken")
  long expiredTimeSecOtherToken = 86400;

  @Value("jwt.token.timeoutSec")
  long timeoutSec = 86400;

}
