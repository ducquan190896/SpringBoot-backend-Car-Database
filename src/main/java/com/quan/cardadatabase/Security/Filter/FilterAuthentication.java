package com.quan.cardadatabase.Security.Filter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.cardadatabase.Model.Account;
import com.quan.cardadatabase.Security.CustomAuthenticationManager;
import com.quan.cardadatabase.Security.SecurityConstant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FilterAuthentication extends UsernamePasswordAuthenticationFilter  {

    @Autowired 
    CustomAuthenticationManager customAuthenticationManager;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
     try {
        Account account = new ObjectMapper().readValue(request.getInputStream(), Account.class);
        Authentication authentication = new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword());
    
        return customAuthenticationManager.authenticate(authentication);
     } catch (IOException ex) {
        throw new RuntimeException(ex.getMessage());
     }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(failed.getMessage());
            response.getWriter().flush();
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
    System.out.println(authResult.getName() + " _ " + authResult.getCredentials());
      List<String> claims = authResult.getAuthorities().stream().map(au -> au.getAuthority()).collect(Collectors.toList());
      System.out.println(claims);

      String token = JWT
      .create()
      .withSubject(authResult.getName())
      .withClaim("authorities", claims)
      .sign(Algorithm.HMAC512(SecurityConstant.private_key));
      response.setHeader("Authorization", SecurityConstant.authorization + token);
    }

}
