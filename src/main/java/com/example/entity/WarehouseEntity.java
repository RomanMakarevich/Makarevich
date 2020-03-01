package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity(name = "waryhouse")
public class WarehouseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(optional = false)
    @JoinColumn(name = "product_name", nullable = false)
    private ProductEntity productEntity;
    private Long numberOfProduct;
    private double cost;
    @ManyToOne
    private BasketEntity basketEntity;
}
