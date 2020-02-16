package com.sda.store;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${security.adminPassword}")
	private String adminPassword;

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
					.antMatchers("/public/**").permitAll()
					.antMatchers("/css/welcome.css", "/css/product-list.css", "/css/product.css").permitAll()
					.antMatchers("/js/welcome.js").permitAll()
					.antMatchers("/images/product_thumbnail/*").permitAll()
					.antMatchers("/", "/list/*/*/*", "/product/*").permitAll()
					.antMatchers("/userList/*", "/deleteUser").hasRole("ADMIN").anyRequest().authenticated()
					.and()
				.formLogin()
					.permitAll();
	}

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("admin").password(passwordEncoder().encode(adminPassword)).roles("ADMIN")
				.and()
				.withUser("guest").password(passwordEncoder().encode("guest")).roles("GUEST")
				.and();
//			.and()
//			.jdbcAuthentication().dataSource(dataSource)
//				.usersByUsernameQuery("select email,password,enabled " + "from users " + "where email = ?")
//				.authoritiesByUsernameQuery("select email,authority " + "from authorities " + "where email = ?");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
