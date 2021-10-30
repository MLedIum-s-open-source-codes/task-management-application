package com.example.taskmanagementapplication.config;

import com.example.taskmanagementapplication.security.CustomAccessDeniedHandler;
import com.example.taskmanagementapplication.security.CustomLogoutSuccessHandler;
import com.example.taskmanagementapplication.security.CustomUserDetailsService;
import com.example.taskmanagementapplication.security.RestAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    securedEnabled = true,
    prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtConfig jwtConfig;
  private final CustomUserDetailsService userDetailsService;
  private final CustomAccessDeniedHandler accessDeniedHandler;
  private final CustomLogoutSuccessHandler logoutSuccessHandler;
  private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

  @Bean("authenticationManager")
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors()
          .and()
        .headers().cacheControl().disable()
          .and()
        .headers().frameOptions().sameOrigin()
          .and()
        .formLogin().disable()
        .httpBasic().disable()
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(STATELESS)
          .and()
        .exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler)
        .authenticationEntryPoint(restAuthenticationEntryPoint)
          .and()
        .authorizeRequests()
        .antMatchers("/auth/**").permitAll()
        .anyRequest().authenticated()
          .and()
        .logout()
        .logoutSuccessHandler(logoutSuccessHandler)
        .invalidateHttpSession(false)
        .deleteCookies("JSESSIONID")
        .permitAll()
          .and()
        .apply(jwtConfig);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    super.configure(web);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {

    return new BCryptPasswordEncoder();
  }

}
