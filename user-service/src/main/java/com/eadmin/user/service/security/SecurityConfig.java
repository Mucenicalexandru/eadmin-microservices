package com.eadmin.user.service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenServices jwtTokenServices;

    public SecurityConfig(JwtTokenServices jwtTokenServices) {
        this.jwtTokenServices = jwtTokenServices;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    //all the request will go trough a chain of filters
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .addFilterBefore(new JwtTokenFilter(jwtTokenServices), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
//                    .antMatchers("/api/register-user/*").permitAll()
//                    .antMatchers("/api/register-user").permitAll()
//                    .antMatchers("/api/register-provider").permitAll()
//
//                    .antMatchers("/api/login").permitAll()
//
//                    .antMatchers("/api/groups").permitAll()
//                    .antMatchers("/api/group/*").permitAll()
//
//                    //TODO to reconsider this. Someone can steal all the database of providers
//                    .antMatchers("/api/service_providers").anonymous()
//
//                    .antMatchers("/api/service_providers").permitAll()
//
//                    .antMatchers("/api/group-by-town/*").permitAll()
//
//                    .antMatchers("/api/add-group").hasAuthority("ADMIN")
//                    .antMatchers("/api/delete-group/*").hasAuthority("ADMIN")
//                    .antMatchers("/api/update-group/*").hasAuthority("ADMIN")
//
//                    .antMatchers("/api/add-building/*").hasAuthority("ADMIN")
//                    .antMatchers("/api/delete-building/*").hasAuthority("ADMIN")
//
//                    .antMatchers("/api/add-administrator/*").hasAuthority("ADMIN")
//                    .antMatchers("/api/add-censor/*").hasAuthority("ADMIN")
//                    .antMatchers("/api/add-president/*").hasAuthority("ADMIN")
//
//                    .antMatchers("/api/administrators").hasAuthority("ADMIN")
//                    .antMatchers("/api/presidents").hasAuthority("ADMIN")
//                    .antMatchers("/api/users").permitAll()
//                    .antMatchers("/api/users-by-town/*").hasAuthority("ADMIN")
//
//                    .antMatchers("/api/building/*").hasAuthority("USER")
//                    .antMatchers("/api/group-by-administrator/*").hasAuthority("ADMINISTRATOR")
//                    .antMatchers("/api/building-by-president/*").hasAuthority("PRESIDENT")
//
//                    .antMatchers("/api/tickets-by-building/*").hasAuthority("USER")
//
//                    .antMatchers("/api/administrative-tickets-by-status-and-buildingId/opened/*").hasAuthority("USER")
//                    .antMatchers("/api/administrative-tickets-by-status-and-buildingId/in progress/*").hasAuthority("USER")
//                    .antMatchers("/api/administrative-tickets-by-status-and-buildingId/closed/*").hasAuthority("USER")
//
//                    .antMatchers("/api/add-ticket/*").hasAuthority("PRESIDENT")
//                    .antMatchers("/api/add-poll/*").hasAuthority("PRESIDENT")
//                    .antMatchers("/api/set-poll-inactive/*").hasAuthority("PRESIDENT")
//
//                    //TODO to fix this only for ADMINISTRATOR
//                    .antMatchers("/api/close-ticket/**").permitAll()
//                    .antMatchers("/api/delete-all-by-ticket/*").hasAuthority("ADMINISTRATOR")
//                    .antMatchers("/api/reject-pending_service_offer/*").hasAuthority("ADMINISTRATOR")
//                    .antMatchers("/api/accept-offer/**").hasAuthority("ADMINISTRATOR")
//
//                    //TODO to fix this only for ADMINISTRATOR
//                    .antMatchers("/api/reject-user/*").permitAll()
//                    .antMatchers("/api/accept-user/*").permitAll()
//
//                    .antMatchers("/api/add-personal-ticket").hasAuthority("USER")
//
//                    .antMatchers( "/api/users").permitAll()
//                    .antMatchers( "/api/user/*").permitAll()
//                    .antMatchers( "/api/all-users").permitAll()
//                    //TODO to fix this only for ADMINISTRATOR and USER
//                    .antMatchers( "/api/add-review/*").permitAll()
//                    .antMatchers( "/api/add-review/*").permitAll()
//
//                    .antMatchers( "/api/buildings").permitAll()
//                    .antMatchers( "/api/join-request/*").permitAll()
//                    .antMatchers( "/api/tickets").permitAll()
                    .anyRequest().permitAll();

    }
}
