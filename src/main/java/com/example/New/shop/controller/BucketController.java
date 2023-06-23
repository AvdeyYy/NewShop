package com.example.New.shop.controller;


import com.example.New.shop.dto.BucketDto;
import com.example.New.shop.entities.Bucket;
import com.example.New.shop.repo.BucketRepository;
import com.example.New.shop.service.BucketService;
import com.example.New.shop.service.BucketServiceImpl;
import com.example.New.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
@Controller
public class BucketController {
    @Autowired
    private final BucketService bucketService;
    @Autowired
    private final ProductService productService;
    private final BucketRepository bucketRepository;

    public BucketController(BucketService bucketService, BucketServiceImpl bucketServiceImpl, ProductService productService, BucketRepository bucketRepository) {
        this.bucketService = bucketService;
        this.productService = productService;
        this.bucketRepository = bucketRepository;
    }
    @GetMapping("/bucket")
    public String Bucket(Model model) {
//        if (principal == null) {
//            model.addAttribute("buckets", new BucketDto());
//        } else {
//            BucketDto bucketDto = bucketService.getBucket(principal.getName());
//            model.addAttribute("buckets", bucketDto);
//        }
//        Iterable<Bucket> buckets = bucketRepository.findAll();
        model.addAttribute("bucket");
        return "buckets";
    }





    @PostMapping("/bucket/add/{id}")
    public String addBucket(@PathVariable Long id, Principal principal) {
        productService.addBucketToUser(principal.getName(), id);
        return "redirect:/";
    }

//    @GetMapping("/bucket/remove/{id}")
//    public String removeProductFromBucket(@PathVariable("id") Long id) {
//        Product product = productService.findById(id);
//        if (product != null) bucketService.removeProduct(product);
//        return "redirect:/home";
//    }




}
