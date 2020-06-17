package com.NewBusBookingApp.NBBA.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.NewBusBookingApp.NBBA.model.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	User findByEmail(String email);
	User findByUsername(String username);
	User findByUsernameOrEmail(String username, String email);
	
}
