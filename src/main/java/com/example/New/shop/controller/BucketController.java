package com.example.New.shop.controller;

import com.example.New.shop.entities.User;
import com.example.New.shop.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class BucketController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/bucket")
    public String bucket(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        if (user == null)
            throw new IllegalStateException();

        model.addAttribute("title", "Bucket");
        model.addAttribute("buckets", user.getProduct());
        return "bucket";
    }

    @PostMapping("/bucket/remove/{id}")
    public String removeFromBucket(@PathVariable Long id, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        if (user == null) {
            throw new IllegalStateException("User must exist");
        }

        productRepository.findById(id).ifPresent(product -> {
            if (user.getProduct().stream().noneMatch(other -> other.getId().equals(product.getId()))) {
                // TODO выводить сообщение об ошибке (продукта нет в корзине)
                return;
            }
            user.getProduct().remove(product);
            userRepository.save(user);
        });

        return "redirect:/bucket";
    }
}