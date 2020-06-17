package com.NewBusBookingApp.NBBA.security;

import java.util.Collection;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.NewBusBookingApp.NBBA.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
public class UserPrincipal implements UserDetails {
	private Long id;
	 private Collection<? extends GrantedAuthority> authorities;
	private String username;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDob() {
		return Dob;
	}

	public void setDob(String dob) {
		Dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhonenum() {
		return Phonenum;
	}

	public void setPhonenum(String phonenum) {
		Phonenum = phonenum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String city;
	private String Dob;
	private String gender;
	private String Phonenum;

	@JsonIgnore
	private String email;
	@JsonIgnore
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	  public UserPrincipal(Long id, String name, String city, String Dob,String gender,String Phonenum,  String email, String password, Collection<? extends GrantedAuthority> authorities) {
	        this.id = id;
	        this.username = name;
	        this.city = city;
	        this.Dob = Dob;
	        this.gender = gender;
	        this.Phonenum = Phonenum;
	        this.email = email;
	        this.password = password;
	        this.authorities = authorities;
	    }
	  public static UserPrincipal create(User user) {
	        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
	                new SimpleGrantedAuthority(role.getRole().name())
	        ).collect(Collectors.toList());

	        return new UserPrincipal(
	                user.getUser_id(),
	                user.getUsername(),
	                user.getCity(),
	                user.getDob(),
	                user.getGender(),
	                user.getPhonenum(),
	                user.getEmail(),
	                user.getPassword(),
	                authorities
	        );
	    }


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        UserPrincipal that = (UserPrincipal) o;
	        return Objects.equals(id, that.id);
	    }

	    @Override
	    public int hashCode() {

	        return Objects.hash(id);
	    }

}
