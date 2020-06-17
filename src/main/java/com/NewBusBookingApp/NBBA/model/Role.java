package com.NewBusBookingApp.NBBA.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role {
	public Role(){
		
	}
	public Role(long id, RoleName role) {
		
		this.id = id;
		this.role = role;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Enumerated(EnumType.STRING)

	private RoleName role;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public RoleName getRole() {
		return role;
	}
	
	public void setRole(RoleName role) {
		this.role = role;
	}
}
