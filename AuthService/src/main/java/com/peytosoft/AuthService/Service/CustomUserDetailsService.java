package com.peytosoft.AuthService.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.peytosoft.AuthService.Dao.UserRepository;




@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
    private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        return repository.findByEmail(username).orElseThrow();
	}

}
