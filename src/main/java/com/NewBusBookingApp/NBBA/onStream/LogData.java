package com.NewBusBookingApp.NBBA.onStream;

import javax.validation.constraints.NotBlank;

public class LogData {

	 @NotBlank
	    private String usernameOrEmail;

	    @NotBlank
	    private String password;
	 

	    public String getUsernameOrEmail() {
	        return usernameOrEmail;
	    }

	    public void setUsernameOrEmail(String usernameOrEmail) {
	        this.usernameOrEmail = usernameOrEmail;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
}
