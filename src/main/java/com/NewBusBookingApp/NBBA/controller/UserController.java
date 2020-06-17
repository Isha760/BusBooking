package com.NewBusBookingApp.NBBA.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.NewBusBookingApp.NBBA.model.User;
import com.NewBusBookingApp.NBBA.repository.UserRepository;
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserRepository userrepo;
	
	@RequestMapping(value="/getAll" , method=RequestMethod.GET ,produces= {"application/json"})
	//@PreAuthorize("hasRole('ROLE_USER')")
	public List<User> getAllUser()
	{
System.out.println("aLl");
return userrepo.findAll();
	}
	
	@RequestMapping(value="/update/{id}" , method=RequestMethod.PUT,produces= {"application/json"})
	//@Secured({"ROLE_USER","ROLE_ADMIN"})
	public User updateUser(@PathVariable(value="id")Long id, @Valid @RequestBody User userDetails)
	{
		 User user = userrepo.findById(id).get();

 	    if(userDetails.getUsername()!=null && userDetails.getUsername()!="")
 	    user.setUsername(userDetails.getUsername());
 	    if(userDetails.getPhonenum()!=null && userDetails.getPhonenum()!="")
 	    user.setPhonenum(userDetails.getPhonenum());
 	    if(userDetails.getGender()!=null && userDetails.getGender()!="")
 	    user.setGender(userDetails.getGender());
 	    if(userDetails.getCity()!=null && userDetails.getCity()!="")
 	    user.setCity(userDetails.getCity());
 	    if(userDetails.getDob()!=null && userDetails.getDob()!="")
     	user.setDob(userDetails.getDob());
 	    
 	    
 	    userrepo.save(user);
 	    System.out.println("user updated");
 	    
		return user;
	}
	
	
	@RequestMapping(value="/delete/{id}" , method=RequestMethod.DELETE,produces= {"application/json"})
	public void deleteUser(@PathVariable(value="id")Long id)
	{
		 User user = userrepo.findById(id).get();
		 System.out.println(user.getUsername()+" is deleted");
		 userrepo.delete(user);
		 System.out.println(user.getUsername()+" is deleted");
		
	}
}
