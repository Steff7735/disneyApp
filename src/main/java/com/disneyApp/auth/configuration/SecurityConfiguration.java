/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.disneyApp.auth.configuration;

import com.disneyApp.auth.filter.JwtRequestFilter;
import com.disneyApp.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author fanny
 */
@EnableWebSecurity
@SuppressWarnings("deprecation")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


        // LET'S IMPORT CustomService y CustomFilter.
        @Autowired
        private UserDetailsCustomService userDetailsCustomService;
        @Autowired
        private JwtRequestFilter jwtReqFilter;

        // --- MUST ---
        @Override
        @Bean
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return NoOpPasswordEncoder.getInstance();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception{
            auth.userDetailsService(userDetailsCustomService);
        }

        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception{
            // DISABLE CSRF
            httpSecurity.csrf().disable()
                    // PERMIT All AROUND de "/auth/*"  THIS COUDT BE DANGEROUS
                    .authorizeRequests().antMatchers("/auth/*").permitAll()
                    .anyRequest().authenticated()
                    .and().exceptionHandling()
                    .and().sessionManagement()
                    // Policy = For Each Endpoint Called, NEW Headers. (Always Request Auth, stateless)
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            // ADDING FILTER BEFORE PROCESSING REQUEST ALWAYS
            httpSecurity.addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class);
        }
    
}
