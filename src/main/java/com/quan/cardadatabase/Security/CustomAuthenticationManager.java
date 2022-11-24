package com.quan.cardadatabase.Security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.cardadatabase.Model.Account;
import com.quan.cardadatabase.Repository.AccountRepos;

@Component
public class CustomAuthenticationManager implements AuthenticationManager{

    @Autowired
    AccountRepos accountRepos;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       Optional<Account> entity  = accountRepos.findByUsername(authentication.getName());

       if(!entity.isPresent()) {
        throw new EntityNotFoundException("the account with username not found");

       }

       Account account = entity.get();

      boolean isCheck = new BCryptPasswordEncoder().matches(authentication.getCredentials().toString(), account.getPassword());
      System.out.println(account);
      if(!isCheck) {
        throw new BadCredentialsException("your password is wrong");
      }
      List<SimpleGrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority(account.getRole().getRoleType().getRoleType()));
      Authentication auth = new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword(), authorities);
      System.out.println(account);
      return auth;
    }
}
