package com.example.New.shop.service;

import com.example.New.shop.entities.Image;
import com.example.New.shop.entities.Product;
import com.example.New.shop.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public void saveProduct(Product product, MultipartFile file) throws IOException {
        Image image = null;
        if (file != null && file.getSize() > 0) {
            image = addImage(file);
            product.addImageToProduct(image);
        }

        product.setImages(image);
        productRepository.save(product);
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