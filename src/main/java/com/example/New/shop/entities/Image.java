package com.example.New.shop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long size;
    private String contentType;
    private byte[] bytes;
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Product product;
}
