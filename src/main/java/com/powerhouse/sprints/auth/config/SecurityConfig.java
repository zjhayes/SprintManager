package com.powerhouse.sprints.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests(authorize -> authorize
			.antMatchers("/css/**", "/").permitAll()
			.antMatchers("/sprints/**").hasRole("ROLES_USER")
		)
		.formLogin(formLogin -> formLogin
			.loginPage("/login")
			.failureUrl("/login-error")
			.defaultSuccessUrl("/sprints")
		).logout(logout -> logout
				.logoutUrl("/logout")
		);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("austin")
		.password(bCryptPasswordEncoder().encode("austin"))
		.roles("ROLES_USER")
		.and()
		.withUser("zach")
		.password(bCryptPasswordEncoder().encode("zach"))
		.roles("ROLES_USER")
		.and()
		.withUser("jon")
		.password(bCryptPasswordEncoder().encode("jon"))
		.roles("ROLES_USER");
	}
	// @formatter:on
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
