package com.thu.movie.service;

import com.thu.movie.DTO.UserDto;
import com.thu.movie.model.User;
import com.thu.movie.repository.UserRepository;
import com.thu.movie.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService
        implements UserDetailsService
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userRepository.findByPhone(phone);
        if (user == null) {
            throw new UsernameNotFoundException(phone);
        } else {
            return new CustomUserDetails(user);
        }
    }

    public User registerNewUserAccount(UserDto user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setPhone(user.getPhone());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setImageUrl(user.getImageUrl());
        newUser.setRole("ROLE_USER");
        return userRepository.save(newUser);
    }
}
