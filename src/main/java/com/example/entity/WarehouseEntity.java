package com.example.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class WarehouseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "productName", nullable = false)
    private ProductEntity productEntity;
    private long numberOfProduct;
}
