package com.example.GestorStock.stock.entity;

import com.example.GestorStock.location.entity.Location;
import com.example.GestorStock.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "stocks",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"product_id", "location_id"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStock;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "reserved_quantity", nullable = false)
    private Integer reservedQuantity;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Transient
    public Integer getAvailableQuantity() {
        if (this.quantity == null || this.reservedQuantity == null) {
            return 0;
        }
        return this.quantity - this.reservedQuantity;
    }

    @PrePersist
    protected void onCreate() {
        this.updatedAt = LocalDateTime.now();

        if(this.quantity == null) {
            this.quantity = 0;
        }

        if(this.reservedQuantity == null){
            this.reservedQuantity = 0;
        }
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}
