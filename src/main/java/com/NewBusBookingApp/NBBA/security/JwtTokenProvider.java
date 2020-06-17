package com.NewBusBookingApp.NBBA.security;
import io.jsonwebtoken.*;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Component
public class JwtTokenProvider{
	
	  @Value("${app.jwtSecret}")
	    private String jwtSecret;

	    @Value("${app.jwtExpirationInMs}")
	    private int jwtExpirationInMs;
	    
	    

	    public String generateToken(Authentication authentication) {

	        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
	        
	        Date now = new Date();
	        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

	        return Jwts.builder()
	                .setSubject(Long.toString(userPrincipal.getId()))
	                .setIssuedAt(new Date())
	                .setExpiration(expiryDate)
	                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, jwtSecret)
	                .compact();
	    }
	    
	   

	    public Long getUserIdFromJWT(String token) {
	        Claims claims = Jwts.parser()
	                .setSigningKey(jwtSecret)
	                .parseClaimsJws(token)
	                .getBody();

	        return Long.parseLong(claims.getSubject());
	    }

	    public boolean validateToken(String authToken) {
	        try {
	            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
	            return true;
	        } catch (Exception ex) {
	        	return false;
	        }
	   
	    }

}
