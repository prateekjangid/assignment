package com.amdocs.media.assignement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.amdocs.media.assignement.service.AuthenticationService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AuthenticationService authService;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
				.authorizeRequests().antMatchers("/login").permitAll().
						anyRequest().permitAll().and().
						exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}
	  @Autowired
	    PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
		 authBuilder.userDetailsService(authService);
	}
	
	@Bean()
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
