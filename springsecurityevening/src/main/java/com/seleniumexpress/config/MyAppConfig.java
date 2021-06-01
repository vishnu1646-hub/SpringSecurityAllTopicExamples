package com.seleniumexpress.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("com")
public class MyAppConfig {

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public DataSource datasource() {

		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/devdb");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("Madhuvishnu14");
		driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return driverManagerDataSource;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		//return new BCryptPasswordEncoder();
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource());
		return jdbcTemplate;
	}
	
	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager(){
		//return new JdbcUserDetailsManager(datasource());
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(datasource());
		jdbcUserDetailsManager.setUsersByUsernameQuery("select username,password,enabled from customers where username=?");
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select username,roles from customers where username=?");
		jdbcUserDetailsManager.setChangePasswordSql("update customers set password=? where username=?");
		jdbcUserDetailsManager.setDeleteUserSql("delete from customers where username=?");
		return jdbcUserDetailsManager;
	}
}
