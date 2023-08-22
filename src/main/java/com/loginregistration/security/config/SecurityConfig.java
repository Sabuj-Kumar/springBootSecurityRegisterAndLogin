package com.loginregistration.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder () {
		
		return new BCryptPasswordEncoder();
	}
	@Bean
	public UserDetailsService getUserDetailService() {
		
		return new CustomUserDetailsService();
	}
	@Bean
	public DaoAuthenticationProvider getDaoAuthenticationProvider() {
		
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		
		daoAuthenticationProvider.setUserDetailsService(getUserDetailService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return daoAuthenticationProvider;
	}
	@Bean
	public SecurityFilterChain fiterChain(HttpSecurity http) throws Exception{
		
		http.csrf().disable()
		.authorizeHttpRequests().requestMatchers("/","/register","/signin","/createUser").permitAll()
		.requestMatchers("/user/**").authenticated().and()
		.formLogin().loginPage("/signin").usernameParameter("email").loginProcessingUrl("/userLogin")
		.defaultSuccessUrl("/user/profile").permitAll();
		
		return http.build();
	}
}
