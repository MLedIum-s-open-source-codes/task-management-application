package com.example.taskmanagementapplication.config;

import com.example.taskmanagementapplication.config.properties.ApplicationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan(basePackageClasses = ApplicationProperties.class)
public class AppConfig {

}
