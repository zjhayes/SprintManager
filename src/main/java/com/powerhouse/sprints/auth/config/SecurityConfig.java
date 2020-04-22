package com.powerhouse.sprints.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	// @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests(authorize -> authorize
			.antMatchers("/css/**", "/").permitAll()
			.antMatchers("/sprints/**").authenticated()
			.antMatchers("/admin").hasRole("ADMIN")
		)
		.formLogin(formLogin -> formLogin
			.loginPage("/login")
			.failureUrl("/login-error")
			.defaultSuccessUrl("/projects")
			.usernameParameter("email")
		).logout(logout -> logout
				.logoutUrl("/logout")
		);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	// @formatter:on

}
