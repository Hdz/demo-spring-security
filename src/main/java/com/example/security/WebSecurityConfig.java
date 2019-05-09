package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	  @Bean
	  public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }
	  
	  @Override
	  @Bean
	  public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	  }
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
		 
		   http
		      // désactivation de la protection CSRF
		      // non utilisée dans le cadre d'une Web API
		      .csrf().disable()
		      .authorizeRequests()

		      // un GET /exemples n'est pas soumise à authentification
		      .antMatchers(HttpMethod.GET, "/exemples").permitAll()
		      .antMatchers("/h2-console/**").permitAll()

		      // TODO
		      .antMatchers("/auth").permitAll()
		      // Les autres requêtes sont soumises à authentification
		      .anyRequest().authenticated()
		      .and().headers().frameOptions().disable();
	  }
}