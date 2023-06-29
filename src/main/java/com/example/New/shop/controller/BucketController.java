package com.example.New.shop.controller;

import com.example.New.shop.entities.Product;
import com.example.New.shop.service.BucketServiceV2;
import com.example.New.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class BucketController {
    private final BucketServiceV2 bucketServiceV2;

    private final ProductService productService;
    @Autowired
    public BucketController(BucketServiceV2 bucketServiceV2, ProductService productService) {
        this.bucketServiceV2 = bucketServiceV2;
        this.productService = productService;
    }

    @GetMapping("/bucket")
    public String bucket(Model model) {
        model.addAttribute("products", bucketServiceV2.viewProductInCart());
        model.addAttribute("totalPrice", bucketServiceV2.totalPrice());
        return "buckets";
    }


    @GetMapping("/bucket/add/{id}")
    public String addProductToBucket(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            bucketServiceV2.addProduct(product);
        }
        return "redirect:/";
    }

    @GetMapping("/bucket/remove/{id}")
    public String removeProductInBucket(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            bucketServiceV2.removeProduct(product);
        }
        return "redirect:/bucket";
    }
}
