package com.example.taskmanagementapplication.config.properties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
//@ConfigurationProperties("jwt.token")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtTokenProperties {

  //@Value("jwt.token.issuer")
  String issuer = "Wwwwww";

  //@Value("jwt.token.secretKey")
  String secretKey = "123456789";

  //@Value("jwt.token.expiredTimeSecAuthToken")
  long expiredTimeSecAuthToken = 604800L;
  //@Value("jwt.token.expiredTimeSecOtherToken")
  long expiredTimeSecOtherToken = 86400L;

  //@Value("jwt.token.timeoutSec")
  long timeoutSec = 86400L;

}
