package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "basket")
public class BasketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;
    @OneToMany(mappedBy = "basketEntity")
    private List<ProductItemEntity> basketList;
    @OneToOne
    private CompleteOrderEntity completeOrderEntity;
    private double totalCost;
}
