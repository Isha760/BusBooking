package com.NewBusBookingApp.NBBA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.NewBusBookingApp.NBBA.model.Role;
import com.NewBusBookingApp.NBBA.model.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
	
	Role findByRole(RoleName roleName);

}
