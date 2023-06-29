package com.example.New.shop.service;

import com.example.New.shop.entities.Image;
import com.example.New.shop.entities.Product;
import com.example.New.shop.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Autowired
    private final ProductRepository productRepository;
//    @Autowired
//    private final UserRepository userRepository;
//    @Autowired
//    private final BucketServiceV2 bucketServiceV2;
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
    public Optional<Product> findById(Long id) {
       return productRepository.findById(id);
    }
//    @Override
//    public void addBucketToUser(String username, Long productId) {
//         User user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new RuntimeException(username + "not found");
//        }
//        Bucket bucket = user.getBucket();
//        if (bucket == null) {
//            Bucket bucket1 = bucketServiceV2.createBucket(user, Collections.singletonList(productId));
//            user.setBucket(bucket1);
//            userRepository.save(user);
//        } else {
//            bucketServiceV2.addProduct(bucket, Collections.singletonList(productId));
//        }
//
//    }

    private Image addImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        return image;
    }
}


