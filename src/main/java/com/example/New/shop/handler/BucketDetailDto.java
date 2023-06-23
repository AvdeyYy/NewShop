package com.example.New.shop.handler;

import com.example.New.shop.entities.Product;

import java.math.BigDecimal;

public class BucketDetailDto {
    private  String title;
    private  String description;
    private BigDecimal price;

    private BucketDetailDto(Product product) {
        this.description = product.getDescription();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }
}
