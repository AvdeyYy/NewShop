package com.example.New.shop.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")

public class Product {
    @Id
    @Nonnull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private BigDecimal price;
    private String description;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private Image images;

    public Product(String title, BigDecimal price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public void addImageToProduct(Image image) {
        image.setProduct(this);
    }
}