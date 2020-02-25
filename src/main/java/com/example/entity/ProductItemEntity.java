package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity(name = "product_item")
public class ProductItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity productEntity;
    private long numberOfProduct;
    private double cost;
    @ManyToOne
    @JoinColumn(name = "basket_id", nullable = false)
    private BasketEntity basketEntity;


}
