package com.sda.store;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${security.adminPassword}")
	private String adminPassword;

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// defining access to diverse resources / groups of resources
				// for diverse roles, for all authentified user
				// or for everyone
				.authorizeRequests().antMatchers("/public/**").permitAll().antMatchers("/main.css").permitAll()
				.antMatchers("/").permitAll().antMatchers("/userList/*", "/deleteUser").hasRole("ADMIN").anyRequest()
				.authenticated().and().formLogin().permitAll();
	}

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode(adminPassword)).roles("ADMIN")
				.and().and().jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select email,password,enabled " + "from users " + "where email = ?")
				.authoritiesByUsernameQuery("select email,authority " + "from authorities " + "where email = ?");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
