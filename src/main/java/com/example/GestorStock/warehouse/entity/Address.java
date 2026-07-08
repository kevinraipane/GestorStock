package com.example.GestorStock.warehouse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Column(nullable = false, length = 150)
    private String street;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(name = "zip_code", nullable = false, length = 15)
    private String zipCode;

    @Column(nullable = false, length = 50)
    private String province;
}
