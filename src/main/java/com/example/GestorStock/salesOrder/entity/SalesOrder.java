package com.example.GestorStock.salesOrder.entity;

import com.example.GestorStock.salesOrder.entity.enums.SalesOrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
