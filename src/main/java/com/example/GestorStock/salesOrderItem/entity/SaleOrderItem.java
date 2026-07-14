package com.example.GestorStock.salesOrderItem.entity;

import com.example.GestorStock.location.entity.Location;
import com.example.GestorStock.product.entity.Product;
import com.example.GestorStock.salesOrder.entity.SalesOrder;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "sales_order_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSalesOrderItem;

    @Column(name = "quantity_requested", nullable = false)
    private Integer quantityRequested;

    @Column(name = "quantity_dispatched", nullable = false)
    private Integer quantityDispatched;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_order_id", nullable = false)
    private SalesOrder salesOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_location_id")
    private Location originLocation;

    @PrePersist
    protected void onCreate() {
        if (this.quantityDispatched == null) {
            this.quantityDispatched = 0;
        }
    }
}
