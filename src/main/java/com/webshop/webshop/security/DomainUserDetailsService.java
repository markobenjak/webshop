package com.webshop.webshop.security;

import com.webshop.webshop.Customer.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {
    private final CustomerDao customerDao;

    public DomainUserDetailsService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String email) {

        Optional<Customer> customer = customerDao.FindCustomerByEmail(email);


        return customerDao
                .FindCustomerByEmail(email)
                .map(this::createSpringSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException("User " + email + " was not found in the database"));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(Customer customer) {
        ArrayList<Authority> authorities = new ArrayList<Authority>();
        authorities.add(customer.getAuthority());

        List<GrantedAuthority> grantedAuthorities = authorities
                                .stream()
                                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(), grantedAuthorities);
    }
}
