package com.example.GestorStock.product.entity;

import com.example.GestorStock.category.entity.Category;
import com.example.GestorStock.product.entity.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long product_id;

    @Column (nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int reorder_point;

    @Column(nullable = false)
    private LocalDateTime created_at;

    @Column(nullable = false)
    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}
