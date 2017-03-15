package com.thousandeyes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	//Method to configure the security of the REST URLs
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
        .authorizeRequests()
        .antMatchers("/h2-console/*").permitAll()
        .antMatchers(HttpMethod.GET, "/user/followers*").hasRole("USER")
        .antMatchers(HttpMethod.GET, "/user/follows*").hasRole("USER")
        .antMatchers(HttpMethod.POST, "/user/startFollowing*").hasRole("USER")
        .antMatchers(HttpMethod.POST, "/user/unfollow*").hasRole("USER")
        .antMatchers(HttpMethod.GET, "/tweet/list*").hasRole("USER")
        .antMatchers(HttpMethod.GET, "/user/mostPopularFollower*").hasRole("USER");
       
        
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
    
    //Method to configure the basic authentication information. 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("pass").roles("USER");
    }
    
    
}