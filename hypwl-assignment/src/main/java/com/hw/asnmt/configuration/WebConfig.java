package com.hw.asnmt.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * This is the spring annotation driven configuration class. Contains
 * configuration to run this project as spring web based application.
 * 
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.hw.asnmt")
public class WebConfig {

}
