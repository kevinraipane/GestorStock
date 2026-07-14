package com.example.GestorStock.purchaseOrderItem.entity;

import com.example.GestorStock.product.entity.Product;
import com.example.GestorStock.purchaseOrder.entity.PurchaseOrder;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "purchase_order_items")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PurchaseOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPurchaseOrderItem;

    @Column(name = "quantity_ordered", nullable = false)
    private Integer quantityOrdered;

    @Column(name = "quantity_received", nullable = false)
    private Integer quantityReceived;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "purchase_order_id", nullable = false)
    private PurchaseOrder purchaseOrder;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "product_id", nullable = false)
    private Product product;
}
