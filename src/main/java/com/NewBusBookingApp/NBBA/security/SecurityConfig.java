package com.NewBusBookingApp.NBBA.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( securedEnabled = true,
jsr250Enabled = true,
prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter	 {
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	/**	@Bean
	public AuthenticationProvider authProvider() {

		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());


		return provider;

	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
			.csrf().disable()
			 .sessionManagement()
             .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
             .and()
			.authorizeRequests()//antMatchers(HttpMethod.POST, "/api/main/**").permitAll().antMatchers(HttpMethod.GET, "/api/**").
			.anyRequest().fullyAuthenticated();
	}
	 */
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {

		return new JwtAuthenticationFilter();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}


	@Bean   
	public BCryptPasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.cors().and()
		.csrf().disable()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/api/main/**")
		.permitAll().antMatchers(HttpMethod.GET,"/api/getAll").permitAll()
		.antMatchers(HttpMethod.PUT,"/api/update/**").permitAll()
		.antMatchers(HttpMethod.DELETE,"/api/delete/**").permitAll()
		.antMatchers(HttpMethod.GET,"/api/internal/**").permitAll()
		.anyRequest().authenticated();

		http.addFilterBefore(jwtAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);

	}


}
