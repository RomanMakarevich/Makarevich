package com.example.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "order_entity")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;
    @OneToOne
    @JoinColumn(name = "basket_id", nullable = false)
    private BasketEntity basketEntity;
    private double totalCost;

}
