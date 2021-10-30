package com.example.taskmanagementapplication.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("server")
public class ApplicationProperties {

  private int port;

}
