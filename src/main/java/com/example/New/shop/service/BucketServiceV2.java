package com.example.New.shop.service;

import com.example.New.shop.entities.Bucket;
import com.example.New.shop.entities.Product;
import com.example.New.shop.entities.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BucketServiceV2 {
    void addProduct(Optional<Product> product);
    void removeProduct(Optional<Product> product);
    void clearProduct();
    Map<Product, Integer> viewProductInCart();
    BigDecimal totalPrice();


}
