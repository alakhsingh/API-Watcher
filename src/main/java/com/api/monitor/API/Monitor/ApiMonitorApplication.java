package com.api.monitor.API.Monitor;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@EnableEncryptableProperties
@SpringBootApplication
public class ApiMonitorApplication {
	Logger logger = LoggerFactory.getLogger(ApiMonitorApplication.class);

	@Value("${spring.datasource.driver-class-name}")
	private String dbDriverClassName;

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Value("${spring.datasource.username}")
	private String dbUsername;

	@Value("${EncryptedProperty}")
	private String myProperty;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dbDriverClassName);
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(myProperty);

		logger.info("dataSource url: " + dataSource.getUrl());
		return dataSource;
	}


	public static void main(String[] args) {
		SpringApplication.run(ApiMonitorApplication.class, args);
	}

}
