package com.thousandeyes;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/h2-console/*").permitAll()
            .antMatchers(HttpMethod.GET, "/userFollowers*").permitAll()
            .antMatchers(HttpMethod.GET, "/userFollows*").permitAll()
            .antMatchers(HttpMethod.GET, "/startFollowing*").permitAll()
            .antMatchers(HttpMethod.GET, "/unfollow*").permitAll()
            .antMatchers(HttpMethod.GET, "/tweetList*").permitAll()
            .anyRequest().authenticated();
        
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}