package com.example.New.shop.repo;

import com.example.New.shop.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);


}