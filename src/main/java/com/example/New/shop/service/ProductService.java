package com.example.New.shop.service;

import com.example.New.shop.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


public interface ProductService {
    void saveProduct(Product product,MultipartFile file) throws IOException;
    void deleteProduct(Long id);
    Optional<Product> findById(Long id);

//    void addBucketToUser(String username, Long productId);


}
