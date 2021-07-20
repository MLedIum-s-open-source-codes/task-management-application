package com.example.taskmanagementapplication.security.token;

import com.example.taskmanagementapplication.config.properties.JwtTokenProperties;
import com.example.taskmanagementapplication.enumeration.TokenSubjectEnum;
import com.example.taskmanagementapplication.security.CustomUserDetailsService;
import com.nimbusds.oauth2.sdk.token.AccessTokenType;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@Log4j2
@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

  private final JwtTokenProperties jwtTokenProperties;
  private final CustomUserDetailsService userDetailsService;
  private final Map<String, Date> tokenBlackList = new ConcurrentHashMap<>();

  @PostConstruct
  public void start() {
    ScheduledExecutorService scheduledExecutorService
        = Executors.newScheduledThreadPool(1, r -> new Thread("token-scheduler"));
    scheduledExecutorService.scheduleWithFixedDelay(
        this::cleanBlackList, 0, jwtTokenProperties.getTimeoutSec(), TimeUnit.SECONDS);
    log.info("Job token-scheduler run with delay {}", jwtTokenProperties.getTimeoutSec());
  }

  public String createToken(String username) {

    return createToken(TokenSubjectEnum.AUTH.name(), username);
  }

  public String createToken(String subject, String username) {
    long expirationTime = jwtTokenProperties.getExpiredTimeSecOtherToken();
    if (subject.equals(TokenSubjectEnum.AUTH.name())) {
      expirationTime = jwtTokenProperties.getExpiredTimeSecAuthToken();
    }
    String token = Jwts.builder()
        .setId(username)
        .setSubject(subject)
        .setExpiration(Date.from(Instant.now().plusSeconds(expirationTime)))
        .setIssuer(jwtTokenProperties.getIssuer())
        .setIssuedAt(Date.from(Instant.now()))
        .signWith(SignatureAlgorithm.HS256,
            jwtTokenProperties.getSecretKey().getBytes(StandardCharsets.UTF_8))
        .compact();
    log.info("Token {} '{}' created for user '{}'", subject, token, username);
    return token;
  }

  public UsernamePasswordAuthenticationToken getAuthentication(String token) {
    validateToken(TokenSubjectEnum.AUTH.name(), token);
    UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(
        userDetails, null, userDetails.getAuthorities());
  }

  public String getToken(HttpServletRequest request) {
    String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (header != null && header.startsWith(AccessTokenType.BEARER.getValue())) {
      return header.substring(AccessTokenType.BEARER.getValue().length() + 1);
    }
    log.info("JWT token is not found");
    return null;
  }

  public void validateToken(String subject, String token) {
    assertNotNull(token, "JWT token doesn't exist");
    Jwts.parser().setSigningKey(jwtTokenProperties.getSecretKey()
        .getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token);
    checkSubjectToken(subject, token);
    checkValidityPeriod(token);
    checkContainsInTokenBlackList(token);
  }

  private void checkSubjectToken(String subject, String token) {
    if (subject != null && !getSubject(token).equals(subject)) {
      log.info("JWT token {} is invalid", token);
      throw new JwtException("JWT token is invalid");
    }
  }

  private void checkContainsInTokenBlackList(String token) {
    if (tokenBlackList.containsKey(token)) {
      log.info("JWT token {} is invalid", token);
      throw new JwtException("JWT token is invalid");
    }
    log.info("JWT token {} is valid", token);
  }

  private void checkValidityPeriod(String token) {
    final Date expiration = getExpirationDate(token);
    if (expiration == null || expiration.before(new Date())) {
      log.info("JWT token {} expired", token);
      throw new JwtException("JWT token expired");
    }
  }

  public String getUsername(String token) {
    return Jwts.parser()
        .setSigningKey(jwtTokenProperties.getSecretKey().getBytes(StandardCharsets.UTF_8))
        .parseClaimsJws(token).getBody().getId();
  }

  public String getSubject(String token) {
    return Jwts.parser()
        .setSigningKey(jwtTokenProperties.getSecretKey().getBytes(StandardCharsets.UTF_8))
        .parseClaimsJws(token).getBody().getSubject();
  }

  public Date getExpirationDate(String token) {
    return Jwts.parser()
        .setSigningKey(jwtTokenProperties.getSecretKey().getBytes(StandardCharsets.UTF_8))
        .parseClaimsJws(token).getBody().getExpiration();
  }

  public void addToBlackList(HttpServletRequest request) {
    String token = getToken(request);
    validateToken(null, token);
    Date expirationDate = Jwts.parser().setSigningKey(jwtTokenProperties.getSecretKey()
        .getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody().getExpiration();
    tokenBlackList.put(token, expirationDate);
    log.info("User's {} token {} added to back list", getUsername(token), token);
    MDC.clear();
  }

  private void cleanBlackList() {
    Date currentDate = new Date();
    List<String> keys = tokenBlackList.entrySet().stream()
        .filter(e -> e.getValue().before(currentDate))
        .map(Map.Entry::getKey).collect(Collectors.toList());
    keys.forEach(tokenBlackList::remove);
    log.info("Tokens {} removed from back list", keys);
  }

}
