package com.example.GestorStock.stockMovement.entity;

import com.example.GestorStock.location.entity.Location;
import com.example.GestorStock.product.entity.Product;
import com.example.GestorStock.purchaseOrder.entity.PurchaseOrder;
import com.example.GestorStock.salesOrder.entity.SalesOrder;
import com.example.GestorStock.stockMovement.entity.enums.MovementType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_movements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStockMovement;

    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type", nullable = false, updatable = false)
    private MovementType movementType;

    @Column(nullable = false, updatable = false)
    private Integer quantity;

    @Column(length = 100, updatable = false)
    private String reference;

    @Column(columnDefinition = "TEXT", updatable = false)
    private String notes;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_location_id", updatable = false)
    private Location originLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_location_id", updatable = false)
    private Location destinationLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_order_id", updatable = false)
    private PurchaseOrder purchaseOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_order_id", updatable = false)
    private SalesOrder salesOrder;

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}
