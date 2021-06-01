package com.seleniumexpress.config;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.google.protobuf.AbstractMessage.Builder;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	// this class helps to create spring security filter chain or gun man

	// user details username , password ,roles

	@Autowired
	private DataSource dataSource;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
		
		/*//in memory storing data in server memory
	  auth.inMemoryAuthentication().withUser("vishnu").password(
	  "{noop}buddy").roles("admin").and() .withUser("karthik").password(
	  "{bcrypt}$2a$10$zvIA28qOjcp/PcCdt0ZCbOGmOiO526WVOnbhS9s25Sv0Id1sNCkse")
	  .roles("user"); karthik123*/
	  
	  
	  // loading the users info from database
	  auth
	  .jdbcAuthentication()
	  .dataSource(dataSource)
	  .usersByUsernameQuery("select username,password,enabled from customers where username=?")
	  .authoritiesByUsernameQuery("select username,roles from customers where username=?")
	  .passwordEncoder(passwordEncoder);
	  
	  }

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth)
	 * throws Exception {
	 * 
	 * //creating user using inmemory in spring security
	 * ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
	 * SimpleGrantedAuthority role1 = new SimpleGrantedAuthority("ADMIN");
	 * SimpleGrantedAuthority role2 = new SimpleGrantedAuthority("USER");
	 * roles.add(role1); roles.add(role2); User bud = new
	 * User("bud46@gmail.com", "vi16",roles);
	 * 
	 * //creating user second way InMemoryUserDetailsManager userDetailsManager
	 * = new InMemoryUserDetailsManager(); UserDetails budUser =
	 * User.withUsername("Buddy").password("vibi14").roles("ADMIN","USER").build
	 * (); UserDetails samUser =
	 * User.withUsername("sameer").password("sam12").roles("USER").build();
	 * userDetailsManager.createUser(budUser);
	 * userDetailsManager.createUser(samUser);
	 * auth.userDetailsService(userDetailsManager);
	 * 
	 * }
	 */

	@Override

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/coder").hasAuthority("Coder").antMatchers("/trainer")
				.hasAuthority("Trainer").and().formLogin().loginPage("/myCustomLogin")
				.loginProcessingUrl("/process-login").permitAll().and().httpBasic().and().logout().permitAll().and()
				.exceptionHandling().accessDeniedPage("/accessDenied");
		/*
		 * antMatchers("/hello").authenticated().antMatchers("/bye").
		 * authenticated() .antMatchers("/home").authenticated()
		 */

	}
	/*
	 * @Bean PasswordEncoder getPasswordEncoder() {
	 * 
	 * return NoOpPasswordEncoder.getInstance(); }
	 */
}
