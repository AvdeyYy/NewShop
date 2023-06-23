//package com.example.New.shop.service;
//import com.example.New.shop.entities.Bucket;
//import com.example.New.shop.entities.Product;
//import com.example.New.shop.entities.User;
//import com.example.New.shop.handler.BucketDto;
//import com.example.New.shop.repo.BucketRepository;
//import com.example.New.shop.repo.ProductRepository;
//import com.example.New.shop.repo.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//@Service
//@RequiredArgsConstructor
//public class BucketServiceImpl implements BucketService {
//    @Autowired
//    private final BucketRepository bucketRepository;
//    @Autowired
//    private final ProductRepository productRepository;
//    private final UserRepository userRepository;
//
//
//    @Override
//    public BucketDto createBucket(User user, List<Long> productsId) {
//        Bucket bucket = new Bucket();
//        bucket.setUser(user);
//        List<Product> productList = getProductsById(productsId);
//        bucket.setProducts(productList);
//        return bucketRepository.save(bucket);
//
//    }
//    @Override
//    public List<Product> getProductsById(List<Long> productsId) {
//        return productsId.stream()
//                .map(productRepository::getOne)
//                .collect(Collectors.toList());
//        }
//    @Override
//    public void addProduct(Bucket bucket, List<Long> productId) {
//        List<Product> productList = bucket.getProducts();
//        List<Product> newProductList = productList == null ? new ArrayList<>() : new ArrayList<>(productList);
//        newProductList.addAll(getProductsById(productId));
//        bucket.setProducts(newProductList);
//        bucketRepository.save(bucket);
//        }
//        @Override
//        public Bucket getBucket(String username) {
//            User user = userRepository.findByUsername(username);
//            if (user.getBucket() == null) {
//                return new Bucket();
//            }
//             Bucket bucket = new Bucket();
//             List<Product> products = user.getBucket().getProducts();
//             bucket.setProducts(products);
//             return bucketRepository.save(bucket);
//        }
//
////    @Override
////    public int totalPriceInBucket() {
////        List<Product> products =
////        for (Product product : products) {
////            if (products.isEmpty()) {
////
////            }
////        }
////    }
//}
