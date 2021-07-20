package com.example.taskmanagementapplication.config.properties;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class JwtTokenProperties {

  String issuer = "Wwwwww";

  String secretKey = "123456789";

  long expiredTimeSecAuthToken = 604800;
  long expiredTimeSecOtherToken = 86400;

  long timeoutSec = 86400;

}
