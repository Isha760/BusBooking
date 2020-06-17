package com.NewBusBookingApp.NBBA.controller;


import java.util.Collections;
import  com.NewBusBookingApp.NBBA.security.*;
import  com.NewBusBookingApp.NBBA.onStream.*;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.NewBusBookingApp.NBBA.model.Role;
import com.NewBusBookingApp.NBBA.model.RoleName;
import com.NewBusBookingApp.NBBA.model.User;
import com.NewBusBookingApp.NBBA.repository.RoleRepository;
import com.NewBusBookingApp.NBBA.repository.UserRepository;

@RestController
@RequestMapping("/api/main")
public class MainController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userrepo;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;

	@RequestMapping(value="/login",method=RequestMethod.POST,produces= {"application/json"})
	public ResponseEntity<?> login(@Valid @RequestBody LogData regData1) {


		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						regData1.getUsernameOrEmail(),
						regData1.getPassword()
						)
				);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = tokenProvider.generateToken(authentication);
	
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));


	}


	@RequestMapping(value="/register",method=RequestMethod.POST,produces= {"application/json"})
	public void register(@Valid @RequestBody RegisData regData) {

		User user=new User(regData.getEmail(),regData.getPassword());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role userRole = roleRepository.findByRole(RoleName.ROLE_USER);

		user.setRoles(Collections.singleton(userRole));

		userrepo.save(user);
		
		System.out.println("User Registered");
	}

}
