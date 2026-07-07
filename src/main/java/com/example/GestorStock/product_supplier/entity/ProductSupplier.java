package com.example.GestorStock.product_supplier.entity;

import com.example.GestorStock.product.entity.Product;
import com.example.GestorStock.supplier.entity.Supplier;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSupplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double unit_cost;

    @Column(nullable = false)
    private int lead_time_days;

    @Column(nullable = false)
    private Boolean prefered;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
