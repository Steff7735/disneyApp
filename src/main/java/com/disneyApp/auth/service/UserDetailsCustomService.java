package com.disneyApp.auth.service;

import com.disneyApp.auth.dto.UserDTO;
import com.disneyApp.auth.entity.UserEntity;
import com.disneyApp.auth.repository.UserRepository;
import com.disneyApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity foundUser = userRepository.findByUsername(username);

        if (foundUser == null) {
            throw new UsernameNotFoundException("Username: " + username + " -> NOT FOUND");
        }

        return new User(
                foundUser.getUsername(),
                foundUser.getPassword(),
                Collections.emptyList() // Roles
        );
    }


    public boolean signupUser(@Valid UserDTO userToCreate) {

        UserEntity newUser = new UserEntity();
        newUser.setUsername(userToCreate.getUsername());
        newUser.setPassword(userToCreate.getPassword());
        // ===

        UserEntity matchingUser = userRepository.findByUsername(userToCreate.getUsername());
        if(matchingUser != null && (matchingUser.getUsername().equals(newUser.getUsername()))) {
            // Already Exists
            // The Controller verify TRUE or FALSE and send ResponseEntity
            return false;
        }
        newUser = userRepository.save(newUser);

        //Email Stuff:
        if(newUser != null) {
            emailService.sendWelcomeEmail(newUser.getUsername());
        }

        return newUser != null;
    }
}
