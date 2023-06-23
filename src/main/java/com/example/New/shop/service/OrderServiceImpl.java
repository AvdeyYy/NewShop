package com.example.New.shop.service;

import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl {
    @Autowired
    private final BucketService bucketService;

    public OrderServiceImpl(BucketService bucketService) {
        this.bucketService = bucketService;
    }

}
