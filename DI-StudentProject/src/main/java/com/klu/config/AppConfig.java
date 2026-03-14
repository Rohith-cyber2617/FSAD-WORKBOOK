package com.klu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = "com.klu.model")
@ImportResource("classpath:student.xml")   // IMPORT XML INTO JAVA CONFIG
public class AppConfig {
}

