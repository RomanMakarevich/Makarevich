package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity(name = "order_entity")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;
//    @OneToOne
//    @JoinColumn(name = "basket_id", nullable = false)
//    private BasketEntity basketEntity;
    @OneToMany(mappedBy = "orderEntity")
    private List<ProductItemEntity> basketList;
    private double totalCost;

}
