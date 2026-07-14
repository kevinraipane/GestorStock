package com.example.GestorStock.purchaseOrder.entity;


import com.example.GestorStock.purchaseOrder.entity.enums.PurchaseOrderStatus;
import com.example.GestorStock.purchaseOrderItem.entity.PurchaseOrderItem;
import com.example.GestorStock.stockMovement.entity.StockMovement;
import com.example.GestorStock.supplier.entity.Supplier;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchase_orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPurchaseOrder;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PurchaseOrderStatus status;

    @Column(name = "ordered_at", nullable = false, updatable = false)
    private LocalDateTime orderedAt;

    @Column(name = "received_at")
    private LocalDateTime receivedAt;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @Builder.Default
    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseOrderItem> purchaseOrderItems = new ArrayList<>();

    @Builder.Default
    @OneToMany (mappedBy = "purchaseOrder")
    private List<StockMovement> stockMovements = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        if (this.orderedAt == null) {
            this.orderedAt = LocalDateTime.now();
        }

        if (this.status == null) {
            this.status = PurchaseOrderStatus.DRAFT;
        }
    }
}
