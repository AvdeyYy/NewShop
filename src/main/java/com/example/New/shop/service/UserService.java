package com.example.New.shop.service;

import com.example.New.shop.entities.User;
import com.example.New.shop.entities.enums.Roles;
import com.example.New.shop.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean createUser(User user){
        String userName = user.getUsername();
        if (userRepository.findByUsername(userName) != null) {
            return false;
        }
        user.setUsername(userName);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Roles.ADMIN));
        userRepository.save(user);
        return true;
    }

}
