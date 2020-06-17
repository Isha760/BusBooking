package com.NewBusBookingApp.NBBA.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.NewBusBookingApp.NBBA.model.User;
import com.NewBusBookingApp.NBBA.repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository repo;
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		
		 User user =repo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
		return UserPrincipal.create(user);
		
	}
	
	public UserDetails loadUserById(Long Id) {
		
		User user=repo.findById(Id).get();
		return UserPrincipal.create(user);
	}

}
