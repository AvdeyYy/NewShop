package com.example.New.shop.controller;


import com.example.New.shop.entities.Product;
import com.example.New.shop.repo.ProductRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    @Autowired
    private final ProductRepository productRepository;
//    @GetMapping("/")
//    public String homeProduct(String title, Model model) {
//        model.addAttribute("title", "Home page");
//        return "home";
//    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About us");
        return "about";
    }

    @GetMapping("/")
    public String products(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "home";


    }
}
