package com.niit.mks.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 *
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfig 
{
	private final static String JDBC_URL = "jdbc:h2:tcp://localhost/~/test";
	private final static String JDBC_DRIVER_CLASS = "org.h2.Driver";
	private final static String USER_NAME = "sa";
	private final static String USER_PASSWORD = "";

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(JDBC_DRIVER_CLASS);
		dataSource.setUrl(JDBC_URL);
		dataSource.setUsername(USER_NAME);
		dataSource.setPassword(USER_PASSWORD);
		return dataSource;
	}

	@Bean
	public SessionFactory sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
		builder.addProperties(hibernateProperties());
		builder.scanPackages("com.niit.mks.model");
		return builder.buildSessionFactory();
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();

		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.lazy", "false");
		return properties;
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;

	}
}
