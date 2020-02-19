package com.example.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "waryhouse")
public class WarehouseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "productName", nullable = false)
    private ProductEntity productEntity;
    private long numberOfProduct;
    private Double cost;
    @ManyToOne
    private BasketEntity basketEntity;
}
