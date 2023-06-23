package com.example.New.shop.service;

import com.example.New.shop.entities.Bucket;
import com.example.New.shop.entities.Image;
import com.example.New.shop.entities.Product;
import com.example.New.shop.entities.User;
import com.example.New.shop.repo.ProductRepository;
import com.example.New.shop.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Collections;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final BucketService bucketService;
    @Override
    public void saveProduct(Product product,MultipartFile file) throws IOException {
        Image image = null;
        if (file.getSize() != 0) {
            image = addImage(file);
            product.addImageToProduct(image);
        }
        product.setImages(image);
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    @Override
    public void addBucketToUser(String username, Long productId) {
         User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException(username + "not found");
        }
        Bucket bucket = user.getBucket();
        if (bucket == null) {
            Bucket bucket1 = bucketService.createBucket(user, Collections.singletonList(productId));
            user.setBucket(bucket1);
            userRepository.save(user);
        } else {
            bucketService.addProduct(bucket, Collections.singletonList(productId));
        }
        
    }

    private Image addImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        return image;
    }
}


