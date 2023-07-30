package com.example.New.shop.service;

import com.example.New.shop.entities.User;
import com.example.New.shop.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public void createUser(User user){
        String userName = user.getUsername();
        if (userRepository.findByUsername(userName) != null) {
            return;
        }

        user.setUsername(userName);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRoles(Collections.singleton(Roles.ADMIN));
        userRepository.save(user);
    }

//    public void addBucketToUser(User user) {
//        if (user.getBucket() == null) {
//            Bucket bucket = new Bucket();
//            user.setBucket(bucket);
//            userRepository.save(user);
//        }
//    }
}