package com.example.New.shop.service;

import com.example.New.shop.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class BucketServiceImplV2 implements BucketServiceV2 {
    private final Map<Optional<Product>, Integer> bucket = new HashMap<>();
    @Override
    public void addProduct(Optional<Product> product) {
        if (bucket.containsKey(product)) {
            bucket.replace(product, bucket.get(product) + 1);
        } else {
            bucket.put(product, 1);
        }
    }

    @Override
    public void removeProduct(Optional<Product> product) {
        if (bucket.containsKey(product)) {
            if (bucket.get(product) > 1) {
                bucket.replace(product, bucket.get(product) - 1);
            } else if (bucket.get(product) > 1) {
                bucket.remove(product);
            }
        }

    }

    @Override
    public void clearProduct() {
        bucket.clear();
    }

    @Override
    public Map<Product, Integer> viewProductInCart() {
        return null;
    }

    @Override
    public BigDecimal totalPrice() {
        return null;
    }
}
