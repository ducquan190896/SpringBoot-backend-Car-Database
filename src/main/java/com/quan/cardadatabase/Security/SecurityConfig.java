package com.quan.cardadatabase.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.quan.cardadatabase.Security.Filter.FilterAuthentication;
import com.quan.cardadatabase.Security.Filter.FilterException;
import com.quan.cardadatabase.Security.Filter.FilterJwtAuthorization;

import lombok.AllArgsConstructor;

@EnableWebSecurity
// @EnableGlobalMethodSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    CustomAuthenticationManager customAuthenticationManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        FilterAuthentication filterAuthentication = new FilterAuthentication(customAuthenticationManager);
        filterAuthentication.setFilterProcessesUrl("/login");

        http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/api/v1/account/register").permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilterBefore(new FilterException(), filterAuthentication.getClass())
        .addFilter(filterAuthentication)
        .addFilterAfter(new FilterJwtAuthorization(), filterAuthentication.getClass())
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}
