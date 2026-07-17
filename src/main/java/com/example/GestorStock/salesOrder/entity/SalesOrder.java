package com.example.GestorStock.salesOrder.entity;

import com.example.GestorStock.salesOrder.entity.enums.SalesOrderStatus;
import com.example.GestorStock.salesOrderItem.entity.SalesOrderItem;
import com.example.GestorStock.stockMovement.entity.StockMovement;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sales_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSalesOrder;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SalesOrderStatus status;

    @Column(name = "customer_name", nullable = false, length = 150)
    private String customerName;

    @Column(name = "customer_ref", length = 150)
    private String customerRef;

    @Column(name = "ordered_at", nullable = false)
    private LocalDateTime orderedAt;

    @Column(name = "dispatched_at")
    private LocalDateTime dispatchedAt;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Builder.Default
    @OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalesOrderItem> salesOrderItems = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "salesOrder")
    private List<StockMovement> stockMovements = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        if (this.orderedAt == null) {
            this.orderedAt = LocalDateTime.now();
        }

        if (this.status == null) {
            this.status = SalesOrderStatus.DRAFT;
        }
    }
}
