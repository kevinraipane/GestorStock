package com.example.GestorStock.location.entity;

import com.example.GestorStock.warehouse.entity.Warehouse;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "locations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocation;

    @Column(nullable = false, length = 10)
    private String aisle;

    @Column(nullable = false, length = 10)
    private String shelf;

    @Column(nullable = false, length = 10)
    private String bin;

    @Column(name = "full_code", nullable = false, unique = true, length = 50)
    private String fullCode;

    @Column(nullable = false)
    private Boolean available;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @PrePersist
    protected void onCreate() {
        if (this.available == null) {
            this.available = true;
        }
    }
}
