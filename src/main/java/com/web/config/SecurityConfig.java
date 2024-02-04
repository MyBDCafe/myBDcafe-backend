package com.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {

	    return new BCryptPasswordEncoder();
	}
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

    	http
				.csrf().disable()
				.headers().frameOptions().disable()
				.and()
				.authorizeHttpRequests()
				.antMatchers("/", "/login").permitAll()
				.antMatchers("/admin").hasRole("ADMIN")
				.antMatchers("/my/**").hasAnyRole("ADMIN", "USER")
				.anyRequest().permitAll()
                ;

        return http.build();
    }

}
