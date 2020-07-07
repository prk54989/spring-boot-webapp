package com.codebase.development.springinlinedb.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

@Configuration
public class DatasourceConnectionConfig2 {
	
	@Autowired
	private  Environment env;
	
	@Primary
	@Bean(name = "ssdwDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.ssdw")
	public DataSource ssdwDataSource() {
	    return DataSourceBuilder
	            .create()
	            .url(env.getProperty("db-batch.datasource.url"))
	            .driverClassName(env.getProperty("db-batch.datasource.driverClassName"))
	            .username(env.getProperty("db-batch.datasource.username"))
	            .password(env.getProperty("db-batch.datasource.password"))
	            .build();
	}
	
	
	@Bean(name = "abcDataSource")
	@ConfigurationProperties(prefix = "db-abc.datasource")
	public DataSource abcDataSource() {
	    return DataSourceBuilder
	            .create()
	            .url(env.getProperty("db-abc.datasource.url"))
	            .driverClassName(env.getProperty("db-abc.datasource.driverClassName"))
	            .username(env.getProperty("db-abc.datasource.username"))
	            .password(env.getProperty("db-abc.datasource.password"))
	            .build();
	}

	@Bean(name = "xyzDataSource")
	@ConfigurationProperties(prefix = "db-xyz.datasource")
	public DataSource xyzDataSource() {
	    return DataSourceBuilder
	            .create()
	            .url(env.getProperty("db-xyz.datasource.url"))
	            .driverClassName(env.getProperty("db-xyz.datasource.driverClassName"))
	            .username(env.getProperty("db-xyz.datasource.username"))
	            .password(env.getProperty("db-xyz.datasource.password"))
	            .build();
	}



	@Bean
	@Primary
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource schema1() {
	    return DataSourceBuilder.create().build();
	}

	@Bean
	@ConfigurationProperties(prefix="spring.datasource2")
	public DataSource schema2() {
	    return DataSourceBuilder.create().build();
	}

}
