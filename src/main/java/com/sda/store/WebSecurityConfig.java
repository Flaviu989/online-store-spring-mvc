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
					.antMatchers("/css/welcome.css", "/css/product-list.css", "/css/product.css", "/css/product-search.css", "/css/user-form.css").permitAll()
					.antMatchers("/js/welcome.js", "/js/product-search.js").permitAll()
					.antMatchers("/images/product_thumbnail/*").permitAll()
					.antMatchers("/", "/list/*/*/*", "/product/*", "/search", "/user/*").permitAll()
					.antMatchers("/cart", "/orders", "/user/settings", "/css/cart.css", "/css/order.css", "/css/user.css").authenticated()
					.antMatchers("/admin", "admin/**", "/css/admin-panel.css").hasRole("ADMIN").anyRequest().authenticated()
					.and()
				.formLogin()
					.permitAll();
	}

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
//			User and password in memory
//			.inMemoryAuthentication()
//				.withUser("admin").password(passwordEncoder().encode(adminPassword)).roles("ADMIN")
//				.and()
//				.withUser("guest").password(passwordEncoder().encode("guest")).roles("GUEST")
//				.and();
		
//			User and password in database
			.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery("select username,password,1 "
    					+ "from user "
    					+ "where username = ?")
    			.authoritiesByUsernameQuery("select username,"
    					+ "IF(admin=1,'ROLE_ADMIN','ROLE_USER') as authority "
    					+ "from user "
    					+ "where username = ?")
				.passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
