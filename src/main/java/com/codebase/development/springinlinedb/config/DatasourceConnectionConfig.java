package com.codebase.development.springinlinedb.config;



import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DatasourceConnectionConfig {
	
	@Autowired
	private DataSourcePropertiesReader dataSourcePropertiesReader;
	
	@Primary
	@Bean(name = "Properties")
	@Qualifier(value = "Properties")
	@ConfigurationProperties(prefix = "spring.datasource.ssdw")
	public DataSourceProperties Properties() {
	    return new DataSourceProperties();
	}
	
	@Bean(name = "transmissionProperties")
	@Qualifier(value = "transmissionProperties")
	@ConfigurationProperties(prefix = "spring.datasource.transmission")
	public DataSourceProperties transmissionProperties() {
	    return new DataSourceProperties();
	}
	
	
	@Primary
	@Bean(name = "ssdwDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.ssdw")
	public DataSource ssdwDataSource() {
	    DataSource dataSoucre = Properties().initializeDataSourceBuilder()
	    		.type(DataSource.class).build();
	    return getDataSource(dataSoucre);
	}
	
	@Bean(name = "transmissionDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.transmission")
	public DataSource transmissionDataSource() {
	    DataSource dataSoucre = Properties().initializeDataSourceBuilder()
	    		.type(DataSource.class).build();
	    return dataSoucre;
	}
	
	@Bean(name = "ssdwJdbcTemplate")
	public JdbcTemplate ssdwJdbcTemplate( @Qualifier("ssdwDataSource") DataSource ssdwDataSource) {
		return new JdbcTemplate(ssdwDataSource);
	}
	
	@Bean(name = "ssdwNamedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate ssdwNamedParameterJdbcTemplate( @Qualifier("ssdwDataSource") DataSource ssdwDataSource) {
		return new NamedParameterJdbcTemplate(ssdwDataSource);
	}
	
	@Bean(name = "transmissionJdbcTemplate")
	public JdbcTemplate transmissionDataSourceJdbcTemplate( @Qualifier("transmissionDataSource") DataSource transmissionDataSource) {
		return new JdbcTemplate(transmissionDataSource);
	}
	
	@Bean(name = "transmissionNamedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate transmissionNamedParameterJdbcTemplate( @Qualifier("transmissionDataSource") DataSource transmissionDataSource) {
		return new NamedParameterJdbcTemplate(transmissionDataSource);
	}
	
	private DataSource getDataSource(DataSource dataSource) {
		dataSource.setInitialSize(dataSourcePropertiesReader.getInitialSize());
		dataSource.setMaxWait(dataSourcePropertiesReader.getMaxWait());
		dataSource.setMaxActive(dataSourcePropertiesReader.getMaxActive());
		dataSource.setMaxIdle(dataSourcePropertiesReader.getMaxIdle());
		dataSource.setDefaultAutoCommit(dataSourcePropertiesReader.getDefaultAutoCommit());
		dataSource.setValidationQuery(dataSourcePropertiesReader.getValidationQuery());
		dataSource.setTestOnBorrow(dataSourcePropertiesReader.getTestOnBorrow());
		dataSource.setTestOnConnect(dataSourcePropertiesReader.getTestOnConnect());
		dataSource.setTestOnReturn(dataSourcePropertiesReader.getTestOnReturn());
		dataSource.setTestWhileIdle(dataSourcePropertiesReader.getTestWhileIdle());
		return dataSource;
		
	}


}
