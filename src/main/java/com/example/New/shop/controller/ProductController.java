package com.example.New.shop.controller;
import com.example.New.shop.entities.*;
import com.example.New.shop.repo.*;
import com.example.New.shop.service.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ImageRepository imageRepository;

    @GetMapping("/product")
    public String products(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<?> getImageId(@PathVariable Long id) {
        Image image = imageRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", image.getName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }

    @PostMapping("/product/create")
    public String createProduct(Product product, MultipartFile file) throws IOException {
        productService.saveProduct(product,file);
        return "redirect:/product";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productRepository.findById(id).ifPresent(product -> {
            userRepository.findAll().forEach(user -> {
                if (user.getProduct().remove(product))
                    userRepository.save(user);
            });

            productRepository.delete(product);
        });

        return "redirect:/product";
    }

    @GetMapping("product/{id}/bucket")
    public String bucket(@PathVariable Long id, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        if (user == null)
            throw new IllegalStateException("User must exist");

        productRepository.findById(id).ifPresent(product -> {
            if (user.getProduct().stream().anyMatch(other -> other.getId().equals(product.getId()))) {
                // TODO выводить сообщение об ошибке (продукт уже в корзине)
                return;
            }

            user.getProduct().add(product);
            userRepository.save(user);
        });

        return "redirect:/bucket";
    }
}